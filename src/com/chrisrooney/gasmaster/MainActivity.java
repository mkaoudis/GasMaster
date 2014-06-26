package com.chrisrooney.gasmaster;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	Button Save, MPG;
	EditText Odometer, Price, Gallons;
	DataHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Save = (Button) findViewById(R.id.Save);
		MPG = (Button) findViewById(R.id.MPG);
		Odometer = (EditText) findViewById(R.id.Odometer);
		Price = (EditText) findViewById(R.id.Price);
		Gallons = (EditText) findViewById(R.id.Gallons);
		
		Save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String getOdometer = Odometer.getText().toString();
				String getPrice = Price.getText().toString();
				String getGallons = Gallons.getText().toString();
				handler = new DataHandler(getBaseContext());
				handler.open();
				long id = handler.insertData(getOdometer, getPrice, getGallons);
				Toast.makeText(getBaseContext(), "Data inserted", Toast.LENGTH_LONG).show();
				handler.close();	
			}
		});
			
		
		MPG.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String getOdometer, getPrice, getGallons;
				getOdometer = "";
				getPrice = "";
				getGallons = "";
				handler = new DataHandler(getBaseContext());
				handler.open();
				Cursor C = handler.returnData();
				if(C.moveToFirst())
				{
					do
					{
						getOdometer = C.getString(0);
						getPrice = C.getString(1);
						getGallons = C.getString(2);
					}while(C.moveToNext());
				}
				
				handler.close();
				Toast.makeText(getBaseContext(), "Odometer:"+getOdometer + " Price:"+getPrice + " Gallons:"+getGallons, Toast.LENGTH_LONG).show();
				
			}
			
		});

		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
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
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_main, container,
					false);
			return rootView;
		}
	}*/

}
