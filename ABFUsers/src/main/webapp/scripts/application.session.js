angular.module('webappApp').service('Session', function() {
	
	this.sessionUser = null;
	
	this.create = function(userSession) {
		this.sessionUser = userSession;
	};
	
	this.destroy = function() {
		this.sessionUser = null;
	};
});