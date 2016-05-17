
angular.module('webappApp').constant('AUTH_EVENTS', {
	loginSuccess : 'auth-login-success',
	loginFailed : 'auth-login-failed',
	logoutSuccess : 'auth-logout-success',
	sessionTimeout : 'auth-session-timeout',
	notAuthenticated : 'auth-not-authenticated',
	notAuthorized : 'auth-not-authorized'
});

angular.module('webappApp').constant('USER_ROLES', {
	all : '*',
	admin : 'admin',
	editor : 'editor',
	guest : 'guest'
});

angular.module('webappApp').constant("ABF_CONSTANTS", {
	"FAILURE_HEADER" : "Failure Message",
	"INFO_HEADER" : "Message",
	"AUTHENTICATE_FAILURE_HEADER":"Failed Login",
	"AUTHENTICATE_FAILURE_MESSAGE":"Unable to login due to invalid credentials.",
	
	"AUTHENTICATE_SUCCESS_MESSAGE":"Welcome $1 to ABF..!!",
	
	"SUCCESS" : "success",
	"FAILURE" : "failure",
	"FAILURE_MESSAGE" : "Unable to perform operation!!",
	"ONSHORE":"Onshore",
	"OFFSHORE":"Offshore",
	
	"MASTER_DATA" : "Master Data - ",
	"RESOURCE_TYPES" : "Resource Type(s)",
	"BANDS" : "Band(s)",
	"GRADES" : "Grade(s)",
	"ROLES" : "Role(s)",
	"USER_ROLE" : "User Role(s)",
	"ONSHORE_PRICES" : "Onshore Price(s)",
	"OFFSHORE_PRICES" : "Offshore Price(s)",
	"STAY_TYPE" : "Stay Type(s)",
	"STATUS" : "Status(s)",
	"SKILLS" : "Skill(s)",
	"BUSINESS_LINES" : "Business Line(s)",
	"FIXED_HOURS" : "Fixed Hour(s)"
});