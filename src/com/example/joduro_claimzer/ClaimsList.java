package com.example.joduro_claimzer;

import java.util.ArrayList;

public class ClaimsList {
	
	private ArrayList<Claim> claimsList;
	
	public ClaimsList(){
		claimsList = new ArrayList<Claim>();
	}
	public void addClaim(Claim claim) {
		/*
		int i = 0;
		for (; i < claimsList.size();++i){
			if(claim.getStartDate().before(claimsList.get(i).getStartDate())){
				break;
			}
		}
		claimsList.add(i, claim);
	}
	*/
		claimsList.add(claim);
	}
	
	public void removeClaim(Claim claim){
		claimsList.remove(claim);
	}
	
	public void getClaims(){
		
	}
	

}
