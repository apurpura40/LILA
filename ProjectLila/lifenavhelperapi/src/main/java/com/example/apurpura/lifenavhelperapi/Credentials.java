package com.example.apurpura.lifenavhelperapi;

/**
 * Created by apurpura on 5/30/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

public class Credentials {
    public static SigningOnActivity signonActivity;
    public static GoogleAccountCredential credential;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;

    public static boolean isGooglePlayServicesAvailable(final Activity activity) {
        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {

            }
            return false;
        }
        return true;
    }

    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     //* @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
    /*public static void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode, final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                        connectionStatusCode,
                        activity,
                        REQUEST_GOOGLE_PLAY_SERVICES);
                dialog.show();
            }
        });
    }*/

    public static boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) signonActivity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            /*
             * Toast.makeText(getActivity(), "No Internet connection!",
             * Toast.LENGTH_LONG).show();
             */
            return false;
        }
        return true;
    }

}
