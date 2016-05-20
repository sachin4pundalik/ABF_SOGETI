angular.module('webappApp').directive('contractHeader', function () {
    return {
        restrict: 'E', //E = element, A = attribute, C = class, M = comment         
        scope:{
        	contract:'='
        },
        templateUrl: './angular-components/contract-header/contract-header.html',
        link: function ($scope, element, attrs) { } //DOM manipulation
    }
});