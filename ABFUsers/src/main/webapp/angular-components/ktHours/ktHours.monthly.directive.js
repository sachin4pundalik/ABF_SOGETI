webappApp.directive('ktMonthlyView', function () {
    return {
        restrict: 'E', //E = element, A = attribute, C = class, M = comment         
        scope:false,
        templateUrl: './angular-components/ktHours/monthlyView.html',
        link: function ($scope, element, attrs) { } //DOM manipulation
    }
});