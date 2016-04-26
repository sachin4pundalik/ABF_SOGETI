
webappApp.controller('fixedHoursCtrl', ['$scope','$http','fixedHoursService', 'toastr', fixedHoursCtrl_fn]);

function fixedHoursCtrl_fn($scope,$http, fixedHoursService, toastr) {
	
	$scope.fixedProjectHoursList = [];
	$scope.selectedFixedTypes = [];
	
	$scope.fixedHoursDataSet = [
								{id:1, name:"Setup Service Desk"},
								{id:2, name:"Setup ITMS Tooling"},
								{id:3, name:"ITMS tooling use"},
								{id:4, name:"Service Desk Tickets"},
								{id:5, name:"Travel Expense KT"},
								{id:6, name:"Risks"}
								];
	
	$scope.addFixedHourBilling = function (){
		$scope.resetFixedHours();
		
		if(angular.isDefined($scope.selectedFixedTypes) && $scope.selectedFixedTypes.length > 0){
			angular.forEach($scope.selectedFixedTypes, function (value){
				$scope.fixedProjectHoursList.push({typeName:value, typeValue:0});
			});
		}else{
			toastr.error("Choose atleast 1 option. ", "Validation");
		}
	};
	
	$scope.removeItem = function(fixedHour){
			_.remove($scope.fixedProjectHoursList, function(currentObject) {
			    return currentObject.typeName === fixedHour.typeName;
			}); 
			toastr.success("Option \""+fixedHour.typeName+"\" removed!", "Message");
	}
	
	$scope.resetFixedHours= function(){
		
		if(angular.isDefined($scope.fixedProjectHoursList) && $scope.fixedProjectHoursList.length > 0){
			$scope.fixedProjectHoursList= [];
			toastr.info("Reset is done. Please select atleast 1 option to save.", "Message");
		}
	}
	
	$scope.saveFixedHours= function (){
		if(angular.isDefined($scope.fixedProjectHoursList) && $scope.fixedProjectHoursList.length > 0){
			angular.forEach($scope.selectedFixedTypes, function (value){
				$scope.fixedProjectHoursList.push({typeName:value, typeValue:0});
			});
			toastr.info("Reset is done. Please select atleast 1 option to save.", "Message");
		}
		fixedHoursService.setFixedHours( $scope.fixedProjectHoursList );
	};
}