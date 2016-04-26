webappApp.directive('abfHeader', function(){
	return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment         
        
        templateUrl: './angular-components/abf-header/abfHeaderTmpl.html',
        controller: 'abfHeaderCtrl', //Embed a custom controller in the directive
        link: function ($scope, element, attrs) { } //DOM manipulation
    }
});