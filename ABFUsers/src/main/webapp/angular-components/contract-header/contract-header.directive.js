angular.module('webappApp').directive('contractHeader', function () {
    return {
        restrict: 'E', //E = element, A = attribute, C = class, M = comment         
        scope:{
        	contract:'=',
        	isCollapsed:'@collapse'
        },
        templateUrl: './angular-components/contract-header/contract-header.html',
        controller: ['$scope','$location', function($scope, $location) {
        	$scope.tabs = [
        	               { title:'AM Hours' },
        	               { title:'KT Hours'},
        	               { title:'Fixed Hours' }
        	             ];
        	
        	$scope.selectTab = function(index){
        		if(index=='0'){
        			$location.path('/hours');
        		}else if(index=='1'){
        			$location.path('/kt');
        		}else if(index=='2'){
        			$location.path('/fixed');
        		}
        	}
        
        }],
        link: function ($scope, element, attrs) { 
        	
        } //DOM manipulation
    }
});