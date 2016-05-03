'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the webappApp
 */
webappApp.controller('loginCtrl', [ '$scope', '$location', '$rootScope',
		'$cookieStore', 'LoginService', 'toastr','dataSetService', LoginCtrl_Fn ]);

function LoginCtrl_Fn($scope, $location, $rootScope, $cookieStore, LoginService, toastr, dataSetService) {

	$scope.user = {
		userName : '',
		password : ''
	};
	$scope.error = '';
	
	$scope.doLogin = function() {

		LoginService.validateUser($scope.user).then(function(response){
			toastr.options=dataSetService.errorAlertOptions;
			
			if(response.data.status === 'success'){
				let user = response.data.successResponse.userName;
				
				
				toastr.options=dataSetService.successAlertOptions;
				toastr.success('Welcome to ABF !!','Welcome Message');
				$rootScope.currentUser = user;
				$rootScope.isVisible = true;
				$cookieStore.put('loggedin', 'true');
				$location.path('/landing');
			}else{
				toastr.options = {
		                closeButton: true,
		                newestOnTop: true,
		                progressBar: true,
		                positionClass: 'toast-top-full-width',
		                preventDuplicates: false,
		                onclick: null
		            };
				
				toastr.error('Sorry Unable to validate your credentials', 'Authentication Failure');
				$rootScope.isVisible = false;
				$scope.error='Sorry Unable to validate your credentials';
				$cookieStore.put('loggedin', 'false');
			}
		}, function(error){
			toastr.options = {
	                closeButton: true,
	                newestOnTop: true,
	                progressBar: true,
	                positionClass: 'toast-top-full-width',
	                preventDuplicates: false,
	                onclick: null
	            };
			toastr.error('Sorry Unable to validate your credentials', 'Authentication Failure');
			$rootScope.isVisible = false;
			$scope.error='Sorry Unable to validate your credentials';
			$cookieStore.put('loggedin', 'false');
		});
	};
}