webappApp.service('LoginService', ['$http', LoginService_fn]);

function LoginService_fn($http) {
	
	var loginService = this;
	
	loginService.validateUserUrl = './login/authenticate';
	
	loginService.validateUser= function (user){
		return  $http.post(loginService.validateUserUrl, user);
	}
	
}