package com.example.joduro_claimzer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_claim);
	}
	
    public void claimSaveButton(View v){
        // Switch activity to edit claim
    	/*
    	//Add the claim
    	EditText NameEditText = (EditText) findViewById( R.id.claimNameEditText);  
    	String nameText = NameEditText.getText().toString();
    	
    	EditText sdEditText = (EditText) findViewById( R.id.ClaimStartDateEditText);  
    	String sdText = sdEditText.getText().toString();
    	
    	EditText edEditText = (EditText) findViewById( R.id.claimEndDateEditText);  
    	String edText = edEditText.getText().toString();
    	
    	//code snippit stolen from https://stackoverflow.com/questions/4216745/java-string-to-date-conversion Jan 2015
    	DateFormat format = new SimpleDateFormat("d/MM,yyyy", Locale.ENGLISH);
    	
    	Date sdDate = null;
    	Date edDate = null;
		try {
			sdDate = format.parse(sdText);
			edDate = format.parse(edText);
		} catch (ParseException e) {
			Toast.makeText(this,"Improper Date",Toast.LENGTH_SHORT).show();
		}
    	
    	
    	//---
    	Claim claim = new Claim(nameText, sdDate, edDate, "open" );
    	
    	ClaimsListController ct = new ClaimsListController();
    	ct.addClaim(claim);
    	
    	Toast.makeText(this,claim.getName(),Toast.LENGTH_SHORT).show();
    	
    	//!!!!!NEED TO SAVE IT TO DISK SOMEHOW
    	
    	 */
        Intent intent = new Intent(EditClaimActivity.this, MainActivity.class);
        startActivity(intent);
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
