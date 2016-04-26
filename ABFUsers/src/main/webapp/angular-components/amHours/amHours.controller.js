webappApp.controller('amHoursCtrl', [ '$scope', '$http', 'amHoursService','toastr', 'dataSetService',
		amHoursCtrl_fn ]);

function amHoursCtrl_fn($scope, $http, amHoursService, toastr, dataSetService) {
	
	$scope.resources = [];
	
	// to maintain views
	$scope.views = {
			showResource : true,
			showMonthly : false,
			showWeekly : false
	};
	
	$scope.manageView = function (showView){
		
		for(var prop in $scope.views){
			if(prop === showView){
				$scope.views[prop] = true;
			}else{
				$scope.views[prop] = false;
			}
		} 
	};
	
	//Start and end months to consider
	$scope.startMonth = moment();
	var eMonth = moment().add(12, 'month');
	$scope.endMonth =  eMonth;
	
	$scope.resourceType = dataSetService.resourceType;
	$scope.blines = [{key:"Microsoft", value:"1"}, {key:"Java",value:"2"}, {key:"SAP",value:"3"}, {key:"Testing",value:"4"}];
	$scope.roles = [{key:"5", value:"5"},{key:"4", value:"4"},{key:"3", value:"3"},{key:"2", value:"2"},{key:"1", value:"1"}];
	$scope.grades = [{key:"5", value:"5"},{key:"4", value:"4"},{key:"3", value:"3"},{key:"2", value:"2"},{key:"1", value:"1"}];
	$scope.stays = [{key:"Standard", value:"1"},{key:"Long", value:"2"},{key:"Short", value:"1"}];
	$scope.bands = [{key:"P1", value:"1"},{key:"P2", value:"2"},{key:"P3", value:"3"},{key:"P4", value:"4"},{key:"P5", value:"5"}];
	$scope.skills = [{key:"AM", value:"1"},{key:"IS", value:"2"},{key:"Primary Skills", value:"1"}];
	
	$scope.getProjectTypes = function(){
		createContractService.getProjectTypes().then(function(response){
			$scope.projectTypes = response.data;
		});
	}
	
	$scope.getProjectRoles = function(item){
		createContractService.getProjectRoles(item.type).then(function(response){
			$scope.projectRoles = response.data;
		});
	};
	
	$scope.getGrades = function(item){
		createContractService.getGrades(item.type,item.role).then(function(response){
			$scope.grades = response.data;
		});
	};
	
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
		toastr.info("Message", "Heading");
		$scope.week.currMonth= monthNm;
		$scope.week.monthIndex = _.findIndex($scope.resources[0].months, function(m) { return m.month === $scope.week.currMonth; });
		$scope.week.weeklyTmpl = "./angular-components/amHours/weeklyView.html";
		$scope.views.showWeekly= true;
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
		
		$scope.resource = {id:'', type:'', bline:'', skill:'', band:'', role:'', grade:'', stay:'', price:10, months:[]};		
		console.log(angular.toJson($scope.resourc, true));
		angular.toJson($scope.resourc, true);
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
		
		var price = _.toNumber(resource.price || 1);
		var w1 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w1 || 0);
		var w2 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w2 || 0);
		var w3 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w3 || 0);
		var w4 = _.toNumber(resource.months[$scope.week.monthIndex].weeks.w4 || 0);
		var resWeekTotal = validateNumber(w1)+validateNumber(w2)+validateNumber(w3)+validateNumber(w4);
		resource.months[$scope.week.monthIndex].total = (price * resWeekTotal);
		
		angular.forEach($scope.resources, function(resource, key) {
				angular.forEach(resource.months, function(month, key){
					var w1 = _.toNumber(months[$scope.week.monthIndex].weeks.w1 || 0);
					var w2 = _.toNumber(months[$scope.week.monthIndex].weeks.w2 || 0);
					var w3 = _.toNumber(months[$scope.week.monthIndex].weeks.w3 || 0);
					var w4 = _.toNumber(months[$scope.week.monthIndex].weeks.w4 || 0);
					var resWeekTotal = validateNumber(w1)+validateNumber(w2)+validateNumber(w3)+validateNumber(w4);
					resource.months[$scope.week.monthIndex].total = resWeekTotal;
				});
			
			});
	}
	
	$scope.headerMonths = [];
	function getMonthHeaders(){
		var start = dataSetService.startContract.get('month'); //moment().month(Number|String);
		var end = dataSetService.endContract.get('month');
		var noofMonths = moment(dataSetService.endContract).diff(moment(dataSetService.startContract), 'months', true);
		console.log('Months diff '+ noofMonths+ ' <> start '+start+ ' <> end '+end);
		for(var i=start; i<(noofMonths+start);i++ ){
			var mName = moment().month(i).format('MMM')+" "+ moment().month(i).format('YY');
			$scope.headerMonths.push(mName);
		}
	}
	
	getMonthHeaders();
	
	$scope.resource = {id:'', type:'', bline:'', skill:'', band:'', role:'', grade:'', stay:'', price:0, months:[]};
	
	$scope.newResource = function (){
		
		for(month in $scope.headerMonths ){
			var month = {month:$scope.headerMonths[month], total:0, weeks:{w1:0, w2:4, w3:5, w4:8}};			
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
		
		//$scope.resources = _.dropWhile($scope.resources, item);
		
	}
	
	function validateNumber( value ){
		if(angular.isNumber(value)){
			return value;
		}else {
			return 0;
		}
	}

}