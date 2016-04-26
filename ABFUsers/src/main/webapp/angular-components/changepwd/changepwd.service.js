'use strict';
webappApp.controller("changePwdService", ['$http','$q', function($scope,$http) {  
	     
	
		var defered = $q.deferd();
		
		  return $http.post('changepassword/'+$scope.pw2)
			 .success(function(data,header,config,status)
			 {
				 $scope.pw1 = '';
				 $scope.pw2 = '';
				 $scope.changePasswordStatus = data.message;
				 
			 })
			 .error(function(data, status, headers, config){
				 $scope.changePasswordStatus = data.message;
			  });
		
	
    }]);
