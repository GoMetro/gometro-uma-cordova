var exec = require("cordova/exec");

var handleSuccess = function(result, callback) {
    if (callback && typeof callback === "function") {
        callback(result);
    }
};

var handleFailure = function(error, callback) {
    if (callback && typeof callback === "function") {
        callback(error);
    }
};

var init = function(token, successCallback, failureCallback) {
    exec(
        function(result) {
            handleSuccess(result, successCallback)
        },
        function(error) {
            handleFailure(error, failureCallback)
        },
        "GoMetroUma",
        "init",
        [token]
    );
};

module.exports = {
    init: init
};
