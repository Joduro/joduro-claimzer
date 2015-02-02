/*
 Copyright 2015 Jeffrey Oduro

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES 
OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.joduro_claimzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	
	private static final String FILENAME = "claims.sav";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loadClaims();
        
        ListView listView = (ListView) findViewById(R.id.ClaimsListView);
        final ArrayList<Claim> list = ClaimsListController.getClaimsList().getClaims();
        final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(claimAdapter);
        
        // Added a change observer
        ClaimsListController.getClaimsList().addListener(new Listener() {
        	@Override
        	public void update() {
        		claimAdapter.notifyDataSetChanged();
        		saveClaims();
        	}
        });
        
        //Bring up the edit claim screen on long click
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {    
				Intent intent = new Intent(MainActivity.this, EditClaimActivity.class);
		        intent.putExtra("claim_position", position);
		        startActivity(intent);
		        
				return false;
			}
        	
        });
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
		    	ClaimsListController ct = new ClaimsListController();
		    	Claim claim = ct.getClaim(position);
				if (claim.getStatus().equals("Submitted") || claim.getStatus().equals("Approved")){
					Toast.makeText(MainActivity.this,"Cannot edit Expenses for this " + claim.getStatus() + " Claim",Toast.LENGTH_SHORT).show();
				}
				else {
					//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android
					// accessed Jan 2015  
					Intent intent = new Intent(MainActivity.this, ListExpensesActivity.class);
			        intent.putExtra("claim_position", position);
			        startActivity(intent);
				}	
			}  	
        });   
    }
    
    protected void onResume() {
    	super.onResume();
    }
    public void editClaimScreen(View v){
        // Switch activity to edit claim
        Intent intent = new Intent(MainActivity.this, EditClaimActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    // Adapted from my copy of LonelyTwitter https://github.com/Joduro/lonelyTwitter Jan 2015
	public void saveClaims(){
		Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE); //open file output
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(ClaimsListController.getClaimsList().getClaims(), osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadClaims() {
		Gson gson = new Gson();
		ArrayList<Claim> claims = new ArrayList<Claim>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			InputStreamReader in = new InputStreamReader(fis);
			
			// Adapted from http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html Jan 2015
			Type typeOfT = new TypeToken<ArrayList<Claim>>(){}.getType();
			claims = gson.fromJson(in, typeOfT);
			fis.close();
			
			ClaimsListController.loadClaims(claims);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


