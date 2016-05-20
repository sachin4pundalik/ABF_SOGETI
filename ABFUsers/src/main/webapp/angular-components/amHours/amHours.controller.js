webappApp.controller('amHoursCtrl', [ '$scope', '$location', 'masterDataService', 'toastr',
		'DataSetService', 'Session', 'ABF_CONSTANTS', amHoursCtrl_fn ]);

function amHoursCtrl_fn($scope, $location, masterDataService, toastr, DataSetService, Session, ABF_CONSTANTS) {
	
	$scope.contract = DataSetService.currContract;
	$scope.currentUser = Session.sessionUser;
	
	$scope.isCollapsed= true;
	
	$scope.changeCollapse=function(){
		$scope.isCollapsed = !$scope.isCollapsed;
		
	};
	
	$scope.initDataSet= function(){
		
		try{
			
			DataSetService.fetchResourceTypes();
			DataSetService.fetchSkills();
			DataSetService.fetchRoles();
			DataSetService.fetchGrades();
			DataSetService.fetchStayTypes();
			DataSetService.fetchBands();

			getMonthHeaders();
			
			$scope.fetchExistingResources();
		}catch (e) {
			toastr.error(e, ABF_CONSTANTS.FAILURE_HEADER);
		}
		
	};
	
	$scope.resources = [];
	
	// to maintain views
	$scope.views='';
	/* = {
			showResource : true,
			showMonthly : false,
			showWeekly : false
	};*/
	
	$scope.manageView = function (showView){
		
		$scope.views= showView;
	};
	
	//Start and end months to consider
	$scope.startMonth = moment();
	var eMonth = moment().add(12, 'month');
	$scope.endMonth =  eMonth;
	
	$scope.rTypes = DataSetService.getResourceTypes();
	$scope.blines = DataSetService.getBusinessLines();
	$scope.roles = DataSetService.getRoles();
	$scope.grades = DataSetService.getGrades();
	$scope.stays = DataSetService.getStayTypes();
	$scope.bands = DataSetService.getBands();
	$scope.skills = DataSetService.getSkills();
	
	$scope.fetchExistingResources = function(){
		try {
			if (angular.isObject($scope.contract) && $scope.contract.contractId > 0) {
				let url = "./amhours/fetchcontractamhours/"+$scope.contract.contractId;
				masterDataService.fetchAll(url).then(
						function(response) {
							var resData = response.data;
							if (angular.equals(resData.status,ABF_CONSTANTS.SUCCESS)) {
								$scope.resources = resData.successResponse;
							} else if (angular.equals(resData.status, ABF_CONSTANTS.FAILURE)) {
								toastr.info(resData.failureResponse);
							}
						},
						function(error) {
							throw new Error(error.status + " " + error.statusText);
						});
			}
		}catch(e){
			toastr.error(e.message, ABF_CONSTANTS_FAILURE_HEADER);
		}
	};
	
	$scope.getPrice = function (){
		
		try{
			var rType = JSON.parse($scope.resource["resourceType"]);
			var bline = JSON.parse($scope.resource["businessLine"]);
			
			var url = '';
			if(rType && bline){
				if(angular.equals(rType.resourceType, ABF_CONSTANTS.ONSHORE)){
					var role = JSON.parse($scope.resource["role"]);
					var grade = JSON.parse($scope.resource["grade"]);
					if(role && grade){
						url="./onshorePrice/find/"+bline.businesslineId+"/"+role.roleId+"/"+grade.gradeId;  //'{bline}/{role}/{grade}";
					}else{
						throw new Error("Please complete selection");
					}
				}else if(angular.equals(rType.resourceType, ABF_CONSTANTS.OFFSHORE)){
					var band = JSON.parse($scope.resource["band"]);
					var stay = JSON.parse($scope.resource["stayType"]);
					if(band && stay){
						url="./offshorePrice/find/"+bline.businesslineId+"/"+band.bandId+"/"+stay.stayTypeId; //{bline}/{band}/{stayType}";
					}else{
						throw new Error("Please complete selection");
					}	
				}
				
				masterDataService.fetch(url, '')
					.then(function(response){
						var resData = response.data;
						if(angular.equals(resData.status, ABF_CONSTANTS.SUCCESS)){
							var item= resData.successResponse;
							$scope.resource.price = item.price || 0;
							$scope.resource.onShorePrice=item.onshorepriceId || -1;
							$scope.resource.offShorePrice=item.offshorepriceId || -1;
							
						}else if(angular.equals(resData.status, ABF_CONSTANTS.FAILURE)){
							toastr.info(resData.failureResponse);
						}
				}, function(error){
					throw new Error(error.status+" "+error.statusText);
				});
			}else{
				throw new Error("Please complete selection");
			}
			
		}catch(e){
			toastr.error(e.message, ABF_CONSTANTS.FAILURE_HEADER);
		}
	};
	
	$scope.goBack = function(){
		$location.path("/landing");
	};
	
	$scope.week = {
			weeklyTmpl:'',
			monthIndex : 0,
			currMonth: '',
			totals: {
				w1Tot:0,
				w2Tot:0,
				w3Tot:0,
				w4Tot:0
			}
	};
	
	
	$scope.openWeek = function (monthNm) {
		$scope.week.currMonth= monthNm;
		$scope.week.monthIndex = _.findIndex($scope.resources[0].months.month, function(m) { return m.name === $scope.week.currMonth; });
		$scope.week.weeklyTmpl = "./angular-components/amHours/weeklyView.html";
		$scope.views="showWeekly"; //= true;
	};
	
	$scope.resetHours = function (){
		
		angular.forEach($scope.resources, function(resource, key) {
			resource.months.month[$scope.week.monthIndex].w1= 0;
			resource.months.month[$scope.week.monthIndex].w2= 0;
			resource.months.month[$scope.week.monthIndex].w3= 0;
			resource.months.month[$scope.week.monthIndex].w4= 0;
			
			resource.months.month[$scope.week.monthIndex].total = 0;
			});
	};
	
	$scope.resetResource = function (){
		
		$scope.resource = {amContractResourceId:0, contractId:-1, resourceType:null, businessLine:null, skill:null, band:null, role:null, grade:null, stayType:null, price:0, onShorePrice:-1, offShorePrice:-1, months:{month:[]}};		
		
	};
	
	$scope.calculateWeekTotal = function(){
		
		angular.forEach($scope.resources, function(resource, key) {
			var price = _.toNumber(resource.price || 1);
			var w1 = _.toNumber(resource.months.month[$scope.week.monthIndex].weeks.w1 || 0);
			var w2 = _.toNumber(resource.months.month[$scope.week.monthIndex].weeks.w2 || 0);
			var w3 = _.toNumber(resource.months.month[$scope.week.monthIndex].weeks.w3 || 0);
			var w4 = _.toNumber(resource.months.month[$scope.week.monthIndex].weeks.w4 || 0);
			var resWeekTotal = validateNumber(w1)+validateNumber(w2)+validateNumber(w3)+validateNumber(w4);
			resource.months.month[$scope.week.monthIndex].total = (price * resWeekTotal);
			});
	}
	  
	$scope.copy2All = function (){
		try{
			angular.forEach($scope.resources, function(resource, key) {
				var price = _.toNumber(resource.price || 1);
				var w1 = _.toNumber(resource.months.month[$scope.week.monthIndex].weeks.w1 || 0);
				var w2 = _.toNumber(resource.months.month[$scope.week.monthIndex].weeks.w2 || 0);
				var w3 = _.toNumber(resource.months.month[$scope.week.monthIndex].weeks.w3 || 0);
				var w4 = _.toNumber(resource.months.month[$scope.week.monthIndex].weeks.w4 || 0);
				var resWeekTotal = validateNumber(w1)+validateNumber(w2)+validateNumber(w3)+validateNumber(w4);
				resource.months.month[$scope.week.monthIndex].total = (price * resWeekTotal);
				
					angular.forEach(resource.months.month, function(month, key){
						if(month.name !== $scope.week.currMonth){
							month.weeks.w1= w1;
							month.weeks.w2= w2;
							month.weeks.w3= w3;
							month.weeks.w4= w4;
							month.total=(price * resWeekTotal);
						}
					});
				});
			$scope.views='showMonthly';
			toastr.info("Same details are copied to all month(s).", ABF_CONSTANTS.INFO_HEADER);
		}catch(e){
			toastr.error("Unable to copy content to all months", ABF_CONSTANTS.FAILURE_HEADER);
		}
	}
	
	$scope.headerMonths = [];
	function getMonthHeaders(){
		try{
			var start = DataSetService.startContract.get('month'); //moment().month(Number|String);
			var end = DataSetService.endContract.get('month');
			var noofMonths = moment(DataSetService.endContract).diff(moment(DataSetService.startContract), 'months', true);
			//console.log('Months diff '+ noofMonths+ ' <> start '+start+ ' <> end '+end);
			for(var i=start; i<(noofMonths+start);i++ ){
				var mName = moment().month(i).format('MMM')+" "+ moment().month(i).format('YY');
				$scope.headerMonths.push(mName);
			}
		}catch(e){
			$scope.goBack();
		}
	}
	
	
	$scope.resource = {amContractResourceId:0, contractId:-1, resourceType:null, businessLine:null, skill:null, band:null, role:null, grade:null, stayType:null, price:0, onShorePrice:-1, offShorePrice:-1, months:{month:[]}};		
	
	$scope.resourceChange= function(){
		
		try{
			let rType = JSON.parse($scope.resource["resourceType"]);
			
			if(angular.equals(rType.resourceType, ABF_CONSTANTS.ONSHORE)){
				let url = './businessline/resourceType/$1';
				url = url.replace("$1", rType.resourcetypeId);

				DataSetService.fetchBusinessLines(url)
				.then(function(response){
					$scope.blines = response
				});
			}else if(!angular.equals(rType.resourceType, ABF_CONSTANTS.OFFSHORE)){
				DataSetService.fetchBusinessLines('./businessline/all')
				.then(function(response){
					$scope.blines = response
				});
			}
			
		}catch(e){
			toastr.error(e,"Data Setup Issue");
		}
		
	};

	$scope.skillChange= function(){
		
		try{
			let skill= JSON.parse($scope.resource["skill"]);
			let rType = JSON.parse($scope.resource["resourceType"]);
			
			if(angular.equals(rType.resourceType, ABF_CONSTANTS.OFFSHORE)){
				
				let url = './businessline/resourceType/$1/skill/$2';
				
				url = url.replace("$1", rType.resourcetypeId);
				url = url.replace("$2", skill.skillId);
				
				DataSetService.fetchBusinessLines(url)
				.then(function(response){
					$scope.blines = response
				});
			}
			
		}catch(e){
			throw new Error(e);
		}
		
	};
	
	$scope.newResource = function (){
		
		//First check : price should never be less or equal to 0 zero.
		if(angular.isNumber($scope.resource.price) && $scope.resource.price > 0){
			// reset all string json format to json object.
			for(prop in $scope.resource){
				if(angular.isString($scope.resource[prop])){
					$scope.resource[prop] = JSON.parse($scope.resource[prop]);
				}
			}
			//Specific to contractId
			$scope.resource.contractId=$scope.contract.contractId;
			for(month in $scope.headerMonths ){
				var month = {name:$scope.headerMonths[month], total:0, weeks:{w1:0, w2:0, w3:0, w4:0}};			
				//append to main resource obj
				$scope.resource.months.month.push(month);
			}
			$scope.resources.push($scope.resource);
			$scope.resetResource();
		}else{
			throw new Error("Please select combination for a valid price.");
		}
		
	}
	
	$scope.bookHours = function(){
		if(angular.isArray($scope.resources) && $scope.resources.length > 0){
			masterDataService.save('./amhours/create',$scope.resources)
			.then(function(response){
				var resData =  response.data;
				
				if(angular.equals(resData.status, ABF_CONSTANTS.SUCCESS)){
					//Back to default view.
					$scope.views="";
					$location.path("/fixed");
				}else if(angular.equals(resData.status, ABF_CONSTANTS.FAILURE)){
					toastr.error(resData.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
				}
			}, function(error){
				toastr.error(error, ABF_CONSTANTS.FAILURE_HEADER);
			});
		}else{
			toastr.warning("Please select atleast one resource for the contract.", ABF_CONSTANTS.INFO_HEADER);
		}
	}
	
	$scope.deleteResource= function (item){
		
		try{
			if(angular.isNumber(item.amContractResourceId) && item.amContractResourceId>0){
				masterDataService.remove(item.amContractResourceId)
				.then(function(response){
					var resData =  response.data;
					
					if(angular.equals(resData.status, ABF_CONSTANTS.SUCCESS)){
						_.remove($scope.resources, function(currentObject) {
						    return currentObject === item;
						}); 
						toastr.success("Delete resource from list.", "Resource Deletion");
						$scope.fetchExistingResources();
					}else if(angular.equals(resData.status, ABF_CONSTANTS.FAILURE)){
						toastr.error(resData.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
					}
					
				}, function(error){
					toastr.error(ABF_CONSTANTS.FAILURE_MESSAGE, ABF_CONSTANTS.FAILURE_HEADER);
				});
			}else{
				_.remove($scope.resources, function(currentObject) {
				    return currentObject === item;
				}); 
			}
			
		}catch(e){
			toastr.error("Unable to remove item from the list.","Resource Deletion");
		}
		
	}
	
	function validateNumber( value ){
		if(angular.isNumber(value)){
			return value;
		}else {
			return 0;
		}
	}

}