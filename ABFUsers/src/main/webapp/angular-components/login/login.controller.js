'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the webappApp
 */
webappApp.controller('loginCtrl', [ '$scope', '$location', '$rootScope',
		'$cookieStore', 'LoginService', 'toastr', LoginCtrl_Fn ]);

function LoginCtrl_Fn($scope, $location, $rootScope, $cookieStore, LoginService, toastr) {

	$scope.user = {
		userName : '',
		password : ''
	};

	$scope.doLogin = function() {

		LoginService.validateUser($scope.user).then(function(response){
			if(response.data.status === 'success'){
				let user = response.data.successResponse.userName;
				toastr.success('Welcome to ABF !!', "Welcome Message");
				$rootScope.currentUser = user;
				$rootScope.isVisible = true;
				$cookieStore.put('loggedin', 'true');
				$location.path('/landing');
			}else{
				toastr.error('Sorry Unable to validate your credentials', "Authentication Failure");
				$rootScope.isVisible = false;
				$cookieStore.put('loggedin', 'false');
			}
		}, function(error){
			toastr.error('Sorry Unable to validate your credentials', "Authentication Failure");
			$rootScope.isVisible = false;
			$cookieStore.put('loggedin', 'false');
		});
	};
}