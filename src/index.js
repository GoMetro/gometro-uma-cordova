
export const init = function(token, success, error) {
    cordova.exec(success, error, "GoMetroUma", "init", [token]);
};
