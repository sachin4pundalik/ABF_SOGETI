'use strict';

webappApp.service('createContractService', ['$http', '$q', function($http, $q) {

	var contractService = this;

	contractService.userContracts = [
					{contractId : '1',customerName : 'Sogeti B V',bisCode : '1234'},
					{contractId : '2',customerName : 'Philips B V',bisCode : '2347'},
					{contractId : '3',customerName : 'KPN NL',bisCode : '3544'},
					{contractId : '4',customerName : 'Ziggo',bisCode : '6432'}
					];
	
	contractService.reqApprovalContracts = [
	             					{contractId : '10',customerName : 'Ziggo',bisCode : '1234'},
	             					{contractId : '20',customerName : 'Philips',bisCode : '3434'},
	             					{contractId : '30',customerName : 'Sogeti',bisCode : '2346'},
	             					{contractId : '40',customerName : 'KPN',bisCode : '6523'}
	             					];
	
	contractService.createContract = function (contract){
		
		return $http.post('./contract/create',contract)
        .then(function(response) {
        	
        	var status = response.data.status;
        	var successRes = null;
        	var failureRes = null;
            if (angular.equals(status,"success")) {
            	console.log("Success " + JSON.stringify(response.data));
            	return $q.resolve(response.data.successResponse);
            } else {
            	console.log("Failure " + JSON.stringify(response.data));
                return $q.reject(response.data.successResponse);
            }
        }, function(response) {
            // something went wrong
        	console.log("Failure " + JSON.stringify(response));
            return $q.reject(response.data);
        });
	}
	
	contractService.getAllContracts = function() {
		return $http.get('./contract/all')
        .then(function(response) {
        	var status = response.data.status;
        	var successRes = null;
        	var failureRes = null;
            if (angular.equals(status,'success')) {
            	return $q.resolve(response.data.successResponse);
            } else {
                return $q.reject(response.data.successResponse);
            }
        }, function(response) {
            // something went wrong
            return $q.reject(response.data);
        });
	};

	contractService.getUserContracts = function() {
		
		return contractService.userContracts;
	};

	contractService.getReqApprovalContracts = function() {
		
		return contractService.reqApprovalContracts;
	};
} ]);