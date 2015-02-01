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

	protected int claimPos;
	
	protected Claim claim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_expense);
		
		//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android 
		// accessed Jan 2015 
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			
			claimPos = extras.getInt("claim_position");
			
	    	ClaimsListController ct = new ClaimsListController();
	    	claim = ct.getClaim(claimPos);
	    	
	    	updatingExpPos = -1;
		}	
		else{
			finish();
		}
	}
	
	protected void onStart(){
		super.onStart();
		
		//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android 
		// accessed Jan 2015 
		
		Bundle extras = getIntent().getExtras();
		if (extras.size() > 1) {
			updatingExpPos = extras.getInt("expense_position");
			
			Expense exp = claim.getExpenses().get(updatingExpPos);
			
	    	//ClaimsListController ct = new ClaimsListController();
	    	//Claim claim = ct.getClaim(updatingExpPos);
	    	
	    	//Fill in the claim to match the existing one

	    	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	    	
	    	EditText descEditText = (EditText) findViewById( R.id.expDescEditText);  
	    	descEditText.setText(exp.getDesc());
	    	
	    	EditText dateEditText = (EditText) findViewById( R.id.expDateEditText);  
	    	dateEditText.setText(format.format(exp.getDate()));
	    	
	    	EditText costEditText = (EditText) findViewById( R.id.expCostEditText);  
	    	costEditText.setText((Double.toString(exp.getCost())));
			
	    	EditText currEditText = (EditText) findViewById( R.id.expCurrencyEditText);  
	    	currEditText.setText(exp.getCurrency());
	    	
	    	Button saveButton = (Button) findViewById(R.id.expSaveButton);
	    	saveButton.setText("Update");
		}
		else{
			updatingExpPos = -1;
		}
	}
	
	protected int updatingExpPos;
	
    public void expSaveButton(View v){
        // Switch activity to edit claim

    	//Add the claim
    	EditText descEditText = (EditText) findViewById( R.id.expDescEditText);  
    	String descText = descEditText.getText().toString();
    	
    	EditText dateEditText = (EditText) findViewById( R.id.expDateEditText);  
    	String dateText = dateEditText.getText().toString();
    	
    	EditText costEditText = (EditText) findViewById( R.id.expCostEditText);  
    	String costText = costEditText.getText().toString();
    	
    	
    	// Code Adapted from WhiteFang34's post on https://stackoverflow.com/questions/5769669/convert-string-to-double-in-java
    	// accessed Jan 2015
    	double cost = Double.parseDouble(costText);
    	
    	EditText currEditText = (EditText) findViewById( R.id.expCurrencyEditText);  
    	String currText = currEditText.getText().toString();
    	
    	//code adapted from https://stackoverflow.com/questions/4216745/java-string-to-date-conversion 
    	// accessed Jan 2015
    	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    	
    	Date date = null;
		try {
			date = format.parse(dateText);
		} catch (ParseException e) {
			Toast.makeText(this,"Improper Date",Toast.LENGTH_SHORT).show();
		}
    	
		if (!(date == null)) { 
	    	//---
	    	Expense expense = new Expense(date, descText, cost, currText);
	    	
	    	//ClaimsListController ct = new ClaimsListController();
	    	//Claim claim = ct.getClaim(claimPos);
	    	
	    	//case for updating a claim
			if (updatingExpPos >= 0){
				claim.updateExpense(updatingExpPos, expense);
				Toast.makeText(this,"Updated Expense " + descText,Toast.LENGTH_SHORT).show();
				updatingExpPos = -1;
			}
			
			//case for creating a new claim
			else{
		    	claim.addExpense(expense); 	
		    	Toast.makeText(this,"New Expense " + descText,Toast.LENGTH_SHORT).show();
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
