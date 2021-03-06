package com.example.demo_t1_sqlite_listview.database;

import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdressbuchOpenHandler extends SQLiteOpenHelper {

	// Name und Version der Datenbank
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "adressbuch.db";

	// Name und Attribute der Tabelle Eintrag
	public static final String _ID = "_id";
	public static final String TABLE_NAME_EINTRAG = "eintrag";
	public static final String EINTRAG_NAME = "name";
	public static final String EINTRAG_VORNAME = "vorname";
	public static final String EINTRAG_BDAY = "bday";
	public static final String EINTRAG_FOTO = "foto";

	// Tabelle anlegen
	private static final String TABLE_EINTRAG_CREATE = "CREATE TABLE "
			+ TABLE_NAME_EINTRAG + " (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + EINTRAG_NAME + " TEXT,"
			+ EINTRAG_VORNAME + " TEXT," + EINTRAG_BDAY + " INTEGER ,"
			+ EINTRAG_FOTO + " BLOB);";

	// Tabelle l�schen
	private static final String TABLE_EINTRAG_DROP = "DROP TABLE IF EXISTS "
			+ TABLE_NAME_EINTRAG;

	public AdressbuchOpenHandler(Context context) {
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
		Log.d("AdressbuchOpenHandler", "Upgrade der Datenbank von Version "
				+ oldVersion + " zu " + newVersion
				+ "; alle daten werden gel�scht");
		db.execSQL(TABLE_EINTRAG_DROP);
		onCreate(db);
	}

	public long insert(String name, String vorname, String bday, byte[] foto) {
		long rowId = -1;
		try {
			// Datenbank �ffnen
			SQLiteDatabase db = getWritableDatabase();
			// die zu speichernden Werte
			ContentValues values = new ContentValues();
			values.put(EINTRAG_NAME, name);
			values.put(EINTRAG_VORNAME, vorname);
			values.put(EINTRAG_BDAY, bday);
			values.put(EINTRAG_FOTO, foto);
			// in die Tabelle Eintrag einf�gen
			rowId = db.insert(TABLE_NAME_EINTRAG, null, values);
		} catch (SQLiteException e) {
			Log.e("AdressbuchOpenHandler", "insert()", e);
		} finally {
			Log.d("AdressbuchOpenHandler", "insert(): rowId=" + rowId);
		}

		return rowId;

	}

	public boolean udpate(long id, String name, String vorname, String bday,
			byte[] foto) {
		boolean isSuccessful;
		try {
			// Datenbank �ffnen
			SQLiteDatabase db = getWritableDatabase();
			// die zu speichernden Werte
			ContentValues values = new ContentValues();
			values.put(EINTRAG_NAME, name);
			values.put(EINTRAG_VORNAME, vorname);
			values.put(EINTRAG_BDAY, bday);
			values.put(EINTRAG_FOTO, foto);
			// in die Tabelle Eintrag einf�gen
			db.update(TABLE_NAME_EINTRAG, values,
					_ID + " = " + String.valueOf(id), null);
			isSuccessful = true;
		} catch (SQLiteException e) {
			isSuccessful = false;
			Log.e("AdressbuchOpenHandler", "update()", e);
		} finally {

		}
		return isSuccessful;

	}

	public void deleteAll() {
		try {
			// Datenbank �ffnen
			SQLiteDatabase db = getWritableDatabase();

			// aus der Tabelle Eintraege loeschen
			db.delete(TABLE_NAME_EINTRAG, null, null);

			// Datenbank schliessen
			// db.close();

		} catch (SQLiteException e) {
			Log.e("AdressbuchOpenHandler", "delete()", e);
		} finally {

		}
	}

	public Cursor fetchByName(String inputText) throws SQLException {

		SQLiteDatabase db = getWritableDatabase();
		Cursor mCursor = null;
		if (inputText == null || inputText.length() == 0) {
			mCursor = db.query(TABLE_NAME_EINTRAG, null, null, null, null,
					null, _ID + " DESC");

		} else {
			mCursor = db.query(true, TABLE_NAME_EINTRAG,
					// new String[] {KEY_ROWID, KEY_CODE, KEY_NAME,
					// KEY_CONTINENT, KEY_REGION},
					null, EINTRAG_NAME + " like '%" + inputText + "%'", null,
					null, null, null, null);
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	public Cursor queryEintrag(long id) {
		SQLiteDatabase db = getWritableDatabase();
		String selection = _ID.concat("=").concat(String.valueOf(id));
		return db.query(TABLE_NAME_EINTRAG, null, selection, null, null, null,
				null);
	}

	public Cursor query() {
		// ggf. Datenbank �ffnen
		SQLiteDatabase db = getWritableDatabase();
		return db.query(TABLE_NAME_EINTRAG, null, null, null, null, null, _ID
				+ " DESC");
	}

}
