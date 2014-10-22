package com.mefistofelerion.justrun;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//TODO general cleaning
public class Initial extends Activity {

    private static final String TAG = "Initial Activity";
    private int paceValue;
    private int stepValue;
    private float distanceValue;
    private float speedValue;
    private boolean isRunning;
    //private TextView stepValueView;
    private SharedPreferences savedValues;
    private Editor saveInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stepValue = 0;
        paceValue = 0;
        if (!isKitKatWithStepCounter(getPackageManager())) {
            isRunning = true;
            initPedometerService();

        }
        saveInfo = savedValues.edit();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "[ACTIVITY] onStart");
        super.onStart();
    }

    /*
    will try to save all data from shared preferences locally
     */
    @Override
    protected void onStop(){
        try {
            FileOutputStream fileToWrite = openFileOutput("Data.dat", MODE_PRIVATE);
            savedValues = getSharedPreferences("MyData", MODE_PRIVATE);
            String data = "";
            if(savedValues.contains("steps")){
                data += String.valueOf(savedValues.getInt("steps",0)) + " ";
            }
            if(savedValues.contains("pace")){
                data += String.valueOf(savedValues.getInt("pace",0)) + " ";
            }
            if(savedValues.contains("calories")){
                data += String.valueOf(savedValues.getFloat("calories",0)) + " ";
            }
            if(savedValues.contains("distance")){
                data += String.valueOf(savedValues.getFloat("distance",0)) + " ";
            }
            if(savedValues.contains("speed")){
                data += String.valueOf(savedValues.getFloat("speed", 0)) + " ";
            }

            fileToWrite.write(data.getBytes());
            //if everything goes ok, save it to the DB

            JSONObject dataToSend = new JSONObject();
            dataToSend.put("steps",savedValues.getInt("steps",0));
            dataToSend.put("distance",savedValues.getFloat("distance",0));
            dataToSend.put("calories",savedValues.getFloat("calories",0));
            sendData(dataToSend);
        }catch(FileNotFoundException ex){
            LoggerHelper.error("error while opening file for writing data");
        }catch(IOException ex){
            LoggerHelper.error("error while writing the file");
        }catch(JSONException ex){
            LoggerHelper.error("error while creating json string");
        }
    }

    private void sendData(JSONObject json) {
        try{
        HttpClient client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://localhost:8080/data");
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response = client.execute(httpget, responseHandler);
        if(response == "ok"){
            LoggerHelper.debug("DB wrote correctly the data");
        }
        }catch(ClientProtocolException ex){
            LoggerHelper.error("error while executing request to the server");
        }catch(IOException ex){
            LoggerHelper.error("error while executing request to the server");
        }
        Toast.makeText(getApplicationContext(),"Information sent!",Toast.LENGTH_SHORT).show();
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

        //these could be useful when doing the experiments, I will let this here if i need it
        /* stepValueView     = (TextView) findViewById(R.id.stepsView);
        paceValueView     = (TextView) findViewById(R.id.pace_value);
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
                    LoggerHelper.debug(String.valueOf(stepValue));
                    saveInfo.putInt("steps", stepValue);
                    break;
                case PACE_MSG:
                    paceValue = msg.arg1;
                    if (paceValue <= 0) {
                        saveInfo.putInt("pace", paceValue);
                    } else {
                        LoggerHelper.info("no pace detected");
                    }
                    break;
                case DISTANCE_MSG:
                    distanceValue = ((int)msg.arg1)/1000f;
                    if (distanceValue <= 0) {
                        saveInfo.putFloat("distance", distanceValue);
                    }
                    else {
                        LoggerHelper.info("there's no distance yet");
                    }
                    break;
                case SPEED_MSG:
                    speedValue = ((int)msg.arg1)/1000f;
                    if (speedValue <= 0) {
                       saveInfo.putFloat("speed", speedValue);
                    }
                    else {
                        LoggerHelper.info("there's no speed yet");
                    }
                    break;
                case CALORIES_MSG:
                    caloriesValue = msg.arg1;
                    if (caloriesValue <= 0) {
                        saveInfo.putFloat("calories", caloriesValue);
                    }
                    else {
                       LoggerHelper.info("there's no calories to show yet ");
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
        getMenuInflater().inflate(R.menu.initial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
