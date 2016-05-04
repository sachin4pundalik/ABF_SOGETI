'use strict';

webappApp.controller('createContractCtrl', ['$scope','createContractService', '$location', 'dataSetService', 'toastr','ABF_CONSTANTS', createContractCtrl_Fn]);

	
function createContractCtrl_Fn($scope, createContractService, $location, dataSetService, toastr, ABF_CONSTANTS) { 
	
	
	$scope.contract = {
			contractId: '',
		    contractName:'',
			customerName:'',
			companyName:'',
			comments:'',
			contractCreatedBy:'venkata.kalyanam@gmail.com',
			contractStartDate:'',
			contractEndDate:'',
			loginId:'2'
		};
	$scope.contractList = [];
	
	function resetContract(){
		$scope.contract = {
				contractId: '',
			    contractName:'',
				customerName:'',
				companyName:'',
				comments:'',
				contractCreatedBy:'venkata.kalyanam@gmail.com',
				contractStartDate:'',
				contractEndDate:'',
				loginId:'2'
			};
	}
	
	$scope.createContract = function() {
		toastr.options = dataSetService.errorAlertOptions;
		createContractService
		.createContract($scope.contract)
		.then(
				function(response) {
					if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
						resetContract();
						toastr.options = dataSetService.successAlertOptions;
						toastr.success(
								'Contract created successfully!!',
								'Message');
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
		toastr.options = dataSetService.errorAlertOptions;
		if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
			createContractService
					.createContract($scope.contract)
					.then(
							function(response) {
								resetContract();
								toastr.options = dataSetService.successAlertOptions;
								toastr.success(
										'Contract created successfully!!',
										'Message');
								$location.path('/hours');
							},
							function(error) {
								toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
								console.error(JSON.stringify(error, null, 2));
							});
		} else {
			toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
			console.error(JSON.stringify(error, null, 2));
		}
	}	
}