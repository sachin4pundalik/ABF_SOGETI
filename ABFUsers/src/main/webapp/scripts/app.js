'use strict';

var webappApp = angular.module('webappApp', ['ngRoute', 'ngResource','xeditable','ui.bootstrap','ngCookies','webappApp.directives','ngAnimate', 'toastr']);
webappApp.run(function(editableOptions) {	  
	editableOptions.theme = 'bs2';
});

angular.module('webappApp.directives', [])
.directive('pwCheck', [function () {
return {
    require: 'ngModel',
    link: function (scope, elem, attrs, ctrl) {
        var firstPassword = '#' + attrs.pwCheck;
        elem.add(firstPassword).on('keyup', function () {
            scope.$apply(function () {
                // console.info(elem.val() === $(firstPassword).val());
                ctrl.$setValidity('pwmatch', elem.val() === $(firstPassword).val());
            });
        });
    }
}
}]);

webappApp.constant("ABF_CONSTANTS", {
	"FAILURE_HEADER":"Failure Message",
	"INFO_HEADER":"Message",
	
	"SUCCESS":"success",
	"FAILURE":"failure",
    "FAILURE_MESSAGE":"Unable to perform operation!!",
    
    "MASTER_DATA": "Master Data - ",
    "RESOURCE_TYPES":"Resource Type(s)",
    "BANDS": "Band(s)",
    "GRADES": "Grade(s)",
    "ROLES": "Role(s)",
    "USER_ROLE":"User Role(s)",
    "ONSHORE_PRICES": "Onshore Price(s)",
    "OFFSHORE_PRICES": "Offshore Price(s)",
    "STAY_TYPE": "Stay Type(s)",
    "STATUS": "Status(s)",
    "SKILLS": "Skill(s)",
    "BUSINESS_LINES": "Business Line(s)",
    "FIXED_HOURS": "Fixed Hour(s)"
});
/*webappApp.config(function(toastrConfig) {
	  angular.extend(toastrConfig, {
	    allowHtml: false,
	    autoDismiss: false,
	    closeButton: false,
	    closeHtml: '<button>&times;</button>',
	    containerId: 'toast-container',
	    extendedTimeOut: 1000,
	    iconClasses: {
	      error: 'toast-error',
	      info: 'toast-info',
	      success: 'toast-success',
	      warning: 'toast-warning'
	    },
	    maxOpened: 0,    
	    messageClass: 'toast-message',
	    newestOnTop: true,
	    onHidden: null,
	    onShown: null,
	    onTap: null,
	    positionClass: 'toast-top-right',
	    preventDuplicates: false,
	    preventOpenDuplicates: false,
	    progressBar: false,
	    tapToDismiss: true,
	    target: 'body',
	    templates: {
		  toast: 'directives/toast/toast.html',
		  progressbar: 'directives/progressbar/progressbar.html'
		},
	    timeOut: 5000,
	    titleClass: 'toast-title',
	    toastClass: 'toast'
	  });
	});*/
webappApp.config([ '$routeProvider', 'toastrConfig', function($routeProvider, toastrConfig){
  	$routeProvider.when('/landing', {
		templateUrl : 'angular-components/dashboard/dashboard.html',
		controller : 'dashboardCtrl'
	}).when('/login', {
		templateUrl : 'angular-components/login/login.html',
		controller : 'loginCtrl'
	}).when('/logout', {
    	templateUrl : 'angular-components/login/login.html',
		controller : 'logoutCtrl'
	}).when('/changePassword', {
    	templateUrl : 'angular-components/changepwd/changePwd.html',
		controller : 'changePasswordCtrl'
	})
	.when('/hours', {
    	templateUrl : 'angular-components/amHours/resources.html',
		controller : 'amHoursCtrl'
	})
	.when('/kt', {
    	templateUrl : 'angular-components/ktHours/ktHours.html',
		controller : 'ktHoursCtrl'
	})
	.when('/fixed', {
    	templateUrl : 'angular-components/fixedHours/fixedHours.html',
		controller : 'fixedHoursCtrl'
	})
	.when('/report', {
    	templateUrl : 'angular-components/report/report.html',
		controller : ''
	})
	.when('/createContract', {
    	templateUrl : 'angular-components/create-contract/create-contract.html',
		controller : 'createContractCtrl'
	})
	//Master data settings start
	.when('/mrtype', {
    	templateUrl : 'angular-components/master/resourcetype/resourcetype.html',
		controller : 'MasterResourceTypeCtrl'
	})
	.when('/mskill', {
    	templateUrl : 'angular-components/master/skill/skill.html',
		controller : 'MasterSkillCtrl'
	})
	.when('/mband', {
    	templateUrl : 'angular-components/master/band/band.html',
		controller : 'MasterBandCtrl'
	})
	.when('/mbline', {
    	templateUrl : 'angular-components/master/bline/bline.html',
		controller : 'MasterBlineCtrl'
	})
	.when('/mrole', {
    	templateUrl : 'angular-components/master/role/role.html',
		controller : 'MasterRoleCtrl'
	})
	.when('/mgrade', {
    	templateUrl : 'angular-components/master/grade/grade.html',
		controller : 'MasterGradeCtrl'
	})
	.when('/mstatus', {
    	templateUrl : 'angular-components/master/status/status.html',
		controller : 'MasterApprovalStatusCtrl'
	})
	.when('/mstay', {
    	templateUrl : 'angular-components/master/stay/stay.html',
		controller : 'MasterStayCtrl'
	})
	.when('/monshoreprice', {
    	templateUrl : 'angular-components/master/onshoreprice/onshoreprice.html',
		controller : 'MasterOnshorePriceCtrl'
	})
	.when('/moffshoreprice', {
    	templateUrl : 'angular-components/master/offshoreprice/offshoreprice.html',
		controller : 'MasterOffshorePriceCtrl'
	})
	.when('/mfixedhours', {
    	templateUrl : 'angular-components/master/fixedhours/fixedhours.html',
		controller : 'MasterFixedHourCtrl'
	})
	.when('/muserrole', {
    	templateUrl : 'angular-components/master/userrole/userrole.html',
		controller : 'MasterUserRoleCtrl'
	})
	//Master data setting end
	.otherwise({
		redirectTo : '/login'
	});
  	/*
  	 	toast-top-right (Default)
		toast-top-center
		toast-top-left
		toast-top-full-width
		toast-bottom-right
		toast-bottom-center
		toast-bottom-left
		toast-bottom-full-width 
  	 */
  	//toastr configuration
  	/*angular.extend(toastrConfig, {
	   
	    positionClass: 'toast-bottom-full-width',
	    progressBar: true
	   
	  });*/
} ])
.run (['$rootScope', '$location',  '$http', '$cookieStore', 
           function ($rootScope, $location,  $http ,$cookieStore ){
$rootScope.$on('$routeChangeStart', function (event, next, current) {

	// to block unauthorized user and handle page refresh
	 if($cookieStore.get('loggedin') == null || $cookieStore.get('loggedin') == "")
	 {
		 $location.path('/login');
	 }
	
	 // for the menus
	 if ($location.path() == "" || $location.path()  == '/login'  || $location.path()  == '/logout') {
		  $rootScope.isVisible = false;
	   }
	  else  {
		  $rootScope.isVisible = true;
	  }

});
}]);


