package com.example.joduro_claimzer;

//import java.util.Calendar;
//import java.util.Date;

public class ClaimsListController {
	
	// Lazy Singleton
	private static ClaimsList claimsList = null;
	
	static public ClaimsList getClaimsList() {
		//!!!!!!!!!!!!!!!!!!NEED TO load claimslist
		/*
		claimsList = new ClaimsList();
		Date date1;
		Date date2; 
		
		//code stolen from stack overflow https://stackoverflow.com/questions/5165428/how-to-set-time-to-a-date-object-in-java Jan 2015
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,17);
		cal.set(Calendar.MINUTE,30);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		
		date1 = cal.getTime();
		date2 = cal.getTime();
		Claim claim = new Claim("John", date1, date2, "OPEN"  );
		
		claimsList.addClaim(claim);
		//
		*/
		if (claimsList == null) {
			claimsList = new ClaimsList();
		}
		return claimsList;
		
	}
	
	public void addClaim(Claim claim) {
		getClaimsList().addClaim(claim);
	}
}
