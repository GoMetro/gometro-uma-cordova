package com.gometroapp.uma;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class GoMetroUmaCordova extends CordovaPlugin {

    private static final String TAG = "GoMetroUma";

    private String goMetroUmaUsername;
    private String goMetroUmaPassword;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "Initializing GoMetro UMA");
    }

    public boolean execute(String action,
                           JSONArray args,
                           final CallbackContext callbackContext) throws JSONException {

        if (action.equals("init")) {
            this.goMetroUmaUsername = args.getString(0);
            this.goMetroUmaPassword = args.getString(1);
            this.onResume(false);

            callbackContext.sendPluginResult(new PluginResult(Status.OK));
        } else {
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, "not implemented"));
        }

        return true;
    }

    @Override
    public void onResume(boolean multitasking) {

        if (this.goMetroUmaUsername == null || this.goMetroUmaPassword == null) {
            return;
        }

        Activity activity = this.cordova.getActivity();

        GoMetroUma.initialise(
            activity,
            this.goMetroUmaUsername,
            this.goMetroUmaPassword
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        GoMetroUma.onActivityResult(requestCode, resultCode, data);
    }
}
