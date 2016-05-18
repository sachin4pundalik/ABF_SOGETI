'use strict';

webappApp.controller('createContractCtrl', ['$scope','createContractService', '$location', 'Session', 'toastr','ABF_CONSTANTS', createContractCtrl_Fn]);

	
function createContractCtrl_Fn($scope, createContractService, $location, Session, toastr, ABF_CONSTANTS) { 
	
	$scope.contract = {
			contractId: '',
		    contractName:'',
			customerName:'',
			companyName:'',
			comments:'',
			contractCreatedBy:Session.sessionUser.userName,
			contractStartDate:'',
			contractEndDate:'',
			loginId:Session.sessionUser.loginId
		};
	
	$scope.fromDt = {
			minDate:moment().add(-7, 'day').format("YYYY-MM-DD"),
			maxDate:moment().add(1, 'year').format("YYYY-MM-DD")
	};
	
	$scope.toDt = {
			minDate:moment().add(2, 'day').format("YYYY-MM-DD"),
			maxDate:moment().add(2, 'year').format("YYYY-MM-DD")
	};
	
	$scope.contractList = [];
	
	function resetContract(){
		
		$scope.contract = {
				contractId: '',
			    contractName:'',
				customerName:'',
				companyName:'',
				comments:'',
				contractCreatedBy:Session.sessionUser.userName,
				contractStartDate:'',
				contractEndDate:'',
				loginId:Session.sessionUser.loginId
			};
	}
	
	$scope.createContract = function() {
		
		createContractService
		.createContract($scope.contract)
		.then(
				function(response) {
					if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
						resetContract();
						toastr.success('Contract created successfully!!',ABF_CONSTANTS.INFO_HEADER);
						$location.path('/landing');

					} else {
						toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
						console.error(JSON.stringify(error, null, 2));
					}
				},
				function(error) {
					toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
					console.error(JSON.stringify(error, null, 2));
				});
	};
	
	$scope.goBack = function(){
		$location.path('/landing');
	};
	
	$scope.bookHours = function() {
		if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
			createContractService
					.createContract($scope.contract)
					.then(
							function(response) {
								resetContract();
								toastr.success('Contract created successfully!!', ABF_CONSTANTS.INFO_HEADER);
								$location.path('/hours');
							},
							function(error) {
								toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
							});
		} else {
			toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
		}
	}	
}