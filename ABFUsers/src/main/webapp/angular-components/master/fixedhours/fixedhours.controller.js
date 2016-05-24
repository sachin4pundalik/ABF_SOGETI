webappApp.controller('MasterFixedHourCtrl', [ '$scope', '$location',
                                         'toastr','DataSetService', 'MasterDataService', 'ABF_CONSTANTS', MasterFixedHourCtrl_Fn ]);

function MasterFixedHourCtrl_Fn($scope, $location,toastr, DataSetService, MasterDataService, ABF_CONSTANTS){
	
	$scope.fixedHours = DataSetService.fixedHours;
	
	$scope.fHour= {
			fixedcostId:'',
			fixedcostName:'',
			fixedcostDescription:''
	};
	$scope.currentView='';
	
	$scope.getfixedHours = function (){
		var url = './fixedCost/all';// './json/fixedHours.json'; //'./masterdata/fixedHours';
		
		MasterDataService.fetchAll(url)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.fixedHours = DataSetService.fixedHours = response.data.successResponse;
				toastr.info("Fixed Hours loaded from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.FIXED_HOURS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.newfHour = function(){
		
		$scope.currentView ="new";
		$scope.reset();
	}
	
	$scope.update=function(){
		MasterDataService.update('./fixedCost/update', $scope.fHour)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getfixedHours();
				$scope.goBack();
				toastr.info("Fixed Hours updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.FIXED_HOURS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.save= function(){
		
		MasterDataService.save('./fixedCost/create', $scope.fHour)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getfixedHours();
				$scope.goBack();
				toastr.info("Fixed Hours saved to server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.FIXED_HOURS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.edit= function(fHourId){
		$scope.currentView = 'edit';
		$scope.getfHour(fHourId);
	};
	
	$scope.getfHour = function ( fHourId ){
		MasterDataService.fetch('./fixedCost/find/', fHourId)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.fHour=response.data.successResponse;
				toastr.info("Fixed Hour is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.FIXED_HOURS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.remove=function(fhourId){
		MasterDataService.remove('./fixedCost/delete/', fhourId)
		.then(function(response){
			$scope.getfixedHours();
			toastr.success("Fixed Hours is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.FIXED_HOURS);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.reset();
	}
	
	$scope.reset=function(){

		$scope.fHour= {
				fixedcostId:'',
				fixedcostName:'',
				fixedcostDescription:''
		};
	};
	
	$scope.getfixedHours();
}