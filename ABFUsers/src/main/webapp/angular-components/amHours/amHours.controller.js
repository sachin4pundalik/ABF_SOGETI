webappApp.controller('amHoursCtrl', [ '$scope', 'amHoursService', 'toastr',
		'DataSetService', 'Session', 'ABF_CONSTANTS', amHoursCtrl_fn ]);

function amHoursCtrl_fn($scope, amHoursService, toastr, DataSetService, Session, ABF_CONSTANTS) {
	
	$scope.contract = DataSetService.currContract;
	$scope.currentUser = Session.sessionUser;
	
	$scope.initDataSet= function(){
		
		DataSetService.fetchResourceTypes();
		DataSetService.fetchSkills();
		DataSetService.fetchRoles();
		DataSetService.fetchGrades();
		DataSetService.fetchStayTypes();
		DataSetService.fetchBands();

		getMonthHeaders();
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
	
	$scope.getPrice = function (){
		
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
		$scope.week.monthIndex = _.findIndex($scope.resources[0].months, function(m) { return m.month === $scope.week.currMonth; });
		$scope.week.weeklyTmpl = "./angular-components/amHours/weeklyView.html";
		$scope.views="showWeekly"; //= true;
	};
	
	$scope.resetHours = function (){
		
		angular.forEach($scope.resources, function(resource, key) {
			resource.months[$scope.week.monthIndex].w1= 0;
			resource.months[$scope.week.monthIndex].w2= 0;
			resource.months[$scope.week.monthIndex].w3= 0;
			resource.months[$scope.week.monthIndex].w4= 0;
			
			resource.months[$scope.week.monthIndex].total = 0;
			});
	};
	
	$scope.resetResource = function (){
		
		$scope.resource = {amContractResourceId:0, resourceType:null, businessLine:null, skill:null, band:null, role:null, grade:null, stayType:null, price:50, onShorePrice:120, offShorePrice:0, months:[]};		
		
	};
	
	$scope.calculateWeekTotal = function(){
		
		angular.forEach($scope.resources, function(resource, key) {
			var price = _.toNumber(resource.price || 1);
			var w1 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w1 || 0);
			var w2 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w2 || 0);
			var w3 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w3 || 0);
			var w4 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w4 || 0);
			var resWeekTotal = validateNumber(w1)+validateNumber(w2)+validateNumber(w3)+validateNumber(w4);
			resource.months[$scope.week.monthIndex].total = (price * resWeekTotal);
			});
	}
	  
	$scope.copy2All = function (){
		try{
			angular.forEach($scope.resources, function(resource, key) {
				var price = _.toNumber(resource.price || 1);
				var w1 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w1 || 0);
				var w2 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w2 || 0);
				var w3 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w3 || 0);
				var w4 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w4 || 0);
				var resWeekTotal = validateNumber(w1)+validateNumber(w2)+validateNumber(w3)+validateNumber(w4);
				resource.months[$scope.week.monthIndex].total = (price * resWeekTotal);
				
					angular.forEach(resource.months, function(month, key){
						if(month.month !== $scope.week.currMonth){
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
			console.log('Months diff '+ noofMonths+ ' <> start '+start+ ' <> end '+end);
			for(var i=start; i<(noofMonths+start);i++ ){
				var mName = moment().month(i).format('MMM')+" "+ moment().month(i).format('YY');
				$scope.headerMonths.push(mName);
			}
		}catch(e){
			toastr.error("No contract details available", ABF_CONSTANTS.FAILURE_HEADER);
		}
	}
	
	
	$scope.resource = {amContractResourceId:0, resourceType:null, businessLine:null, skill:null, band:null, role:null, grade:null, stayType:null, price:50, onShorePrice:120, offShorePrice:0, months:[]};		
	
	$scope.resourceChange= function(){
		
		try{
			let rType = JSON.parse($scope.resource["resourceType"]);
			
			if(angular.equals(rType.resourceType, ABF_CONSTANTS.OFFSHORE)){
				let url = './businessline/resourceType/$1';
				url = url.replace("$1", rType.resourcetypeId);

				DataSetService.fetchBusinessLines(url)
				.then(function(response){
					$scope.blines = response
				});
			}else if(!angular.equals(rType.resourceType, ABF_CONSTANTS.ONSHORE)){
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
			
			if(angular.equals(rType.resourceType, ABF_CONSTANTS.ONSHORE)){
				
				let url = './businessline/resourceType/$1/skill/$2';
				
				url = url.replace("$1", rType.resourcetypeId);
				url = url.replace("$2", skill.skillId);
				
				console.log("BLines URL : " + url);
				DataSetService.fetchBusinessLines(url)
				.then(function(response){
					$scope.blines = response
				});
			}
			
		}catch(e){
			toastr.error(e,"Data Setup Issue");
		}
		
	};
	
	$scope.newResource = function (){
		// reset all string json format to json object.
		for(prop in $scope.resource){
			if(angular.isString($scope.resource[prop])){
				$scope.resource[prop] = JSON.parse($scope.resource[prop]);
			}
		}
		
		for(month in $scope.headerMonths ){
			var month = {month:$scope.headerMonths[month], total:0, weeks:{w1:0, w2:0, w3:0, w4:0}};			
			//append to main resource obj
			$scope.resource.months.push(month);
		}
		
		$scope.resources.push($scope.resource);
		$scope.resetResource();
	}
	
	$scope.deleteResource= function (item){
		
		try{
			_.remove($scope.resources, function(currentObject) {
			    return currentObject === item;
			}); 
			toastr.success("Delete resource from list.", "Resource Deletion");
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