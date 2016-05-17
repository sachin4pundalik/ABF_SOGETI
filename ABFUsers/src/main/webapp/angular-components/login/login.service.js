webappApp.service('AuthService', ['$http','Session', 'ABF_CONSTANTS', AuthService_fn]);

function AuthService_fn($http, Session, ABF_CONSTANTS) {
	
	var authService = this;
	authService.validateUserUrl = './login/authenticate';
	
	  authService.login = function (credentials) {
	    return $http
	      .post(authService.validateUserUrl, credentials)
	      .then(function (response) {
	    	  if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
	    		  Session.create(response.data.successResponse);
	  	          //console.log(JSON.stringify(response.data.successResponse, null ,2));
	  	          return response.data;
	    	  }else{
	    		  Session.destroy();
	  	          return response.data;
	    	  }
	      });
	  };
	 
	  authService.isAuthenticated = function () {
		  if(angular.isObject(Session.sessionUser)){
			  console.log(JSON.stringify(Session.sessionUser));  
		  }
		 // console.log("isAuthenticated : " + angular.isDefined(Session.sessionUser) + angular.isObject(Session.sessionUser));
	    return (angular.isDefined(Session.sessionUser) && angular.isObject(Session.sessionUser));
	  };
	 
	  authService.isAuthorized = function (authorizedRoles) {
	    if (!angular.isArray(authorizedRoles)) {
	      authorizedRoles = [authorizedRoles];
	    }
	    return (authService.isAuthenticated() &&
	      authorizedRoles.indexOf(Session.sessionUser) !== -1);
	  };
	  
	  authService.setUser = function (user) {
		  Session.create(user);
	  };
	  
	  authService.logoutUser = function () {
		  	Session.destroy();
	  };
	
}