package com.example.demo_t1_sqlite_listview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdressbuchOpenHandler extends SQLiteOpenHelper {

	// Name und Version der Datenbank
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "adressbuch.db";
	
	// Name und Attribute der Tabelle Eintrag
	private static final String _ID = "_id";
	private static final String TABLE_NAME_EINTRAG = "eintrag";
	private static final String NAME = "name";
	private static final String VORNAME = "vorname";
	private static final String BDAY = "bday";
	private static final String FOTO = "foto";
	
	// Tabelle anlegen
	private static final String TABLE_EINTRAG_CREATE
	= "CREATE TABLE "
	+ TABLE_NAME_EINTRAG + " (" + _ID
	+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ NAME + " TEXT,"
    + VORNAME + " TEXT,"
    + BDAY + " INTEGER ,"
    + FOTO + " BLOB);";
	
	public AdressbuchOpenHandler (Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_EINTRAG_CREATE);
        Log.d("AdressbuchOpenHandler", "Tabelle erzeugt");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
