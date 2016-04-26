webappApp.directive('amCost', function () {
    return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment         
       
        templateUrl: './angular-components/report/amCost/amCostTmpl.html',
        controller: 'amCostCtrl', //Embed a custom controller in the directive
        link: function ($scope, element, attrs) { } //DOM manipulation
    }
});