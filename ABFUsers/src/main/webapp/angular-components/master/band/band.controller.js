webappApp.controller('MasterBandCtrl', [ '$scope', '$location',
                                         'toastr','DataSetService', 'MasterDataService', 'ABF_CONSTANTS', MasterBandCtrl_Fn ]);

function MasterBandCtrl_Fn($scope, $location,toastr, DataSetService, MasterDataService, ABF_CONSTANTS){
	
	$scope.bands = DataSetService.bands;
	
	$scope.band= {
			bandId:'',
			bandName:''
	};
	$scope.currentView='';
	
	$scope.getBands = function (){
		var url = './band/all';// './json/bands.json'; //'./masterdata/bands';
		
		MasterDataService.fetchAll(url)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.bands = DataSetService.bands = response.data.successResponse;
				$scope.goBack();
				toastr.info("Bands updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
		});
	};
	
	$scope.newBand = function(){
		$scope.currentView ="new";
	}
	
	$scope.update=function(){
		MasterDataService.update('./band/update', $scope.band)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getBands();
				$scope.goBack();
				toastr.info("Bands updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.save= function(){
		
		MasterDataService.save('./band/create', $scope.band)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getBands();
				$scope.goBack();
				toastr.success("Bands updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.edit= function(bandId){
		$scope.currentView = 'edit';
		$scope.getBand(bandId);
	};
	
	$scope.getBand = function ( bandId ){
		MasterDataService.fetch('./band/find/', bandId)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.band=response.data.successResponse;
				toastr.info("Band is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.remove=function(bandId){
		MasterDataService.remove('./band/delete/', bandId)
		.then(function(response){
			$scope.getBands();
			toastr.success("Band is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.band= {
				bandId:'',
				bandName:''
		};
	}
	
	$scope.getBands();
}