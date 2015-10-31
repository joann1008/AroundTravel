package com.extra.aroundtravel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "atravel.db";
    private static final int DATABASE_VERSION = 1;
	
	public SqlHelper(Context context) {
		super(context, DATABASE_NAME, null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS user" +  
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR,password VARCHAR,groupid INTEGER)"); 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("ALTER TABLE user ADD COLUMN other STRING");
	}

}
