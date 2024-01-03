var exec = require("cordova/exec");

var handleSuccess = function (result, callback) {
    if (callback && typeof callback === "function") {
        callback(result);
    }
};

var handleFailure = function (error, callback) {
    if (callback && typeof callback === "function") {
        callback(error);
    }
};

var init = function (
    username,
    password,
    successCallback,
    failureCallback,
    externalDeviceId
) {
    var params = [username, password];
    if (externalDeviceId !== null) {
        params.push(externalDeviceId);
    }
    exec(
        function (result) {
            handleSuccess(result, successCallback);
        },
        function (error) {
            handleFailure(error, failureCallback);
        },
        "GoMetroUma",
        "init",
        params
    );
};

module.exports = {
    init: init,
};
