webappApp.controller('MasterUserRoleCtrl', [ '$scope', '$location',
                                         'toastr','dataSetService', 'masterDataService', 'ABF_CONSTANTS', MasterUserRoleCtrl_Fn ]);

function MasterUserRoleCtrl_Fn($scope, $location,toastr, dataSetService, masterDataService, ABF_CONSTANTS){
	
	$scope.userRoles = dataSetService.userRoles;
	
	$scope.userRole= {
			userRoleId:'',
			userRole:''
	};
	$scope.currentView='';
	
	$scope.getuserRoles = function (){
		masterDataService.fetchAll('./userRole/all')
		.then(function(response){
			console.log("Success : " + JSON.stringify(response));
			$scope.userRoles = dataSetService.userRoles = response.data.successResponse;
			toastr.info("User Role loaded from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.USER_ROLES);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	$scope.newUserRole = function(){
		$scope.currentView ="new";
		$scope.reset();
	}
	
	$scope.update=function(){
		masterDataService.update('./userRole/update', $scope.userRole)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getuserRoles();
				$scope.goBack();
				toastr.success("User Role updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.USER_ROLES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.save= function(){
		
		masterDataService.save('./userRole/create', $scope.userRole)
		.then(function(response){
			$scope.getuserRoles();
			$scope.goBack();
			toastr.success("User Role is saved..!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.USER_ROLES);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.edit= function(userRoleId){
		$scope.currentView = 'edit';
		$scope.getuserRole(userRoleId);
	};
	
	$scope.getuserRole = function ( userRoleId ){
		masterDataService.fetch('./userRole/find/', userRoleId)
		.then(function(response){
			console.log("Success : " + JSON.stringify(response));
			$scope.userRole=response.data.successResponse;
			toastr.info("User Role is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.USER_ROLES);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.reset=function(){
		$scope.userRole= {
				userRoleId:'',
				userRole:''
		};
	};
	
	$scope.remove=function(userRoleId){
		masterDataService.remove('./userRole/delete/', userRoleId)
		.then(function(response){
			$scope.getuserRoles();
			toastr.success("User Role is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.USER_ROLES);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.reset();
	};
	
	$scope.getuserRoles();
}