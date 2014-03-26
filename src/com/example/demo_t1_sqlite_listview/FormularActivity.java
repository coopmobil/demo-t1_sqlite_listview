package com.example.demo_t1_sqlite_listview;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo_t1_sqlite_listview.database.AdressbuchOpenHandler;

public class FormularActivity extends Activity implements OnClickListener {
	private static final String TAG = FormularActivity.class.getSimpleName();
	public static final String PARAM_EINTRAG_ID = "adr_eintrag_id";

	private Button neuerKontaktSpeichern;
	private EditText txtName, txtVorname, txtBday;

	private long id;
	private AdressbuchOpenHandler adressbuchOpenHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formular_activity);

		txtName = (EditText) findViewById(R.id.editText1);
		txtVorname = (EditText) findViewById(R.id.editText2);
		txtBday = (EditText) findViewById(R.id.editText3);

		neuerKontaktSpeichern = (Button) findViewById(R.id.btnNeuerKontaktSpeichern);
		neuerKontaktSpeichern.setOnClickListener(this);

		if (getIntent().hasExtra(PARAM_EINTRAG_ID)) {
			id = getIntent().getExtras().getLong(PARAM_EINTRAG_ID);
		} else {
			id = -1;
		}

		adressbuchOpenHandler = new AdressbuchOpenHandler(getBaseContext());

		if (id > -1) {

			Log.d(TAG, "id = " + id);

			Cursor dbCursor = adressbuchOpenHandler.queryEintrag(id);
			if (dbCursor.moveToFirst()) {
				Log.d(TAG, "Anzahl Zeilen = " + dbCursor.getCount());

				String name = dbCursor.getString(dbCursor
						.getColumnIndex(AdressbuchOpenHandler.EINTRAG_NAME));
				String vorname = dbCursor.getString(dbCursor
						.getColumnIndex(AdressbuchOpenHandler.EINTRAG_VORNAME));
				String bday = dbCursor.getString(dbCursor
						.getColumnIndex(AdressbuchOpenHandler.EINTRAG_BDAY));

				txtName.setText(name);
				txtVorname.setText(vorname);
				txtBday.setText(bday);

			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * Interface OnClickListener
	 */
	@Override
	public void onClick(View v) {

		final String name = txtName.getText().toString();
		final String vorname = txtVorname.getText().toString();
		final String bday = txtBday.getText().toString();

		int messageId;

		if (id == -1) {

			id = adressbuchOpenHandler.insert(name, vorname, bday, null);
			if (id > -1) {
				messageId = R.string.insert_success;
			} else {
				messageId = R.string.insert_failure;
			}
		} else {
			boolean isSuccesful = adressbuchOpenHandler.udpate(id, name,
					vorname, bday, null);
			if (isSuccesful) {
				messageId = R.string.update_success;
			} else {
				messageId = R.string.update_failure;
			}
		}
		Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();

		finish();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		adressbuchOpenHandler.close();
	}
}
