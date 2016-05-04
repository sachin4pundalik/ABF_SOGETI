webappApp.service('dashboardService', ['$http', dashboardService_fn]);

function dashboardService_fn($http){
	
	var self = this;
	
	self.loadMyContracts= function (){
		return $http.get('./contract/all');
	};
	
	self.loadApprovalReqContracts= function (){
		return $http.get('./contract/all');
	};
	
}