webappApp.controller('MasterStayCtrl', [ '$scope', '$location',
                                         'toastr','DataSetService', 'masterDataService', 'ABF_CONSTANTS', MasterStayCtrl_Fn ]);

function MasterStayCtrl_Fn($scope, $location,toastr, DataSetService, masterDataService, ABF_CONSTANTS){
	
	$scope.stayTypes = DataSetService.stayTypes;
	
	$scope.stay= {
			stayTypeId:'',
			stayType:''
	};
	$scope.currentView='';
	
	$scope.getstayTypes = function (){
		masterDataService.fetchAll('./stayType/all')
		.then(function(response){
			$scope.stayTypes = DataSetService.stayTypes = response.data.successResponse;
			toastr.info("Stay Types updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.STAY_TYPE);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	$scope.newStay = function(){
		$scope.currentView ="new";
		$scope.reset();
	}
	
	$scope.update=function(){
		masterDataService.update('./stayType/update', $scope.stay)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getstayTypes();
				$scope.goBack();
				toastr.info("Bands updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.save= function(){
		
		masterDataService.save('./stayType/create', $scope.stay)
		.then(function(response){
			$scope.getstayTypes();
			$scope.goBack();
			toastr.success("Stay Type is saved..!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.STAY_TYPE);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.edit= function(stayId){
		$scope.currentView = 'edit';
		$scope.getstay(stayId);
	};
	
	$scope.getstay = function ( stayId ){
		masterDataService.fetch('./stayType/find/', stayId)
		.then(function(response){
			$scope.stay=response.data.successResponse;
			toastr.info("Band is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.STAY_TYPE);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.reset=function(){
		$scope.stay= {
				stayTypeId:'',
				stayType:''
		};
	};
	
	$scope.remove=function(stayTypeId){
		masterDataService.remove('./stayType/delete/', stayTypeId)
		.then(function(response){
			$scope.getstayTypes();
			toastr.success("Stay Type is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.STAY_TYPE);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.reset();
	};
	
	$scope.getstayTypes();
}