
angular.module('webappApp').controller('ApplicationController', ['$scope', 
                                               'USER_ROLES',
                                       		   'AUTH_EVENTS',
                                       		   'AuthService',
                                       		   ApplicationController_Fn]);

function ApplicationController_Fn($scope, USER_ROLES, AUTH_EVENTS,
		AuthService) {
	
	$scope.currentUser = null;
	$scope.userRoles = USER_ROLES;
	$scope.isAuthorized = false;
	$scope.isAuthenticated = false;

	$scope.setCurrentUser = function(user) {
		$scope.currentUser = user;
	};

	$scope.resetCurrentUser = function() {
		$scope.currentUser = {};
	}

	$scope.$on(AUTH_EVENTS.loginSuccess, function(evt, data) {
		AuthService.setUser(data);
		$scope.isAuthorized = AuthService.isAuthorized();
		$scope.isAuthenticated = AuthService.isAuthenticated();
		$scope.currentUser = data;
	});

	function resetUserRestriction(){
		$scope.isAuthorized = false;
		$scope.isAuthenticated = false;
		$scope.currentUser = null;
		AuthService.logoutUser();
	}
	
	$scope.$on(AUTH_EVENTS.loginFailed, function() {
		resetUserRestriction();
	});

	$scope.$on(AUTH_EVENTS.logoutSuccess, function() {
		resetUserRestriction();
	});
	
	$scope.$on(AUTH_EVENTS.sessionTimeout, function() {
		resetUserRestriction();
	});
}