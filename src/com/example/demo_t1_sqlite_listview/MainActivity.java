package com.example.demo_t1_sqlite_listview;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.demo_t1_sqlite_listview.database.AdressbuchAdapter;
import com.example.demo_t1_sqlite_listview.database.AdressbuchOpenHandler;

public class MainActivity extends Activity {
	private AdressbuchOpenHandler dbHandler;	
	private AdressbuchAdapter adapter;
	private ListView liste;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liste = (ListView)findViewById(R.id.liste);
        dbHandler = new AdressbuchOpenHandler(this);
        
        Cursor c = dbHandler.query();        
        
        
        adapter = new AdressbuchAdapter(this, c);
        
        liste.setAdapter(adapter);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
