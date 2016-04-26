'use strict';

webappApp.controller('createContractCtrl', ['$scope','createContractService', '$location', 'dataSetService', createContractCtrl_Fn]);

	
function createContractCtrl_Fn($scope, createContractService, $location, dataSetService) { 
	
	$scope.projectTypes = dataSetService.projectTypes;
	
	$scope.contract = {
			typeOfProj : '',
			customerName : '',
			projectName : '',
			from : '',
			to : '',
			comments : ''
		};
	
	function resetContract(){
		$scope.contract = {
				typeOfProj : '',
				customerName : '',
				contractName : '',
				from : '',
				to : '',
				comments : ''
			};
	}
	
	$scope.createContract = function (){
		createContractService.createContract($scope.contract).then(function(response){
			$location.path('/landing');
			resetContract();
		});
	}
	
	$scope.bookHours = function() {
		createContractService.createContract($scope.contract).then(function(response){
			resetContract();
			$location.path('/hours');
		});
	}	
}