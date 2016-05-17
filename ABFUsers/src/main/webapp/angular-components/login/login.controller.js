'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the webappApp
 */
webappApp.controller('loginCtrl',
		[ '$scope', '$location', '$rootScope', '$cookieStore', 'AuthService', 'DataSetService',
				'toastr', 'AUTH_EVENTS', 'ABF_CONSTANTS', LoginCtrl_Fn ]);

function LoginCtrl_Fn($scope, $location, $rootScope, $cookieStore, AuthService,DataSetService,
		toastr, AUTH_EVENTS, ABF_CONSTANTS) {

	$scope.credentials = {
		userName : '',
		password : ''
	};

	$scope.error = '';

	$scope.doLogin = function() {
		$cookieStore.remove('abfuser');
		AuthService.login($scope.credentials).then(
				function(response) {
					if(angular.equals(response.status, ABF_CONSTANTS.SUCCESS)){
						var user = response.successResponse;
						$cookieStore.put('abfuser', JSON.stringify(user));
						$location.path("/landing");
						$rootScope.$broadcast(AUTH_EVENTS.loginSuccess, user);
						
						let msg = ABF_CONSTANTS.AUTHENTICATE_SUCCESS_MESSAGE.replace("$1", (user.firstName+", "+user.lastName));

						//DataSetService.fetchAllMasterData();
						toastr.success(msg, ABF_CONSTANTS.INFO_HEADER);
					}else{
						$scope.error = ABF_CONSTANTS.AUTHENTICATE_FAILURE_MESSAGE;
						toastr.error(ABF_CONSTANTS.AUTHENTICATE_FAILURE_MESSAGE, ABF_CONSTANTS.AUTHENTICATE_FAILURE_HEADER);
						$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
					}
				},
				function() {
					$scope.error = ABF_CONSTANTS.AUTHENTICATE_FAILURE_MESSAGE;
					toastr.error(ABF_CONSTANTS.AUTHENTICATE_FAILURE_MESSAGE, ABF_CONSTANTS.AUTHENTICATE_FAILURE_HEADER);
					$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				});
	};
}