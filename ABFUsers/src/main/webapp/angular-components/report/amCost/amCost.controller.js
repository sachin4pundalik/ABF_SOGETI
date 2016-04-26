webappApp.controller('amCostCtrl', ['$scope', 'amHoursService', function($scope, amHoursService){
	
	 function init() {
		 $scope.amCostHours = amHoursService.getAmMontlyHours();
     }
     
     init();
}]);