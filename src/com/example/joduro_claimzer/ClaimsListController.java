package com.example.joduro_claimzer;

import android.util.Log;

//import java.util.Calendar;
//import java.util.Date;

public class ClaimsListController {
	
	// Lazy Singleton
	private static ClaimsList claimsList = null;
	
	static public ClaimsList getClaimsList() {

		if (claimsList == null) {
			Log.d("CREATING NEW CLAIMSLIST", "Claimslist is null so creating new");
			claimsList = new ClaimsList();
		}
		return claimsList;	
	}
	
	public void addClaim(Claim claim) {
		getClaimsList().addClaim(claim);
	}
	
	public Claim getClaim(int position){
		return getClaimsList().getClaim(position);
	}
	
	public void updateClaim(int position, Claim claim){
		getClaimsList().updateClaim(position, claim);
	}
}
