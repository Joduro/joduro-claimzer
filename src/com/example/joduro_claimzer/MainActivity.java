package com.example.joduro_claimzer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView listView = (ListView) findViewById(R.id.ClaimsListView);
        final ArrayList<Claim> list = ClaimsListController.getClaimsList().getClaims();
        final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(claimAdapter);
        
        // Added a change observer
        ClaimsListController.getClaimsList().addListener(new Listener() {
        	@Override
        	public void update() {
        		//Log.d("UPDATING CLAIMSLIST", "Claims has size: " + list.size());
        		//list.clear();
        		//ArrayList<Claim> claims = ClaimsListController.getClaimsList().getClaims();
        		//list.addAll(claims);
        		//Log.d("UPDATED CLAIMSLIST", "Claims has size: " + list.size());
        		claimAdapter.notifyDataSetChanged();
        	}
        });
        
        //Bring up the edit claim screen on long click
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this,list.get(position).getName(),Toast.LENGTH_SHORT).show();
				
				//Claim claim = list.get(position);
				//ClaimsListController.getClaimsList().removeClaim(claim);
				
				//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android
				// accessed Jan 2015      
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
				//Adapted from code posted by user914425 on https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android
				// accessed Jan 2015  
				Intent intent = new Intent(MainActivity.this, ListExpensesActivity.class);
		        intent.putExtra("claim_position", position);
		        startActivity(intent);
				
			}
        	
        	
        });
        
    }


    protected void onResume() {
    	super.onResume();
    	//Toast.makeText(this,"Resuming Main",Toast.LENGTH_SHORT).show();
    	
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
}
