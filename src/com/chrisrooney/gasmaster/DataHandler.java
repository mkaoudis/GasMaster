/** Copyright 2014 by Christopher Rooney, Mike Kaoudis and Manpreet Singh. All rights reserved*/

package com.chrisrooney.gasmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHandler {
	public static final String ODOMETER = "odometer";
	public static final String PRICE = "price";
	public static final String GALLONS = "gallons";
	public static final String TABLE_NAME = "mytable";
	public static final String DATABASE_NAME = "mydatabase";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_CREATE = "create table mytable (odometer text not null, price text not null, gallons text not null);";
	
	DataBaseHelper dbhelper;
	Context ctx;
	SQLiteDatabase db;
	
	public DataHandler(Context ctx)
	{
		this.ctx = ctx;
		dbhelper = new DataBaseHelper(ctx);
		
	}
	
	private static class DataBaseHelper extends SQLiteOpenHelper
	{
		public DataBaseHelper(Context ctx)
		{
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			try{
			db.execSQL(TABLE_CREATE);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		


		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			
			db.execSQL("DROP TABLE IF EXISTS mytable");
			onCreate(db);
			
		}
		
	}
	
	public DataHandler open()
	{
		db = dbhelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		dbhelper.close();
	}
	
	public long insertData(String odometer, String price, String gallons)
	{
		ContentValues content = new ContentValues();
		content.put(ODOMETER,odometer);
		content.put(PRICE, price);
		content.put(GALLONS, gallons);
		return db.insertOrThrow(TABLE_NAME, null, content);
	}
	
	public Cursor returnData()
	{
		return db.query(TABLE_NAME, new String[] {ODOMETER, PRICE, GALLONS}, null, null, null, null, null);
	}

}
