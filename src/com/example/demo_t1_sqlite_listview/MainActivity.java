package com.example.demo_t1_sqlite_listview;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.demo_t1_sqlite_listview.database.AdressbuchAdapter;
import com.example.demo_t1_sqlite_listview.database.AdressbuchOpenHandler;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private AdressbuchOpenHandler dbHandler;
	private AdressbuchAdapter adapter;
	private ListView liste;
	private Button neuerKontakt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		liste = (ListView) findViewById(R.id.liste);
		dbHandler = new AdressbuchOpenHandler(this);

		Cursor c = dbHandler.query();

		adapter = new AdressbuchAdapter(this, c);

		liste.setAdapter(adapter);
		liste.setOnItemClickListener(this);

		neuerKontakt = (Button) findViewById(R.id.btnNeuerKontakt);
		neuerKontakt.setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		adapter.getCursor().requery();
		adapter.notifyDataSetChanged();
	}

	/**
	 * Interface OnClickListener
	 */
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, FormularActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> liste, View eintrag,
			int positionOfView, long eintragId) {
		Intent intent = new Intent(this, FormularActivity.class);
		intent.putExtra(FormularActivity.PARAM_EINTRAG_ID, eintragId);
		startActivity(intent);
	}

}
