webappApp.controller('dashboardCtrl', ['$scope', 'dashboardService', 'toastr', 'ABF_CONSTANTS', 'dataSetService', '$location', dashboardCtrl_fn]);

function dashboardCtrl_fn($scope, dashboardService, toastr, ABF_CONSTANTS, dataSetService, $location){
	
	$scope.contractsbyMe = [];
	$scope.contractNeedApproval = [];
	
	$scope.getAllContracts = function(){
		toastr.options = dataSetService.errorAlertOptions;
		dashboardService.loadMyContracts()
		.then(
				function(response) {
					if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
						
						$scope.contractsbyMe = response.data.successResponse;
						toastr.options = dataSetService.successAlertOptions;
						toastr.success(
								'Contract(s) loaded successfully!!',
								'Message');					
					} else {
						toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
						console.error(JSON.stringify(error, null, 2));
					}
				},function(error){
					toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
					console.error(JSON.stringify(error, null, 2));
				});
		
	};
	
	$scope.showContract = function(contract){
		dataSetService.currContract = contract;
		$location.path('/fixed');
	}
	
	$scope.getAllContracts();
}