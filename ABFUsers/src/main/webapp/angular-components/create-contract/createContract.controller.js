'use strict';

webappApp.controller('createContractCtrl', ['$scope','MasterDataService', 'DataSetService', '$location', 'Session', 'toastr','ABF_CONSTANTS', createContractCtrl_Fn]);

	
function createContractCtrl_Fn($scope, MasterDataService, DataSetService, $location, Session, toastr, ABF_CONSTANTS) { 
	
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
	
	if(DataSetService.editContract){
		
	}
	
	$scope.contract = DataSetService.editContract?DataSetService.currContract:$scope.contract;
	
	$scope.fromDt = {
			minDate:moment().add(-7, 'day').format("YYYY-MM-DD"),
			maxDate:moment().add(1, 'year').format("YYYY-MM-DD")
	};
	
	$scope.toDt = {
			minDate:moment().add(2, 'day').format("YYYY-MM-DD"),
			maxDate:moment().add(2, 'year').format("YYYY-MM-DD")
	};
	
	$scope.startDtChanged = function(){
		
		if(angular.isObject($scope.contract.contractStartDate)){
			var dt = $scope.contract.contractStartDate;
			
			$scope.toDt = {
					minDate:moment([dt.getFullYear(), (dt.getMonth()+1), dt.getDate()]).add(2, 'day').format("YYYY-MM-DD"),
					maxDate:moment().add(2, 'year').format("YYYY-MM-DD")
			};
		}
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
		if(DataSetService.editContract ){
			$scope.updateContract();
		}else{
			MasterDataService
			.save('./contract/create', $scope.contract)
			.then(
					function(response) {
						
						if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
							resetContract();
							toastr.success('Contract created successfully!!',ABF_CONSTANTS.INFO_HEADER);
							$location.path('/landing');

						} else {
							throw new Error(response.data.failureResponse+"\n"+ABF_CONSTANTS.FAILURE_MESSAGE);
						}
					},
					function(error) {
						throw new Error(error.message+"\n"+ABF_CONSTANTS.FAILURE_MESSAGE);
					});
		}
		
	};
	
	$scope.goBack = function(){
		
		$location.path('/landing');
	};
	
	$scope.updateContract= function(){
		
		MasterDataService
			.update('./contract/update', $scope.contract)
			.then(
				function(response) {
					
					if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
						DataSetService.editContract =false;
						resetContract();
						$location.path('/landing');
					} else {
						throw new Error(response.data.failureResponse+"\n"+ABF_CONSTANTS.FAILURE_MESSAGE);
					}
				},
				function(error) {
					throw new Error(error.message+"\n"+ABF_CONSTANTS.FAILURE_MESSAGE);
				});
	};
	
	$scope.bookHours = function() {
		
		MasterDataService
				.save('./contract/create', $scope.contract)
				.then(
						function(response) {
							resetContract();
							toastr.success('Contract created successfully!!', ABF_CONSTANTS.INFO_HEADER);
							$location.path('/hours');
						},
						function(error) {
							throw new Error(error.message+"\n"+ABF_CONSTANTS.FAILURE_MESSAGE);
						});
		
	}	
}