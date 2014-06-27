package com.mefistofelerion.justrun;

import android.R.bool;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.*;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.fatdecrease.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 * fragment for counting steps and possibly the welcome text
	 */
	public static class PlaceholderFragment extends Fragment {

		private TextView t;
		private boolean mIsRunning;
		private int steps = 0;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			t = new TextView(getActivity());
			final Button button = (Button) rootView
					.findViewById(R.id.button_agree);

			t = (TextView) rootView.findViewById(R.id.welcome_text);
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					t.setText("pasos dados: " + steps);
					PackageManager pm = getActivity().getPackageManager();
					t.setText("is valid: " + isKitKatWithStepCounter(pm) + " "
							+ (int) Build.VERSION.SDK_INT);
					// initPedometer();
					button.setVisibility(TRIM_MEMORY_UI_HIDDEN);
				}
			});
			return rootView;
		}

		private void initAR() {
			// config the video settings and initialize vuforia
			// initialize and start camera

		}

		private void startStepService() {
			if (!mIsRunning) {
				Log.i("JustRun", "[SERVICE] Start");
				mIsRunning = true;
				/*
				 * startService(new Intent(PlaceholderFragment.this,
				 * StepService.class));
				 */
			}
		}

		// check if is has the needed sensors
		@TargetApi(Build.VERSION_CODES.KITKAT)
		@SuppressLint("InlinedApi")
		public static Boolean isKitKatWithStepCounter(PackageManager pm) {

			// Require at least Android KitKat
			int currentApiVersion = (int) Build.VERSION.SDK_INT;
			Log.i("Fragment", "android version: " + currentApiVersion);
			// Check that the device supports the step counter and detector
			// sensors

			return currentApiVersion >= 19
					&& pm.hasSystemFeature(android.content.pm.PackageManager.FEATURE_SENSOR_STEP_COUNTER)
					&& pm.hasSystemFeature(android.content.pm.PackageManager.FEATURE_SENSOR_STEP_DETECTOR);
		}

		private void initPedometer() {
			StepDetector detector = new StepDetector();
		}

		private void saveInformation() {
			// collect all the information for investigation purposes
		}
	}

}
