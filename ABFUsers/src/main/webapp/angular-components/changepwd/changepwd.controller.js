'use strict';
webappApp.controller("changePasswordCtrl", ['$scope','$http',function($scope,$http) {  
	     
	 $scope.pw1 = '';
	 $scope.doChangePassword = function() {
		  
			$http.post('/changepassword', $scope.pw2)
			 .success(function(data,header,config,status)
			 {
				 $scope.pw1 = '';
				 $scope.pw2 = '';
				 $scope.changePasswordStatus = data.message;
				 
			 })
			 .error(function(data, status, headers, config){
				 $scope.changePasswordStatus = data.message;
			  });
		};
	
    }]);
