package com.mefistofelerion.justrun;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import static android.os.PowerManager.PARTIAL_WAKE_LOCK;

public class SensorService extends Service {

    private static final String TAG = "steps service";
    private PowerManager.WakeLock wakeLock;
    private IBinder binder;
    private StepDetector stepDetector;
    private SensorManager sensorManager;
    private Sensor sensor;
    private StepDisplayer stepDisplayer;
    private int steps;
    private ICallback mCallback;


    private StepDisplayer.Listener stepListener = new StepDisplayer.Listener() {
        public void stepsChanged(int value) {
            steps = value;
            passValue();
        }

        public void passValue() {
            if (mCallback != null) {
                mCallback.stepsChanged(steps);
            }
        }
    };


    public void reloadSettings() {


        if (stepDetector != null) {
            stepDetector.setSensitivity(10);
        }

        if (stepDisplayer != null) stepDisplayer.reloadSettings();
//            if (mPaceNotifier     != null) mPaceNotifier.reloadSettings();

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "binding step service");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        acquireWakeLock();
        stepDetector = new StepDetector();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        registerDetector();

        stepDisplayer = new StepDisplayer();
        stepDisplayer.setSteps(0);
        stepDisplayer.addListener(stepListener);
        stepDetector.addStepListener(stepDisplayer);
        Toast.makeText(this, getText(R.string.started), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "[SERVICE] onDestroy");


        // Unregister our receiver.
        unregisterDetector();

        wakeLock.release();

        super.onDestroy();

        // Stop detecting
        sensorManager.unregisterListener(stepDetector);

        // Tell the user we stopped.
        Toast.makeText(this, getText(R.string.stopped), Toast.LENGTH_SHORT).show();
    }

    private void unregisterDetector() {
        sensorManager.unregisterListener(stepDetector);
    }

    private void acquireWakeLock() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PARTIAL_WAKE_LOCK, TAG);
        wakeLock.acquire();

    }

    private void registerDetector() {
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void registerCallback(ICallback cb) {
        mCallback = cb;
    }

    public interface ICallback {
        public void stepsChanged(int value);
    }

    public class StepBinder extends Binder {
        SensorService getService() {
            return SensorService.this;
        }
    }
}
