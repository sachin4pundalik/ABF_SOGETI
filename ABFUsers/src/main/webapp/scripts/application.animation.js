angular.module('webappApp').animation("enter-slide", function () {
    return {
        setup: function (element) {
            element.hide();
        },
        start: function (element, done, memo) {
            try{
                element.slideDown(function () {
                    done();
                });
            }
            catch(ex){}
        }
    };
});

angular.module('webappApp').animation("leave-slide", function () {
    return {
        start: function (element, done, memo) {
            element.slideUp(function () {
                done();
            });
        }
    };
});