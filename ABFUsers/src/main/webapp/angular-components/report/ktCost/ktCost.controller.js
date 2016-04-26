
webappApp.controller('ktCostCtrl', ['$scope', 'ktHoursService', function($scope, ktHoursService){
	
	function init() {
		 $scope.ktCostHours = ktHoursService.getKtMonthlyHours();
    }
    
    init();
	
}]);