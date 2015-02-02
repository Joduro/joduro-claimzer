/*
 Copyright 2015 Jeffrey Oduro

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES 
OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

// Is the sole owner and controller of a ClaimsList that is store all of the claims

package com.example.joduro_claimzer;


import java.util.ArrayList;
import java.util.Date;
import android.util.Log;

public class ClaimsListController {
	
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
	
	public Claim getClaim(int pos){
		return getClaimsList().getClaim(pos);
	}

	public void removeClaim(int pos) {
		getClaimsList().remove(pos);
	}

	public void updateClaim(int claimPos, String name, Date startDate,
			Date endDate, String status) {
		
		getClaimsList().updateClaim(claimPos, name, startDate, endDate, status);
		
	}

	public static void loadClaims(ArrayList<Claim> claims) {
		getClaimsList().loadClaims(claims);
	}

}