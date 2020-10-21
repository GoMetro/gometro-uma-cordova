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

var init = function(username, password, successCallback, failureCallback) {
    exec(
        function(result) {
            handleSuccess(result, successCallback)
        },
        function(error) {
            handleFailure(error, failureCallback)
        },
        "GoMetroUma",
        "init",
        [username, password]
    );
};

module.exports = {
    init: init
};
