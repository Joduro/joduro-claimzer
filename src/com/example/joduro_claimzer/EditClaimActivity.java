package com.example.joduro_claimzer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_claim);
		
		updatingClaimPos = -1;
		
	}
	
	protected void onStart(){
		super.onStart();
		
		//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android 
		// accessed Jan 2015 
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			updatingClaimPos = extras.getInt("claim_position");
			
	    	ClaimsListController ct = new ClaimsListController();
	    	Claim claim = ct.getClaim(updatingClaimPos);
	    	
	    	//Fill in the claim to match the existing one

	    	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	    	
	    	EditText nameEditText = (EditText) findViewById( R.id.claimNameEditText);  
	    	nameEditText.setText(claim.getName());
	    	
	    	EditText sdEditText = (EditText) findViewById( R.id.claimStartDateEditText);  
	    	sdEditText.setText(format.format(claim.getStartDate()));
	    	
	    	EditText edEditText = (EditText) findViewById( R.id.claimEndDateEditText);  
	    	edEditText.setText(format.format(claim.getEndDate()));
	    	
	    	/*
	    	if (claim.getStatus().equals("Submitted") || claim.getStatus().equals("Approved")) {
	    		nameEditText.setEnabled(false);
	    		sdEditText.setEnabled(false);
	    		edEditText.setEnabled(false);
	    	}
	    	*/
	    	
	    	Spinner statusSpinner = (Spinner) findViewById( R.id.claimStatusSpinner);  
	    	
	    	int statusPos = claim.getStatusPos();
	    	
	    	statusSpinner.setSelection(statusPos);
	    	
			
	    	Button saveButton = (Button) findViewById(R.id.claimSaveButton);
	    	saveButton.setText("Update");
		}
		else {
			updatingClaimPos = -1;
		}
	}
	
	protected int updatingClaimPos;
	
	public void setClaimPos(int x) {
		this.updatingClaimPos = x;
	}
	
    public void claimSaveButton(View v){
        // Switch activity to edit claim

    	//Add the claim
    	EditText NameEditText = (EditText) findViewById( R.id.claimNameEditText);  
    	String nameText = NameEditText.getText().toString();
    	
    	EditText sdEditText = (EditText) findViewById( R.id.claimStartDateEditText);  
    	String sdText = sdEditText.getText().toString();
    	
    	EditText edEditText = (EditText) findViewById( R.id.claimEndDateEditText);  
    	String edText = edEditText.getText().toString();
    	
    	// Code adapted from http://www.mkyong.com/android/android-spinner-drop-down-list-example/
    	Spinner statusSpinner = (Spinner) findViewById( R.id.claimStatusSpinner);  
    	String statusText = String.valueOf(statusSpinner.getSelectedItem());
    	
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
	    	
	    	
	    	ClaimsListController ct = new ClaimsListController();
	    	
	    	//case for updating a claim
			if (updatingClaimPos >= 0){			
				ct.updateClaim(updatingClaimPos, nameText, sdDate, edDate, statusText);
				Toast.makeText(this,"Updated Claim " + nameText,Toast.LENGTH_SHORT).show();
				updatingClaimPos = -1;
			}
			
			//case for creating a new claim
			else{
				Claim claim = new Claim(nameText, sdDate, edDate, statusText );
		    	ct.addClaim(claim); 	
		    	Toast.makeText(this,"New Claim " + nameText,Toast.LENGTH_SHORT).show();
			}
	    	//!!!!!NEED TO SAVE IT TO DISK SOMEHOW
			
	    	finish();
	    	
	        //Intent intent = new Intent(EditClaimActivity.this, MainActivity.class);
	        //startActivity(intent);
		}
        
    }
    public void claimDeleteButton(View v){
    	if (updatingClaimPos >= 0){
    		new ClaimsListController().removeClaim(updatingClaimPos);
    	}
    	Toast.makeText(this,"Claim Deleted",Toast.LENGTH_SHORT).show();
    	finish();
    }
    
    private void emailClaimTo(Claim claim, String address){
		//code adapted from Igor Popov's post at https://stackoverflow.com/questions/8284706/send-email-via-gmail
		Intent send = new Intent(Intent.ACTION_SENDTO);
		
		String uriText = "mailto:" + Uri.encode(address) + 
		          "?subject=" + Uri.encode("Travel Claim \"" + claim.getName() + "\" Expenses") + 
		          "&body=" + Uri.encode(claim.getEmailBody());
		Uri uri = Uri.parse(uriText);

		send.setData(uri);
		startActivity(Intent.createChooser(send, "Send mail..."));
	}
    
    public void sendEmailButton(View v){
    	EditText emailEditText = (EditText) findViewById( R.id.claimEmailEditText);  
    	String address = emailEditText.getText().toString();
    	
    	if(address.length() > 5 && address.contains("@") && address.contains(".")) {
        	if (updatingClaimPos < 0){
        		Toast.makeText(this,"Claim has no Expenses",Toast.LENGTH_SHORT).show();
        	}
        	else {
	    		/*
	    		// Adapted from doraemon's post at https://stackoverflow.com/questions/8284706/send-email-via-gmail
	    		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
	    	            "mailto",address, null));
	    		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
	    		startActivity(Intent.createChooser(emailIntent, "Send email..."));
	    		
	    		}
	    		 */
	    		emailClaimTo(ClaimsListController.getClaimsList().getClaim(updatingClaimPos), address);
        	}
    	}
    	else{
    		Toast.makeText(this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
    	}
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
