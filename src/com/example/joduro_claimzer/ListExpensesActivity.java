/*
 Copyright 2015 Joduro

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES 
OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

// Handles the layout for showing the expenses of an individual claim and adding/updating individual expense items

package com.example.joduro_claimzer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ListExpensesActivity extends Activity {

	protected int claimPos; 
	
	protected ArrayAdapter<Expense> expenseAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_expenses);
		
		//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android 
		// accessed Jan 2015 
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			
			claimPos = extras.getInt("claim_position");
			
	    	ClaimsListController ct = new ClaimsListController();
	    	Claim claim = ct.getClaim(claimPos);
	    	
	    	setInfo(claim);
	    	
	    	//EditText descEditText = (EditText) findViewById( R.id.expDescEditText);  
	    	//descEditText.setText(exp.getDesc());
	    	
	        ListView listView = (ListView) findViewById(R.id.expensesListView);
	        ArrayList<Expense> list = claim.getExpenses();
	        /*ArrayAdapter<Expense>*/ expenseAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list);
	        listView.setAdapter(expenseAdapter);
	        
	        listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android
					// accessed Jan 2015  
					Intent intent = new Intent(ListExpensesActivity.this, EditExpenseActivity.class);
					intent.putExtra("claim_position", claimPos);
			        intent.putExtra("expense_position", position);
			        startActivity(intent);
					
				}
	        	
	        	
	        });
		}
		//if there is somehow no claim bundled with the intent just quit
		else {
			finish();
		}
	}
	
	//fill in info about the claim
	protected void setInfo(Claim claim){
    	TextView expEditText = (TextView) findViewById( R.id.ExpensesTextView);  
    	DateFormat format = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
    	expEditText.setText("Claim: " + claim.getName() + "\n" + format.format(claim.getStartDate()) + " - " + format.format(claim.getEndDate()) + "\n" + "Total: " + claim.getTotal());
    	expEditText.setBackgroundColor(Color.BLACK);
    	expEditText.setTextColor(Color.WHITE);
	}
    protected void onResume() {
    	super.onResume();
    	expenseAdapter.notifyDataSetChanged();

    	Claim claim = new ClaimsListController().getClaim(claimPos);
    	setInfo(claim);
    }
    
    public void editExpenseScreen(View v){
        // Switch activity to edit claim
        Intent intent = new Intent(ListExpensesActivity.this, EditExpenseActivity.class);
        intent.putExtra("claim_position", claimPos);
        startActivity(intent);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_expenses, menu);
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
