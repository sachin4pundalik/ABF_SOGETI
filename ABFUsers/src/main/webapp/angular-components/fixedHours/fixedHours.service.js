
webappApp.service('fixedHoursService', ['$http', fixedHoursService_fn]);

function fixedHoursService_fn($http) {
	var fixedHoursService = this;
	
	fixedHoursService.hours= [];
	
	fixedHoursService.setFixedHours= function(hoursItems){
		fixedHoursService.hours = hoursItems;
	};
	
	fixedHoursService.getFixedHours= function(hoursItems){
		return fixedHoursService.hours;
	};
	
}