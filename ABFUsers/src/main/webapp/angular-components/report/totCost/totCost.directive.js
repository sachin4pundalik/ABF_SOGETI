webappApp.directive('totCost', function () {
    return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment         
        scope: {
            //@ reads the attribute value, = provides two-way binding, & works with functions
            totCostHours: '@'         },
        templateUrl: './angular-components/report/totCost/totCostTmpl.html',
        controller: 'totCostCtrl', //Embed a custom controller in the directive
        link: function ($scope, element, attrs) { } //DOM manipulation
    }
});