'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the webappApp
 */
webappApp.controller('logoutCtrl', function($scope, $routeParams, $http,
		$resource, $location, $rootScope, $cookieStore) {

	$rootScope.currentUser = null;
	$cookieStore.put('loggedin', '');
	$location.path('/login');

});
