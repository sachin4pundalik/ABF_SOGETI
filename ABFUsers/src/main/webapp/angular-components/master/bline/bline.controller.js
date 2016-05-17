webappApp.controller('MasterBlineCtrl', [ '$scope', '$location',
                                         'toastr','DataSetService', 'masterDataService', 'ABF_CONSTANTS', MasterBlineCtrl_Fn ]);

function MasterBlineCtrl_Fn($scope, $location,toastr, DataSetService, masterDataService, ABF_CONSTANTS){
	
	$scope.blines = DataSetService.getBusinessLines();
	$scope.rTypes = DataSetService.getResourceTypes();
	$scope.skills= DataSetService.getSkills();
	
	$scope.bline= {businesslineId:"",businesslineName:"",resourceTypeId:"-1",skillId:"-1"};
	
	$scope.currentView='';
	
	$scope.getblines = function (){
		var url = './businessline/all'; //'./json/roles.json';
		
		masterDataService.fetchAll(url)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.blines = DataSetService.blines = response.data.successResponse;
				$scope.goBack();
				toastr.info("Business line updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BUSINESS_LINES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.newBline = function(){
		$scope.currentView ="new";
		$scope.reset();
	}
	
	$scope.update=function(){
		masterDataService.update('./businessline/update', $scope.bline)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				
				$scope.goBack();
				$scope.getblines();
				toastr.info("Business line updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BUSINESS_LINES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.save= function(){
		
		masterDataService.save('./businessline/create', $scope.bline)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				
				$scope.goBack();
				$scope.getblines();
				toastr.info("Business line updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BUSINESS_LINES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.edit= function(blineId){
		$scope.currentView = 'edit';
		//Fetch resource types
		masterDataService.fetchAll('./resourcetype/all')
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.rTypes = response.data.successResponse;
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
		
		//fetch skills
		masterDataService.fetchAll('./skill/all')
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.skills =  response.data.successResponse;
				$scope.getbline(blineId);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
		
	};
	
	$scope.getbline = function ( blineId ){
		masterDataService.fetch('./businessline/find/', blineId)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.bline=response.data.successResponse;
				toastr.info("Business line is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BUSINESS_LINES);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.remove=function(blineId){
		masterDataService.remove('./businessline/delete/', blineId)
		.then(function(response){
			$scope.getblines();
			toastr.success("Business line is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.BUSINESS_LINES);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.reset= function(){
		$scope.bline= {businesslineId:"",businesslineName:"",resourceTypeId:"-1",skillId:"-1"};
	};
	
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.reset();
	}
	
	$scope.getblines();
	
}