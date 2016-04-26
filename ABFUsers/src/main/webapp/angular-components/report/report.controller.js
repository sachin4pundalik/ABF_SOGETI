webappApp.controller('reportCtrl', ['$scope','fixedHoursService', 'amHoursService','ktHoursService',  reportCtrl_fn]);

function reportCtrl_fn($scope, fixedHoursService, amHoursService,ktHoursService){
	
	$scope.fixedCostHours = fixedHoursService.getFixedHours();
	$scope.amCostHours = amHoursService.getAmMontlyHours();
	$scope.ktCostHours = ktHoursService.getKtMonthlyHours();
	
}