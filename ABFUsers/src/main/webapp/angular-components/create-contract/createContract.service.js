'use strict';

webappApp.service('createContractService', ['$http', function($http, ABF_CONSTANTS) {

	var contractService = this;

	contractService.createContract = function (contract){
		
		return $http.post('./contract/create',contract);
		
	}
	
	contractService.getAllContracts = function() {
		return $http.get('./contract/all');
	};

	contractService.getUserContracts = function() {
		
		return contractService.userContracts;
	};

	contractService.getReqApprovalContracts = function() {
		
		return contractService.reqApprovalContracts;
	};
} ]);