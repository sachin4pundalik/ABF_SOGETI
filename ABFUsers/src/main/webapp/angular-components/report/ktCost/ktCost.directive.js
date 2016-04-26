webappApp.directive('ktCost', function () {
    return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment         
        scope: {
            //@ reads the attribute value, = provides two-way binding, & works with functions
            ktCostHours: '@'         },
        templateUrl: './angular-components/report/ktCost/ktCostTmpl.html',
        controller: 'ktCostCtrl', //Embed a custom controller in the directive
        link: function ($scope, element, attrs) { } //DOM manipulation
    }
});