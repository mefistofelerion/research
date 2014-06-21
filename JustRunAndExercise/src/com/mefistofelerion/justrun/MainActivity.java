package com.mefistofelerion.justrun;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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
	 */
	public static class PlaceholderFragment extends Fragment {

		TextView t;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			t = new TextView(getActivity());
			final Button button = (Button) rootView.findViewById(R.id.button_agree);
			
			t = (TextView) rootView.findViewById(R.id.welcome_text);
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					t.setText("Bienvenido!, empecemos a jugar!");
					button.setVisibility(TRIM_MEMORY_UI_HIDDEN);
				}
			});
			return rootView;
		}
		
		private void initAR(){
			//config the video settings and initialize vuforia 
			//initialize and start camera
			
	            
		}
		
		private void initPedometer(){
			//this will have the code for using the pedometer
		}
		
		private void saveInformation(){
			//collect all the information for investigation purposes
		}
	}

}
