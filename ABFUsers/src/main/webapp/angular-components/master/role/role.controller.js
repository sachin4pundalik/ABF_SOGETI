webappApp.controller('MasterRoleCtrl', [ '$scope', '$location',
                                         'toastr','dataSetService', 'masterDataService', 'ABF_CONSTANTS', MasterRoleCtrl_Fn ]);

function MasterRoleCtrl_Fn($scope, $location,toastr, dataSetService, masterDataService, ABF_CONSTANTS){
	
	$scope.roles = dataSetService.roles;
	
	$scope.role= {
			roleId:'',
			roleType:''
	};
	$scope.currentView='';
	
	$scope.getroles = function (){
		var url = './role/all'; //'./json/roles.json';
		
		masterDataService.fetchAll(url)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				console.log("Success : " + JSON.stringify(response));
				$scope.roles = dataSetService.roles = response.data.successResponse;
				$scope.goBack();
				toastr.info("Roles updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.ROLES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	$scope.newRole = function(){
		$scope.currentView ="new";
		$scope.reset();
	}
	
	$scope.update=function(){
		masterDataService.update('./role/update', $scope.role)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getroles();
				$scope.goBack();
				toastr.info("Roles updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.ROLES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	$scope.save= function(){
		
		masterDataService.save('./role/create', $scope.role)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getroles();
				$scope.goBack();
				toastr.info("Roles updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.ROLES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.edit= function(roleId){
		$scope.currentView = 'edit';
		$scope.getrole(roleId);
	};
	
	$scope.getrole = function ( roleId ){
		masterDataService.fetch('./role/find/', roleId)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				console.log("Success : " + JSON.stringify(response));
				$scope.role= response.data.successResponse;
				toastr.info("role is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.ROLES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	$scope.remove=function(roleId){
		masterDataService.remove('./role/delete/', roleId)
		.then(function(response){
			$scope.getroles();
			toastr.success("Role is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.ROLES);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.reset();
	}
	
	$scope.reset= function(){
		$scope.role= {
				roleId:'',
				roleType:''
		};
	}
	$scope.getroles();
}