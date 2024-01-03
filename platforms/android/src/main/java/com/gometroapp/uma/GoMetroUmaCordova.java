package com.gometroapp.uma;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.gometroapp.mobile.android.logging.AndroidLogger;
import com.gometroapp.mobile.android.service.GoMetroServiceBuilder;
import com.gometroapp.mobile.android.service.event.ActivityResultReceived;
import com.gometroapp.mobile.android.service.event.CordovaActivityRefreshed;
import com.gometroapp.mobile.core.event.DeviceEventBus;
import com.gometroapp.mobile.core.logging.LoggerFactory;
import com.gometroapp.mobile.core.support.Objects;
import com.gometroapp.uma.context.GoMetroUmaAndroidContext;

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
    private String goMetroUmaExternalDeviceId;

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
            this.goMetroUmaExternalDeviceId = args.getString(2)
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

        initialise(activity, this.goMetroUmaUsername, this.goMetroUmaPassword, this.goMetroUmaExternalDeviceId);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            DeviceEventBus.publish(new ActivityResultReceived(requestCode, resultCode, data));
        } catch (Throwable throwable) {
            Log.e(TAG, throwable.getMessage(), throwable);
        }
    }

    public void initialise(final Activity activity, final String username, final String password, final String externalDeviceId) {

        try {
            Objects.requireNonNull(activity, "A activity must be provided!");
            Objects.requireNonEmpty(password, "A password must be provided!");
            Objects.requireNonEmpty(username, "A username must be provided!");

            LoggerFactory.setLogger(new AndroidLogger(BuildConfig.DEBUG, TAG));

            new GoMetroServiceBuilder()
                .apiBaseUrl(BuildConfig.API_BASE_URL)
                .context(activity)
                .contextClass(GoMetroUmaAndroidContext.class)
                .debug(BuildConfig.DEBUG)
                .identityBaseUrl(BuildConfig.IDENTITY_BASE_URL)
                .identityClientId("gometro-uma-android")
                .password(password)
                .sdkPackage(BuildConfig.LIBRARY_PACKAGE_NAME)
                .sdkVersion(BuildConfig.VERSION_NAME)
                .trackingEnabledByDefault(false)
                .username(username)
                .externalDeviceId(externalDeviceId)
                .start();

            DeviceEventBus.publishSync(new CordovaActivityRefreshed(this));
        } catch (Throwable throwable) {
            Log.e(TAG, throwable.getMessage(), throwable);
        }
    }
}
