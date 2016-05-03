'use strict';

webappApp.controller('createContractCtrl', ['$scope','createContractService', '$location', 'dataSetService', 'toastr',createContractCtrl_Fn]);

	
function createContractCtrl_Fn($scope, createContractService, $location, dataSetService, toastr) { 
	
	
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
	
	$scope.createContract = function (){
		toastr.options=dataSetService.errorAlertOptions;
		
		createContractService.createContract($scope.contract).then(function(response){

			resetContract();
			toastr.options=dataSetService.successAlertOptions;
			toastr.success('Contract created successfully!!','Message');
			$location.path('/landing');
		}, function(error){
			toastr.error('Unable to perform task, Please contact support.','Failure Message');
			console.error(JSON.stringify(error, null,2));
		});
	}
	$scope.goBack = function(){
		$location.path('/landing');
	};
	
	$scope.bookHours = function() {
		toastr.options=dataSetService.errorAlertOptions;
		
		createContractService.createContract($scope.contract).then(function(response){
			
			resetContract();
			toastr.options=dataSetService.successAlertOptions;
			toastr.success('Contract created successfully!!','Message');
			$location.path('/hours');
			
		}, function(error){
			toastr.error('Unable to perform task, Please contact support.','Failure Message');
			console.error(JSON.stringify(error, null,2));
		});
	}	
}