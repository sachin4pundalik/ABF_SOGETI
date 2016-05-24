webappApp.controller('dashboardCtrl', ['$scope', 'dashboardService', 'toastr', 'ABF_CONSTANTS', 'DataSetService', '$location', dashboardCtrl_fn]);

function dashboardCtrl_fn($scope, dashboardService, toastr, ABF_CONSTANTS, DataSetService, $location){
	
	$scope.contractsbyMe = [];
	$scope.contractNeedApproval = [];
	
	$scope.getAllContracts = function(){
		dashboardService.loadMyContracts()
		.then(
				function(response) {
					if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
						
						$scope.contractsbyMe = response.data.successResponse;
						toastr.success(
								'Contract(s) loaded successfully!!',
								ABF_CONSTANTS.INFO_HEADER);					
					} else {
						toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
					}
				},function(error){
					toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
				});
		
	};
	
	$scope.editContract= function(contract){
		DataSetService.currContract = contract;
		DataSetService.editContract = true;
		$location.path("/createContract");
	};
	
	$scope.showContract = function(contract){
		
		DataSetService.currContract = contract;
		DataSetService.startContract = moment(contract.contractStartDate, "YYYY-MM-DD");
		DataSetService.endContract = moment(contract.contractEndDate, "YYYY-MM-DD");
		$location.path('/hours');
	}
	
	$scope.getAllContracts();
}