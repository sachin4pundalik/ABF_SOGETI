
webappApp.service('amHoursService', ['$http', amHoursService_fn]);

function amHoursService_fn($http) {
	var amHoursService = this;
	
	amHoursService.hours= [];
	amHoursService.monthlyHours= [];
	
	amHoursService.setAmHours= function(hoursItems){
		amHoursService.hours = hoursItems;
		amHoursService.setAmMontlyHours();
	};
	
	amHoursService.getAmHours= function(hoursItems){
		return amHoursService.hours;
	};
	
	amHoursService.getAmMontlyHours= function(){
		return amHoursService.monthlyHours;
	};
	
	amHoursService.setAmMontlyHours= function(){
		amHoursService.monthlyHours= [];
		for( var i =0; i< amHoursService.hours.length; i++){
			var hour = amHoursService.hours[i];
			var monthly = {projectType:hour.projectType, 
					jan: (hour.w1+hour.w2+hour.w3+hour.w4),
					feb: (hour.w5+hour.w6+hour.w7+hour.w8),
					mar: (hour.w9+hour.w10+hour.w11+hour.w12)
					};
			console.log(JSON.stringify(monthly));
			amHoursService.monthlyHours.push(monthly);
		};
	};
	
	amHoursService.getProjectType = function(){
		
	}
	
	amHoursService.getProjectRole = function(projType){
		
	} 
	
	amHoursService.getGrade = function(projType, projRole){
		
	}
	
	amHoursService.getPrice = function (projType, projRole, grade){
		
	}
	
}