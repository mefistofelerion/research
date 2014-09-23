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
    private float distanceValue;
    private float speedValue;
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
        LoggerConf logger = new LoggerConf();//to initialize logger
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


        //these could be useful when doing the experiments
        stepValueView     = (TextView) findViewById(R.id.stepsView);
       /* paceValueView     = (TextView) findViewById(R.id.pace_value);
        distanceValueView = (TextView) findViewById(R.id.distance_value);
        speedValueView    = (TextView) findViewById(R.id.speed_value);
        caloriesValueView = (TextView) findViewById(R.id.calories_value);
        *///temporary just to know what value they are getting
    }
    private SensorService.ICallback mCallback = new SensorService.ICallback() {
        public void stepsChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(STEPS_MSG, value, 0));
        }
        public void paceChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(PACE_MSG, value, 0));
        }

        public void distanceChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(DISTANCE_MSG, (int)(value*1000), 0));
        }
        public void speedChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(SPEED_MSG, (int)(value*1000), 0));
        }
        public void caloriesChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(CALORIES_MSG, (int)(value), 0));
        }
    };
    private static final int STEPS_MSG = 1;
    private static final int PACE_MSG = 2;
    private static final int DISTANCE_MSG = 3;
    private static final int SPEED_MSG = 4;
    private static final int CALORIES_MSG = 5;

    private float caloriesValue;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STEPS_MSG:
                    stepValue = (int) msg.arg1;
                    stepValueView.setText("Steps: " + stepValue);
                    break;
                case PACE_MSG:
                    paceValue = msg.arg1;
                    if (paceValue <= 0) {
                        //paceValueView.setText("0");
                    } else {
                        //mPaceValueView.setText("" + (int) mPaceValue);
                    }
                    break;
                case DISTANCE_MSG:
                    distanceValue = ((int)msg.arg1)/1000f;
                    if (distanceValue <= 0) {
                        //mDistanceValueView.setText("0");
                    }
                    else {
                        /*distanceValueView.setText(
                                ("" + (distanceValue + 0.000001f)).substring(0, 5)
                        );*/
                    }
                    break;
                case SPEED_MSG:
                    speedValue = ((int)msg.arg1)/1000f;
                    if (speedValue <= 0) {
                        //speedValueView.setText("0");
                    }
                    else {
                        /*speedValueView.setText(
                                ("" + (speedValue + 0.000001f)).substring(0, 4)
                        );*/
                    }
                    break;
                case CALORIES_MSG:
                    caloriesValue = msg.arg1;
                    if (caloriesValue <= 0) {
                        //mCaloriesValueView.setText("0");
                    }
                    else {
                       // mCaloriesValueView.setText("" + (int)mCaloriesValue);
                    }
                    break;
                default:
                    super.handleMessage(msg);
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
