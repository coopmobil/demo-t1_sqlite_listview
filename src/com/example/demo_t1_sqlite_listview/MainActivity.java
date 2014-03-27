package com.example.demo_t1_sqlite_listview;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demo_t1_sqlite_listview.database.AdressbuchAdapter;
import com.example.demo_t1_sqlite_listview.database.AdressbuchOpenHandler;

public class MainActivity extends Activity implements OnItemClickListener {
	private AdressbuchOpenHandler dbHandler;
	private AdressbuchAdapter adapter;
	private ListView liste;

	// private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		liste = (ListView) findViewById(R.id.liste);
		dbHandler = new AdressbuchOpenHandler(this);

		// actionBar = getActionBar();

		Cursor c = dbHandler.query();

		adapter = new AdressbuchAdapter(this, c);

		liste.setAdapter(adapter);
		liste.setOnItemClickListener(this);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_search:
			// openSearch();
			
			Cursor c = dbHandler.fetchByName("genadi");
			adapter = new AdressbuchAdapter(this, c);

			liste.setAdapter(adapter);
//			liste.setOnItemClickListener(this);

			return true;
		case R.id.action_new:
			Intent intent = new Intent(this, FormularActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_all_delete:
			dbHandler.deleteAll();
			Toast.makeText(this, R.string.delete_ok, Toast.LENGTH_LONG).show();
			adapter.getCursor().requery();
			adapter.notifyDataSetChanged();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		adapter.getCursor().requery();
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		dbHandler.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onItemClick(AdapterView<?> liste, View eintrag,
			int positionOfView, long eintragId) {
		Intent intent = new Intent(this, FormularActivity.class);
		intent.putExtra(FormularActivity.PARAM_EINTRAG_ID, eintragId);
		startActivity(intent);
	}

}
