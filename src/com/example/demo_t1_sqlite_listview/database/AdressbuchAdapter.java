package com.example.demo_t1_sqlite_listview.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.demo_t1_sqlite_listview.R;

public class AdressbuchAdapter extends CursorAdapter {

	private LayoutInflater inflator;

	private int ciName, ciVorname, ciBday, ciFoto, ciId;

	public AdressbuchAdapter(Context context, Cursor c) {
		super(context, c);
		inflator = LayoutInflater.from(context);

		ciName = c.getColumnIndex(AdressbuchOpenHandler.EINTRAG_NAME);
		ciVorname = c.getColumnIndex(AdressbuchOpenHandler.EINTRAG_VORNAME);
		ciBday = c.getColumnIndex(AdressbuchOpenHandler.EINTRAG_BDAY);
		ciFoto = c.getColumnIndex(AdressbuchOpenHandler.EINTRAG_FOTO);
		ciId = c.getColumnIndex(AdressbuchOpenHandler._ID);

	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView name = (TextView) view.findViewById(R.id.tvName);
		String kundeName = cursor.getString(ciVorname).concat(" ")
				.concat(cursor.getString(ciName));
		name.setText(kundeName);
		
		TextView bday = (TextView)view.findViewById(R.id.tvBday);
		String kundeBday = cursor.getString(ciBday);
		bday.setText(kundeBday);

		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return inflator.inflate(R.layout.listrow, null);
	}

}
