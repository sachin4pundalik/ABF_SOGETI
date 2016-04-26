webappApp.service('dashboardService', ['$http', '$q', dashboardService_fn]);

function dashboardService_fn($http, $q){
	
	function loadMyContracts(){
		// is the same as
		var promise = $http.get('/api/v1/movies/avengers');
		return promise;
	}
	
	function loadApprovalReqContracts(){
		var promise = $http.get('/api/v1/movies/avengers');
		return promise;
	}
	
}