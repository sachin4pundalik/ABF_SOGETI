webappApp.directive('fixedCost', function () {
    return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment         
        
        templateUrl: './angular-components/report/fixedCost/fixedCostTmpl.html',
        controller: 'fixedCostCtrl', //Embed a custom controller in the directive
        link: function ($scope, element, attrs) {
        	//var hoursList = fixedCostHours();
        } //DOM manipulation
    }
});