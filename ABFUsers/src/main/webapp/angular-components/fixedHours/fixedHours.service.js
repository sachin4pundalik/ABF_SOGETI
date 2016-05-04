
webappApp.service('fixedHoursService', ['$http', fixedHoursService_fn]);

function fixedHoursService_fn($http) {
	var fixedHoursService = this;
	
	fixedHoursService.hours= [];
	
	fixedHoursService.setFixedHours= function(hoursItems){
		fixedHoursService.hours = hoursItems;
	};
	
	fixedHoursService.getFixedCosts= function(){
		return $http.get('./fixedCost/all');
	};
	
	fixedHoursService.save=function(list){
		return $http.post('./fixedContract/create', list);
	}
	
}