package com.mefistofelerion.justrun;

import android.util.Log;

/**
 * will be used to collect data to be send later
 * Created by Ivan Guerra on 18/08/14.
 */
public class LoggerHelper {

    private static final String LOG_TAG = "JustRun";

    public static void error(String error) {
        Log.e(LOG_TAG, error);
    }

    public static void debug(String msg) {
        Log.d(LOG_TAG, msg);
    }

    public static void info(String msg) {
        Log.i(LOG_TAG, msg);
    }

}
