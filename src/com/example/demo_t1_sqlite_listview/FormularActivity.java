package com.example.demo_t1_sqlite_listview;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.demo_t1_sqlite_listview.database.AdressbuchOpenHandler;

public class FormularActivity extends Activity implements OnClickListener{
    private Button neuerKontaktSpeichern;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formular_activity);
        
        neuerKontaktSpeichern = (Button) findViewById(R.id.btnNeuerKontaktSpeichern);
        neuerKontaktSpeichern.setOnClickListener(this);
    
        
    }

    
    /**
    * Interface OnClickListener
    */
    @Override
    public void onClick(View v) {        	
    	
    	final EditText txtName = 
    			(EditText) findViewById(R.id.editText1);
    	final EditText txtVorname = 
    			(EditText) findViewById(R.id.editText2);
    	final EditText txtBday = 
    			(EditText) findViewById(R.id.editText3); 
    	
    	final AdressbuchOpenHandler adressbuchOpenHandler = new AdressbuchOpenHandler(getBaseContext()); 
    	
    	
    	adressbuchOpenHandler.insert(txtName.getText().toString(), txtVorname.getText().toString(), txtBday.getText().toString(), null);    	    	
    	    	
    	
    	finish();
    }

	
}
