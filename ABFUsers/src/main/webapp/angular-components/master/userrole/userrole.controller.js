webappApp.controller('MasterUserRoleCtrl', [ '$scope', '$location',
                                         'toastr','DataSetService', 'MasterDataService', 'ABF_CONSTANTS', MasterUserRoleCtrl_Fn ]);

function MasterUserRoleCtrl_Fn($scope, $location,toastr, DataSetService, MasterDataService, ABF_CONSTANTS){
	
	$scope.userRoles = DataSetService.userRoles;
	
	$scope.userRole= {
			userRoleId:'',
			userRole:''
	};
	$scope.currentView='';
	
	$scope.getuserRoles = function (){
		MasterDataService.fetchAll('./userRole/all')
		.then(function(response){
			$scope.userRoles = DataSetService.userRoles = response.data.successResponse;
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
		MasterDataService.update('./userRole/update', $scope.userRole)
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
		
		MasterDataService.save('./userRole/create', $scope.userRole)
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
		MasterDataService.fetch('./userRole/find/', userRoleId)
		.then(function(response){
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
		MasterDataService.remove('./userRole/delete/', userRoleId)
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