package com.mefistofelerion.justrun;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Initial extends Activity {

    private static final String TAG = "Initial Activity";
    private int paceValue;
    private int stepValue;
    private boolean isRunning;
    private TextView stepValueView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        stepValue = 0;
        paceValue = 0;
        if (!isKitKatWithStepCounter(getPackageManager())) {
            isRunning = true;
            initPedometerService();

        }
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "[ACTIVITY] onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "[ACTIVITY] onResume");
        super.onResume();




        // Start the service if this is considered to be an application start (last onPause was long ago)
        if (!isRunning) {
            initPedometerService();
            bindStepService();
        }
        else if (isRunning) {
            bindStepService();
        }



        stepValueView     = (TextView) findViewById(R.id.stepsView);
//        mPaceValueView     = (TextView) findViewById(R.id.pace_value);


    }
    private SensorService.ICallback mCallback = new SensorService.ICallback() {
        public void stepsChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(STEPS_MSG, value, 0));
        }
        public void paceChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(PACE_MSG, value, 0));
        }
    };
    private static final int STEPS_MSG = 1;
    private static final int PACE_MSG = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STEPS_MSG:
                    stepValue = (int) msg.arg1;
                    stepValueView.setText("Steps: " + stepValue);
                    break;
                case PACE_MSG:
//                    mPaceValue = msg.arg1;
//                    if (mPaceValue <= 0) {
//                        mPaceValueView.setText("0");
//                    } else {
//                        mPaceValueView.setText("" + (int) mPaceValue);
//                    }
                    break;
            }

        }
    };
    private SensorService sensorService;

    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            sensorService = ((SensorService.StepBinder)service).getService();

            sensorService.registerCallback(mCallback);
            sensorService.reloadSettings();

        }

        public void onServiceDisconnected(ComponentName className) {
            sensorService = null;
        }
    };
    private void bindStepService() {
        Log.i(TAG, "[SERVICE] Bind");
        bindService(new Intent(Initial.this,
                SensorService.class), connection, Context.BIND_AUTO_CREATE + Context.BIND_DEBUG_UNBIND);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.initial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }

    private Boolean isKitKatWithStepCounter(PackageManager pm) {

        // Require at least Android KitKat
        int currentApiVersion = (int) Build.VERSION.SDK_INT;

        // Check that the device supports the step counter and detector
        // sensors

        return currentApiVersion >= 19
                && pm.hasSystemFeature(android.content.pm.PackageManager.FEATURE_SENSOR_STEP_COUNTER)
                && pm.hasSystemFeature(android.content.pm.PackageManager.FEATURE_SENSOR_STEP_DETECTOR);
    }

    private void initPedometerService() {
        startService(new Intent(Initial.this, SensorService.class));
    }

}
