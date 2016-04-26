'use strict';

webappApp.service('createContractService', ['$http', '$q', 'toastr',function($http, $q, toastr) {

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
            if (typeof response.data === 'object') {
                return response.data;
            } else {
            		toastr.error("Error in operation.", "Operation Failure");
            	 return response.data;
            }

        }, function(response) {
            // something went wrong
            //return $q.reject(response.data);
        	toastr.error(JSON.stringify(response,null,2), "Failed Operation");
        });
	}
	
	contractService.getAllContracts = function() {/*
		return $http.get('http://fishing-weather-api.com/sunday/afternoon')
        .then(function(response) {
            if (typeof response.data === 'object') {
                return response.data;
            } else {
                // invalid response
                return $q.reject(response.data);
            }

        }, function(response) {
            // something went wrong
            return $q.reject(response.data);
        });
	*/};

	contractService.getUserContracts = function() {
		
		return contractService.userContracts;
	};

	contractService.getReqApprovalContracts = function() {
		
		return contractService.reqApprovalContracts;
	};
} ]);