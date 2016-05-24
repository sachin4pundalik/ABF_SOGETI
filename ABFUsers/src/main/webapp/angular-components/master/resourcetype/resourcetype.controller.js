webappApp.controller('MasterResourceTypeCtrl', [ '$scope', '$location',
                                         'toastr','DataSetService', 'MasterDataService', 'ABF_CONSTANTS', MasterResourceTypeCtrl_Fn ]);

function MasterResourceTypeCtrl_Fn($scope, $location,toastr, DataSetService, MasterDataService, ABF_CONSTANTS){
	
	$scope.rTypes = DataSetService.rTypes;
	
	$scope.rType= {
			resourcetypeId:'',
			resourceType:''
	};
	$scope.currentView='';
	
	$scope.getrTypes = function (){
		var url = './resourcetype/all'; //'./json/resourceTypes.json'; 
		
		MasterDataService.fetchAll(url)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.rTypes = DataSetService.rTypes = response.data.successResponse;
				toastr.info("rTypes updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.RESOURCE_TYPES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.newRType = function(){
		$scope.currentView ="new";
		$scope.reset();
	}
	
	$scope.update=function(){
		MasterDataService.update('./resourcetype/update', $scope.rType)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.goBack();
				$scope.getrTypes();
				toastr.info("Resource type updated from server.", ABF_CONSTANTS.MASTER_DATA + ABF_CONSTANTS.RESOURCE_TYPES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.save= function(){
		
		MasterDataService.save('./resourcetype/create', $scope.rType)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.goBack();
				$scope.getrTypes();
				toastr.info("rTypes updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.RESOURCE_TYPES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.edit= function(rTypeId){
		$scope.currentView = "edit";
		$scope.getrType(rTypeId);
	};
	
	$scope.getrType = function ( rTypeId ){
		MasterDataService.fetch('./resourcetype/find/', rTypeId)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.rType=response.data.successResponse;
				toastr.info("Resource Type is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.RESOURCE_TYPES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.remove=function(rTypeId){
		
		MasterDataService.remove('./resourcetype/delete/',rTypeId)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getrTypes();
				toastr.success("Resource type removed!!.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.RESOURCE_TYPES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.remove=function(rTypeId){
		MasterDataService.remove('./resourcetype/delete/', rTypeId)
		.then(function(response){
			$scope.getrTypes();
			toastr.success("Resource Type is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.RESOURCE_TYPES);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.reset=function(){
		$scope.rType= {
				resourcetypeId:'',
				resourceType:''
		};
	};
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.reset();
	}
	
	$scope.getrTypes();
}