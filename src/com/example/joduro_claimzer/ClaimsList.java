/*
 Copyright 2015 Jeffrey Oduro

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES 
OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

// Contains a list of claims and manages simple actions like adding, removing, updating, and loading it. 

package com.example.joduro_claimzer;

import java.util.ArrayList;
import java.util.Date;

public class ClaimsList {
	
	protected ArrayList<Claim> claimsList;
	protected ArrayList<Listener> listeners;
	
	public ClaimsList(){
		claimsList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	public void addClaim(Claim claim) {
		//sort the claims by date
		int i = 0;
		for (; i < claimsList.size();++i){
			if(claim.getStartDate().before(claimsList.get(i).getStartDate())){
				break;
			}
		}
		claimsList.add(i, claim);
		notifyListeners();
	}
	
	public Claim getClaim(int position){
		return claimsList.get(position);
	}

	public void notifyListeners() {
		for (Listener listner : listeners) {
			listner.update();
		}	
	}
	public void addListener(Listener l) {
		listeners.add(l);
	}
	public void removerListener(Listener l) {
		listeners.remove(l);
	}
	
	public ArrayList<Claim> getClaims(){
		return claimsList;
		
	}
	
	public void updateClaim(int claimPos, String name, Date startDate,
			Date endDate, String status) {
		Claim claim = claimsList.get(claimPos);
		claim.setName(name);
		claim.setEndDate(endDate);
		claim.setStartDate(startDate);
		claim.setStatus(status);
		notifyListeners();
	}
	
	public void remove(int pos) {
		claimsList.remove(pos);
		notifyListeners();
	}
	
	public void loadClaims(ArrayList<Claim> claims) {
		claimsList = claims;
		
	}
}
