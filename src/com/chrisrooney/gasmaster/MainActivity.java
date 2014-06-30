package com.chrisrooney.gasmaster;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
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

//TEST ONLY IMPORT
import android.util.Log;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState != null) {
			return;
		}
		setContentView(R.layout.activity_main);
		
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.fragment_container, new MainMenuFragment()).addToBackStack(null).commit();
		

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


	public static class MainMenuFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_menu, container,
					false);
			
			final Button Stats, Data_Entry;
			
			Stats = (Button) rootView.findViewById(R.id.Stats);
			Data_Entry = (Button) rootView.findViewById(R.id.Data_Entry);
			
			Stats.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) { 
					FragmentManager fm = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
					fm.beginTransaction().replace(R.id.fragment_container, new DataTablesFragment()).addToBackStack(null).commit();
				}
			});
			
			Data_Entry.setOnClickListener(new OnClickListener() { 
				
				@Override
				public void onClick(View v) {
					FragmentManager fm = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
					fm.beginTransaction().replace(R.id.fragment_container, new DataEntryFragment()).addToBackStack(null).commit();
					
				}
			});
			return rootView;
		}
	}
	
	public static class DataEntryFragment extends Fragment {		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_data_entry, container, false);
			Button Save, MPG;
			final EditText Odometer;
			final EditText Price;
			final EditText Gallons;
			final DataHandler handler;
			
			Save = (Button) rootView.findViewById(R.id.Save);
			MPG = (Button) rootView.findViewById(R.id.MPG);
			Odometer = (EditText) rootView.findViewById(R.id.Odometer);
			Price = (EditText) rootView.findViewById(R.id.Price);
			Gallons = (EditText) rootView.findViewById(R.id.Gallons);
			
			handler = new DataHandler(getActivity());
			
			Save.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String getOdometer = Odometer.getText().toString();
					String getPrice = Price.getText().toString();
					String getGallons = Gallons.getText().toString();
					handler.open();
					handler.insertData(getOdometer, getPrice, getGallons);
					Toast.makeText(getActivity(), "Data inserted", Toast.LENGTH_LONG).show();
					handler.close();	
				}
			});
				
			
			MPG.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					FragmentManager fm = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
					fm.beginTransaction().replace(R.id.fragment_container, new DataTablesFragment()).addToBackStack(null).commit();
					/*
					String getOdometer, getPrice, getGallons;
					getOdometer = "";
					getPrice = "";
					getGallons = "";
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
					Toast.makeText(getActivity(), "Odometer:"+getOdometer + " Price:"+getPrice + " Gallons:"+getGallons, Toast.LENGTH_LONG).show();*/
					
				}
				
			});
			
			return rootView;
		}
	}

	public static class DataTablesFragment extends Fragment {
		//TODO - implement data table view
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_data_entry, container, false);
			return rootView;
		}
	}
	
	public static class DataGraphsFragment extends Fragment {
		//TODO - implement data graph view
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_data_entry, container, false);
			return rootView;
		}
	}

}
