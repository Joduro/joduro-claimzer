package com.example.joduro_claimzer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditExpenseActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_expense);
		
		updatingExpPos = -1;
	}
	
	protected void onStart(){
		super.onStart();
		
		//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android 
		// accessed Jan 2015 
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			updatingExpPos = extras.getInt("expense_position");
			
	    	ClaimsListController ct = new ClaimsListController();
	    	Claim claim = ct.getClaim(updatingExpPos);
	    	
	    	//Fill in the claim to match the existing one

	    	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	    	
	    	EditText NameEditText = (EditText) findViewById( R.id.expNameEditText);  
	    	NameEditText.setText(claim.getName());
	    	
	    	EditText sdEditText = (EditText) findViewById( R.id.expDateEditText);  
	    	sdEditText.setText(format.format(claim.getStartDate()));
	    	
	    	EditText edEditText = (EditText) findViewById( R.id.expCostEditText);  
	    	edEditText.setText(format.format(claim.getEndDate()));
			
	    	Button saveButton = (Button) findViewById(R.id.claimSaveButton);
	    	saveButton.setText("Update");
		}
		else {
			updatingExpPos = -1;
		}
	}
	
	protected int updatingExpPos;
	
    public void expSaveButton(View v){
        // Switch activity to edit claim

    	//Add the claim
    	EditText NameEditText = (EditText) findViewById( R.id.expNameEditText);  
    	String nameText = NameEditText.getText().toString();
    	
    	EditText sdEditText = (EditText) findViewById( R.id.expDateEditText);  
    	String sdText = sdEditText.getText().toString();
    	
    	EditText edEditText = (EditText) findViewById( R.id.expCostEditText);  
    	String edText = edEditText.getText().toString();
    	
    	//code adapted from https://stackoverflow.com/questions/4216745/java-string-to-date-conversion 
    	// accessed Jan 2015
    	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    	
    	Date sdDate = null;
    	Date edDate = null;
		try {
			sdDate = format.parse(sdText);
			edDate = format.parse(edText);
		} catch (ParseException e) {
			Toast.makeText(this,"Improper Date",Toast.LENGTH_SHORT).show();
		}
    	
		if (!(sdDate == null || edDate == null || nameText == null)) { 
	    	//---
	    	Claim claim = new Claim(nameText, sdDate, edDate, "open" );
	    	
	    	ClaimsListController ct = new ClaimsListController();
	    	
	    	//case for updating a claim
			if (updatingClaimPos >= 0){
				ct.updateClaim(updatingClaimPos, claim);
				Toast.makeText(this,"Updated Claim " + nameText,Toast.LENGTH_SHORT).show();
				updatingClaimPos = -1;
			}
			
			//case for creating a new claim
			else{
		    	ct.addClaim(claim); 	
		    	Toast.makeText(this,"New Claim " + nameText,Toast.LENGTH_SHORT).show();
			}
	    	//!!!!!NEED TO SAVE IT TO DISK SOMEHOW
	    	
	    	finish();
	    	
	        //Intent intent = new Intent(EditClaimActivity.this, MainActivity.class);
	        //startActivity(intent);
		}
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_expense, menu);
		return true;
	}

}
