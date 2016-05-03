webappApp.controller('MasterOffshorePriceCtrl', [ '$scope', '$location',
                                         'toastr','dataSetService', 'masterDataService', 'ABF_CONSTANTS', MasterOffshorePriceCtrl_Fn ]);

function MasterOffshorePriceCtrl_Fn($scope, $location,toastr, dataSetService, masterDataService, ABF_CONSTANTS){
	
	$scope.bands = dataSetService.bands;
	
	$scope.band= null;
	$scope.currentView='';
	
	$scope.getBands = function (){
		masterDataService.fetchAll('./masterdata/bands')
		.then(function(response){
			console.log("Success : " + JSON.stringify(response));
			dataSetService.bands = response;
			toastr.info("Bands updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.save= function(){
		
		masterDataService.save('./masterdata/band/save', $scope.band)
		.then(function(response){
			console.log("Success : " + JSON.stringify(response));
			dataSetService.bands = response;
			toastr.info("Bands updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
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
		masterDataService.fetch('./masterdata/band/', bandId)
		.then(function(response){
			console.log("Success : " + JSON.stringify(response));
			$scope.band=response;
			toastr.info("Band is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BANDS);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.band= null;
	}
}