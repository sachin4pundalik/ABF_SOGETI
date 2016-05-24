angular.module('webappApp').factory('$exceptionHandler',
		[ '$injector', function($injector) {
			return function(exception, cause) {
				var toastr = $injector.get("toastr");
				exception.message += ' (caused by "' + cause + '")';
				toastr.error("Exception:\n"+exception, "Exception");
			};
		} ]);

angular.module('webappApp').config(exceptionConfig);

exceptionConfig.$inject = [ '$provide' ];

function exceptionConfig($provide) {
	$provide.decorator('$exceptionHandler', extendExceptionHandler);
}

extendExceptionHandler.$inject = [ '$delegate', '$injector' ];

function extendExceptionHandler($delegate, $injector) {
	return function(exception, cause) {
		var toastr = $injector.get("toastr");
		$delegate(exception, cause);
		var errorData = {
			exception : exception,
			cause : cause
		};
		/**
		 * Could add the error to a service's collection,
		 * add errors to $rootScope, log errors to remote web server,
		 * or log locally. Or throw hard. It is entirely up to you.
		 * throw exception;
		 */
		toastr.error("extendExceptionHandler:: "+exception, "Exception");
	};
}

angular.module('webappApp').factory('exception', ['logger', exception]);

function exception(logger) {
    var service = {
        catcher: catcher
    };
    return service;


    function catcher(message) {
        return function(reason) {
            logger.error(message, reason);
        };
    }
}