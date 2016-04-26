webappApp.controller('dashboardCtrl', ['$scope', 'dashboardService', 'toastr', dashboardCtrl_fn]);

function dashboardCtrl_fn($scope, dashboardService, toastr){
	
	$scope.contractsbyMe = [];
	$scope.contractNeedApproval = [];
	
	$scope.loadMyContracts = function (){
		
		//var promise = dashboardService.loadMyContracts();
		//promise.then(function(response) {
			var responseData = [
			                    {contractId:1, typeOfProj:'Onshore', contractName:'Sog_Ctract', customerName:'KPN', start_dt:'01/01/2016', end_dt:'31/03/2016', status:'Pending AM Hours' },
			                    {contractId:1, typeOfProj:'Offshore', contractName:'Cap_Ctract', customerName:'Philips', start_dt:'01/01/2016', end_dt:'31/03/2016', status:'Pending KT Hours' },
			                    {contractId:1, typeOfProj:'Onshore', contractName:'Cap_Ctract', customerName:'DSML', start_dt:'01/01/2016', end_dt:'31/03/2016', status:'Pending Fixed Hours' },
			                    {contractId:1, typeOfProj:'Offshore', contractName:'Sog_Ctract', customerName:'ASML', start_dt:'01/01/2016', end_dt:'31/03/2016', status:'Completed' },
			                    ];
			/*angular.forEach(response.data.successResponse, function(item){
				$scope.contractsbyMe.push(item);
			});*/
			
			angular.forEach(responseData, function(item){
				$scope.contractsbyMe.push(item);
			});
		/*	
		}, function(reason) {
			toastr.error('Failed: ' + reason, 'Failure Message');
		}, function(update) {
			toastr.error('Got notification: ' + update, 'Failure Message');
		});*/
	}
	
	$scope.loadMyContracts();
}