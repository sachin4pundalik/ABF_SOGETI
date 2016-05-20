
angular.module('webappApp')
		.config(
				[
						'$routeProvider',
						'toastrConfig',
						function($routeProvider, toastrConfig) {
							$routeProvider
									.when(
											'/landing',
											{
												templateUrl : 'angular-components/dashboard/dashboard.html',
												controller : 'dashboardCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/login',
											{
												templateUrl : 'angular-components/login/login.html',
												controller : 'loginCtrl'
											})
									.when(
											'/logout',
											{
												templateUrl : 'angular-components/login/login.html',
												controller : 'logoutCtrl'
											})
									.when(
											'/changePassword',
											{
												templateUrl : 'angular-components/changepwd/changePwd.html',
												controller : 'changePasswordCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/hours',
											{
												templateUrl : 'angular-components/amHours/resources.html',
												controller : 'amHoursCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/kt',
											{
												templateUrl : 'angular-components/ktHours/ktHours.html',
												controller : 'ktHoursCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/fixed',
											{
												templateUrl : 'angular-components/fixedHours/fixedHours.html',
												controller : 'fixedHoursCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/report',
											{
												templateUrl : 'angular-components/report/report.html',
												controller : '',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/createContract',
											{
												templateUrl : 'angular-components/create-contract/create-contract.html',
												controller : 'createContractCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									// Master data settings start
									.when(
											'/mrtype',
											{
												templateUrl : 'angular-components/master/resourcetype/resourcetype.html',
												controller : 'MasterResourceTypeCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/mskill',
											{
												templateUrl : 'angular-components/master/skill/skill.html',
												controller : 'MasterSkillCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/mband',
											{
												templateUrl : 'angular-components/master/band/band.html',
												controller : 'MasterBandCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/mbline',
											{
												templateUrl : 'angular-components/master/bline/bline.html',
												controller : 'MasterBlineCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/mrole',
											{
												templateUrl : 'angular-components/master/role/role.html',
												controller : 'MasterRoleCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/mgrade',
											{
												templateUrl : 'angular-components/master/grade/grade.html',
												controller : 'MasterGradeCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/mstatus',
											{
												templateUrl : 'angular-components/master/status/status.html',
												controller : 'MasterApprovalStatusCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/mstay',
											{
												templateUrl : 'angular-components/master/stay/stay.html',
												controller : 'MasterStayCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/monshoreprice',
											{
												templateUrl : 'angular-components/master/onshoreprice/onshoreprice.html',
												controller : 'MasterOnshorePriceCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/moffshoreprice',
											{
												templateUrl : 'angular-components/master/offshoreprice/offshoreprice.html',
												controller : 'MasterOffshorePriceCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/mfixedhours',
											{
												templateUrl : 'angular-components/master/fixedhours/fixedhours.html',
												controller : 'MasterFixedHourCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									.when(
											'/muserrole',
											{
												templateUrl : 'angular-components/master/userrole/userrole.html',
												controller : 'MasterUserRoleCtrl',
												resolve: {
												    auth: ["$q", "AuthService", function($q, AuthService) {
												      var userInfo = AuthService.isAuthenticated();

												      if (userInfo) {
												        return $q.when(true);
												      } else {
												        return $q.reject({ authenticated: false });
												      }
												    }]
												  }
											})
									// Master data setting end
									.otherwise({
										redirectTo : '/login'
									});
							/*
							 * toast-top-right (Default) toast-top-center
							 * toast-top-left toast-top-full-width
							 * toast-bottom-right toast-bottom-center
							 * toast-bottom-left toast-bottom-full-width
							 */
							// toastr configuration
							angular.extend(toastrConfig, {

								positionClass : 'toast-bottom-full-width',
								progressBar : true

							});
						} ])
		.run(
				function($rootScope, $location, $cookieStore, AUTH_EVENTS, AuthService) {
					$rootScope
							.$on('$routeChangeStart',
									function(event, next) {
										
										let options = ["","/login","/logout"];
										// for the menus
										if (options.indexOf($location.path())>-1) {
											$cookieStore.remove('abfuser');
											$rootScope.$broadcast(AUTH_EVENTS.sessionTimeout);
										}else if (!AuthService.isAuthenticated()) {
											// Get cookie
											var userCookie = $cookieStore.get('abfuser');
											console.log("user from cookie : " + userCookie);
											// to block unauthorized user and
											// handle page refresh
											if (angular.isUndefined(userCookie)
													|| userCookie===null) {
												$location.path('/login');
												// user is not allowed
												$rootScope.$broadcast(AUTH_EVENTS.sessionTimeout);
											}else{
												//console.log("User Cookie : " + userCookie);
												$rootScope.$broadcast(AUTH_EVENTS.loginSuccess, JSON.parse(userCookie));
											}
										}

									});
					/**
				     * Route cancellation:
				     * On routing error, go to the dashboard.
				     * Provide an exit clause if it tries to do it twice.
				     */
				    $rootScope.$on('$routeChangeError',
				        function(event, current, previous, rejection) {
				            var destination = (current && (current.title || current.name || current.loadedTemplateUrl)) ||
				                'unknown target';
				            var msg = 'Error routing to ' + destination + '. ' + (rejection.msg || '');
				            /**
				             * Optionally log using a custom service or $log.
				             * (Don't forget to inject custom service)
				             */
				            logger.warning(msg, [current]);
				        }
				    );
				});

/*
 * angular.module('webappApp').config(function(toastrConfig) { angular.extend(toastrConfig, {
 * allowHtml: false, autoDismiss: false, closeButton: false, closeHtml: '<button>&times;</button>',
 * containerId: 'toast-container', extendedTimeOut: 1000, iconClasses: { error:
 * 'toast-error', info: 'toast-info', success: 'toast-success', warning:
 * 'toast-warning' }, maxOpened: 0, messageClass: 'toast-message', newestOnTop:
 * true, onHidden: null, onShown: null, onTap: null, positionClass:
 * 'toast-top-right', preventDuplicates: false, preventOpenDuplicates: false,
 * progressBar: false, tapToDismiss: true, target: 'body', templates: { toast:
 * 'directives/toast/toast.html', progressbar:
 * 'directives/progressbar/progressbar.html' }, timeOut: 5000, titleClass:
 * 'toast-title', toastClass: 'toast' }); });
 */