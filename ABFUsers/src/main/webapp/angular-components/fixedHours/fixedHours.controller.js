
webappApp.controller('fixedHoursCtrl', ['$scope','$http','fixedHoursService', 'toastr', 'masterDataService', 'ABF_CONSTANTS','DataSetService', fixedHoursCtrl_fn]);

function fixedHoursCtrl_fn($scope,$http, fixedHoursService, toastr, masterDataService, ABF_CONSTANTS, DataSetService) {
	
	$scope.fixedProjectHoursList = [];
	$scope.selectedFixedTypes = [];
	
	$scope.contract = DataSetService.currContract;
	
	$scope.fixedHoursDataSet = DataSetService.fixedHours;
	
	$scope.addFixedHourBilling = function (){
		$scope.resetFixedHours();
		
		if(angular.isDefined($scope.selectedFixedTypes) && $scope.selectedFixedTypes.length > 0){
			angular.forEach($scope.selectedFixedTypes, function (item){
				var item = JSON.parse(item);
				$scope.fixedProjectHoursList.push({
					price:'',
					description:item.fixedcostDescription,
					contractId:$scope.contract.contractId,
					fixedId:'',
					fixedcostId:item.fixedcostId,
					fixedcostName:item.fixedcostName
				});
				
			});
		}else{
			toastr.warning("Choose atleast 1 option. ", "Validation");
		}
	};
	
	$scope.removeItem = function(fixedHour){
		
		if(fixedHour.fixedId>0){
			masterDataService.remove('./fixedContract/', fixedHour.fixedId)
			.then(function(response){
				if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
					toastr.success("Option \""+fixedHour.typeName+"\" removed!", "Message");
				}else{
					toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
				}
			}, function(error){
				toastr.error("There is problem to delete, please contact Admin.", ABF_CONSTANTS.FAILURE_HEADER);
			});
		}
		_.remove($scope.fixedProjectHoursList, function(currentObject) {
		    return currentObject.fixedcostName === fixedHour.fixedcostName;
		}); 
		
	}
	
	$scope.resetFixedHours= function(){
		
		if(angular.isArray($scope.fixedProjectHoursList) && $scope.fixedProjectHoursList.length > 0){
			$scope.fixedProjectHoursList= [];
			toastr.info("Reset is done. Please select atleast 1 option to save.", ABF_CONSTANTS.INFO_MESSAGE);
		}
	}
	
	$scope.saveFixedHours= function (){
		if(angular.isDefined($scope.fixedProjectHoursList) && $scope.fixedProjectHoursList.length > 0){
			fixedHoursService.setFixedHours( $scope.fixedProjectHoursList );
			fixedHoursService.save( $scope.fixedProjectHoursList )
			.then(function(response){
				if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
					toastr.success("Successfully saved ..!!", ABF_CONSTANTS.INFO_HEADER);
				}else{
					toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
				}
			}, function(error){
				toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			});
			toastr.info("Reset is done. Please select atleast 1 option to save.", "Message");
		}
		
	};
	
	$scope.getFixedContracts = function(){
		
		if(angular.isDefined(DataSetService.currContract.contractId)){
			masterDataService.fetch('./fixedContract/contract/find/', DataSetService.currContract.contractId)
			.then(function(response){
				if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
					angular.forEach(response.data.successResponse, function(item){
						$scope.fixedProjectHoursList.push(item);
					});
				}else{
					toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
				}
			}, function(error){
				toastr.error("Unable to fetch contract.", ABF_CONSTANTS.FAILURE_HEADER);
			});
		}
		
	};
	
	$scope.getFixedContracts();
	
	masterDataService.fetchAll('./fixedCost/all')
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.fixedHoursDataSet = DataSetService.fixedHours = response.data.successResponse;
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
		});
	
}