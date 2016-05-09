webappApp.controller('MasterOffshorePriceCtrl', [ '$scope', '$location',
                                         'toastr','dataSetService', 'masterDataService', 'ABF_CONSTANTS', MasterOffshorePriceCtrl_Fn ]);

 function MasterOffshorePriceCtrl_Fn($scope, $location,toastr, dataSetService, masterDataService, ABF_CONSTANTS){
 	
 	$scope.offshoreprices = dataSetService.offshoreprices;
 	$scope.blines = dataSetService.blines;
 	$scope.bands = dataSetService.bands;
 	$scope.stayTypes= dataSetService.stayTypes;
 	
 	$scope.price= {
 			offshorepriceId:'0',
 			description:'',
 			price:'',
 			businessLineId:'-1',
 			businessLineName:'',
 			bandId:'-1',
 			bandName:'',
 			stayTypeId:'-1',
 			stayTypeName:'',
 			lastUpdatedBy:dataSetService.loggedInUser.loginId
 	};
 	
 	$scope.currentView='';
 	
 	$scope.getoffshoreprices = function (){
 		var url = './offshorePrice/all'; //'./json/roles.json';
 		
 		masterDataService.fetchAll(url)
 		.then(function(response){
 			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 				console.log("Success : " + JSON.stringify(response));
 				$scope.offshoreprices = dataSetService.offshoreprices = response.data.successResponse;
 				$scope.goBack();
 				toastr.info("Offshore price updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.OFFSHORE_PRICES);
 			}else{
 				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
 			}
 		}, function(error){
 			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 			console.log(JSON.stringify(error));
 		});
 	};
 	
 	$scope.newprice = function(){
 		$scope.currentView ="new";
 		$scope.reset();
 	}
 	
 	$scope.update=function(){
 		masterDataService.update('./offshorePrice/update', $scope.price)
 		.then(function(response){
 			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 				
 				$scope.goBack();
 				$scope.getoffshoreprices();
 				toastr.info("Offshore price updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.OFFSHORE_PRICES);
 			}else{
 				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
 			}
 		}, function(error){
 			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 			console.log(JSON.stringify(error));
 		});
 	}
 	
 	$scope.save= function(){
 		
 		masterDataService.save('./offshorePrice/create', $scope.price)
 		.then(function(response){
 			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 				
 				$scope.goBack();
 				$scope.getoffshoreprices();
 				toastr.info("Offshore price updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.OFFSHORE_PRICES);
 			}else{
 				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
 			}
 		}, function(error){
 			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 			console.log(JSON.stringify(error));
 		});
 	};
 	
 	$scope.edit= function(priceId){
 		$scope.currentView = 'edit';
 		$scope.getprice(priceId);
 	};
 	
 	$scope.getprice = function ( priceId ){
 		masterDataService.fetch('./offshorePrice/find/', priceId)
 		.then(function(response){
 			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 				console.log("Success : " + JSON.stringify(response));
 				$scope.price=response.data.successResponse;
 				toastr.info("Offshore price is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.OFFSHORE_PRICES);
 				
 			}else{
 				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
 			}
 		}, function(error){
 			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 			console.log(JSON.stringify(error));
 		});
 	}
 	$scope.remove=function(priceId){
 		masterDataService.remove('./offshorePrice/delete/', priceId)
 		.then(function(response){
 			$scope.getoffshoreprices();
 			toastr.success("Offshore price is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.OFFSHORE_PRICES);
 		}, function(error){
 			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 			console.log(JSON.stringify(error));
 		});
 	};
 	
 	$scope.reset= function(){
 		
 		$scope.price= {
 	 			offshorepriceId:'0',
 	 			description:'',
 	 			price:'50',
 	 			businessLineId:'-1',
 	 			businessLineName:'',
 	 			bandId:'-1',
 	 			bandName:'',
 	 			stayTypeId:'-1',
 	 			stayTypeName:'',
 	 			lastUpdatedBy:dataSetService.loggedInUser.loginId
 	 	};
 	};
 	
 	$scope.goBack = function(){
 		
 		$scope.currentView ='';
 		$scope.reset();
 	}
 	
 	$scope.getoffshoreprices();
 	
 	masterDataService.fetchAll('./businessline/all')
 	.then(function(response){
 		if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 			$scope.blines = dataSetService.blines = response.data.successResponse;
 		}else{
 			toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
 		}
 	}, function(error){
 		toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 		console.log(JSON.stringify(error));
 	});
 	
 	masterDataService.fetchAll('./band/all')
 	.then(function(response){
 		if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 			$scope.bands = dataSetService.bands = response.data.successResponse;
 		}else{
 			toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
 		}
 	}, function(error){
 		toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 		console.log(JSON.stringify(error));
 	});
 	
 	masterDataService.fetchAll('./stayType/all')
 	.then(function(response){
 		if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 			console.log("Success : " + JSON.stringify(response));
 			$scope.stayTypes = dataSetService.stayTypes = response.data.successResponse;
 		}else{
 			toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
 		}
 	}, function(error){
 		toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 		console.log(JSON.stringify(error));
 	});
 	
 }