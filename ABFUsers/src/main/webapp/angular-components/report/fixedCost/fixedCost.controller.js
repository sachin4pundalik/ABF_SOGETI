webappApp.controller('fixedCostCtrl',['$scope','fixedHoursService', function($scope, fixedHoursService){
	
	 function init() {
		 $scope.fixedCostHours = fixedHoursService.getFixedHours();
     }
     
     init();
}]);