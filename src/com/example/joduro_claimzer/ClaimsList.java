package com.example.joduro_claimzer;

import java.util.ArrayList;

import android.util.Log;

public class ClaimsList {
	
	protected ArrayList<Claim> claimsList;
	protected ArrayList<Listener> listeners;
	
	public ClaimsList(){
		claimsList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	public void addClaim(Claim claim) {
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

	public void updateClaim(int position, Claim claim){
		claimsList.set(position, claim);
		notifyListeners();
	}
	
	private void notifyListeners() {
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
	
	public void removeClaim(Claim claim){
		claimsList.remove(claim);
	}
	
	public ArrayList<Claim> getClaims(){
		Log.d("GETTING CLAIMS", "Claims has size " + claimsList.size());
		return claimsList;
		
	}
	
	
}
