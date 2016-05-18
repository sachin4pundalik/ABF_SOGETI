webappApp.controller('MasterOffshorePriceCtrl', [ '$scope', '$location',
                                         'toastr','DataSetService', 'masterDataService', 'ABF_CONSTANTS', 'Session', MasterOffshorePriceCtrl_Fn ]);

 function MasterOffshorePriceCtrl_Fn($scope, $location,toastr, DataSetService, masterDataService, ABF_CONSTANTS, Session){
 	
 	$scope.offshoreprices = DataSetService.getOffshorePrices();
 	$scope.blines = DataSetService.getBusinessLines();
 	$scope.bands = DataSetService.getBands();
 	$scope.stayTypes= DataSetService.getStayTypes();
 	
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
 			lastUpdatedBy:Session.sessionUser.loginId
 	};
 	
 	$scope.currentView='';
 	
 	$scope.getoffshoreprices = function (){
 		var url = './offshorePrice/all'; //'./json/roles.json';
 		
 		masterDataService.fetchAll(url)
 		.then(function(response){
 			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 				$scope.offshoreprices = DataSetService.offshoreprices = response.data.successResponse;
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
 	 			lastUpdatedBy:Session.sessionUser.loginId
 	 	};
 	};
 	
 	$scope.goBack = function(){
 		
 		$scope.currentView ='';
 		$scope.reset();
 	}
 	
 	$scope.getoffshoreprices();
 	
 	masterDataService.fetchAll('./businessline/resourceType/2')
 	.then(function(response){
 		if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
 			$scope.blines = DataSetService.blines = response.data.successResponse;
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
 			$scope.bands = DataSetService.bands = response.data.successResponse;
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
 			$scope.stayTypes = DataSetService.stayTypes = response.data.successResponse;
 		}else{
 			toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
 		}
 	}, function(error){
 		toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
 		console.log(JSON.stringify(error));
 	});
 	
 }