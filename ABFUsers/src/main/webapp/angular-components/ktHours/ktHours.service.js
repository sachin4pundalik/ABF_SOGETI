
webappApp.service('ktHoursService', ['$http', ktHoursService_fn]);

function ktHoursService_fn($http) {
	var ktHoursService = this;
	
	ktHoursService.hours= [];
	ktHoursService.monthlyHours= [];
	
	ktHoursService.setKtHours= function(hoursItems){
		ktHoursService.hours = hoursItems;
		ktHoursService.setKtMontlyHours();
	};
	
	ktHoursService.getKtHours= function(hoursItems){
		return ktHoursService.hours;
	};
	
	ktHoursService.getKtMontlyHours= function(){
		return ktHoursService.monthlyHours;
	};
	
	ktHoursService.setKtMontlyHours= function(){
		ktHoursService.monthlyHours= [];
		for( var i =0; i< ktHoursService.hours.length; i++){
			var hour = ktHoursService.hours[i];
			var monthly = {projectType:hour.projectType, 
					jan: (hour.w1+hour.w2+hour.w3+hour.w4),
					feb: (hour.w5+hour.w6+hour.w7+hour.w8),
					mar: (hour.w9+hour.w10+hour.w11+hour.w12)
					};
			console.log(JSON.stringify(monthly));
			ktHoursService.monthlyHours.push(monthly);
		};
	};
	
	ktHoursService.getProjectType = function(){
		
	}
	
	ktHoursService.getProjectRole = function(projType){
		
	} 
	
	ktHoursService.getGrade = function(projType, projRole){
		
	}
	
	ktHoursService.getPrice = function (projType, projRole, grade){
		
	}
	
}