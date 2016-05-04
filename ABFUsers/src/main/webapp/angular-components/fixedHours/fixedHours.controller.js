
webappApp.controller('fixedHoursCtrl', ['$scope','$http','fixedHoursService', 'toastr', 'masterDataService', 'ABF_CONSTANTS','dataSetService', fixedHoursCtrl_fn]);

function fixedHoursCtrl_fn($scope,$http, fixedHoursService, toastr, masterDataService, ABF_CONSTANTS, dataSetService) {
	
	$scope.fixedProjectHoursList = [];
	$scope.selectedFixedTypes = [];
	
	$scope.fixedHoursDataSet = dataSetService.fixedHours;
	
	$scope.addFixedHourBilling = function (){
		$scope.resetFixedHours();
		
		if(angular.isDefined($scope.selectedFixedTypes) && $scope.selectedFixedTypes.length > 0){
			angular.forEach($scope.selectedFixedTypes, function (item){
				var item = JSON.parse(item);
				$scope.fixedProjectHoursList.push({
					price:'',
					description:item.fixedcostDescription,
					contractId:'',
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
			_.remove($scope.fixedProjectHoursList, function(currentObject) {
			    return currentObject.fixedcostName === fixedHour.fixedcostName;
			}); 
			toastr.success("Option \""+fixedHour.typeName+"\" removed!", "Message");
	}
	
	$scope.resetFixedHours= function(){
		
		if(angular.isDefined($scope.fixedProjectHoursList) && $scope.fixedProjectHoursList.length > 0){
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
					console.info(JSON.stringify(response));
					toastr.success("Successfully saved ..!!", ABF_CONSTANTS.INFO_HEADER);
				}else{
					console.error(JSON.stringify(error, null ,2));
					toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
				}
			}, function(error){
				console.error(JSON.stringify(error, null ,2));
				toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			});
			toastr.info("Reset is done. Please select atleast 1 option to save.", "Message");
		}
		
	};
	
	masterDataService.fetchAll('./fixedCost/all')
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				console.log("Success : " + JSON.stringify(response));
				$scope.fixedHoursDataSet = dataSetService.fixedHours = response.data.successResponse;
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.error(JSON.stringify(error));
		});
	
	
}