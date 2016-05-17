'use strict';

var webappApp = angular.module('webappApp', [ 'ngRoute', 'ngResource',
		'xeditable', 'ui.bootstrap', 'ngCookies', 'webappApp.directives',
		'ngAnimate', 'toastr' ]);
webappApp.run(function(editableOptions) {
	editableOptions.theme = 'bs2';
});

angular.module('webappApp.directives', []).directive(
		'pwCheck',
		[ function() {
			return {
				require : 'ngModel',
				link : function(scope, elem, attrs, ctrl) {
					var firstPassword = '#' + attrs.pwCheck;
					elem.add(firstPassword).on(
							'keyup',
							function() {
								scope.$apply(function() {
									// console.info(elem.val() ===
									// $(firstPassword).val());
									ctrl.$setValidity('pwmatch',
											elem.val() === $(firstPassword)
													.val());
								});
							});
				}
			}
		} ]);