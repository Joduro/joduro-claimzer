/*
 Copyright 2015 Jeffrey Oduro

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES 
OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

// Used to store and control the data associated with each Travel Claim including a list of expenses 

package com.example.joduro_claimzer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Claim {

	private String name;
	private Date startDate;
	private Date endDate;
	private String status;
	
	private ArrayList<Expense> expensesList;
	
	private String total;
	
	public Claim(String name, Date startDate, Date endDate, String status) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.total = "0";
		this.expensesList = new ArrayList<Expense>();
	}
	
	public String toString() {
		
		DateFormat format = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
		
		return format.format(getStartDate()) + " - " + getName() + "\n" + "Total: " + getTotal() + "\n" + getStatus();
	}
	
	public void addExpense(Expense expense) {
		int i = 0;
		for (; i < expensesList.size();++i){
			if(expense.getDate().before(expensesList.get(i).getDate())){
				//expensesList.add(i, expense);
			}
		}
		expensesList.add(i, expense);
	}
	
	public void removeExpense(int pos) {
		expensesList.remove(pos);
	}
	public void updateExpense(int x, Expense expense) {
		expensesList.set(x, expense);
	}
	
	public ArrayList<Expense> getExpenses() {
		return expensesList;
	}
	
	// Creates a map of Currency:Cost for all of the claim's expenses then converts it to a string to display the total price for the claim. 
	public void updateTotal(){
		HashMap<String, Double> counts = new HashMap<String, Double>();
		
		for (Expense expense : getExpenses()){
			if(counts.containsKey(expense.getCurrency())){
				counts.put(expense.getCurrency(), expense.getCost() + counts.get(expense.getCurrency()));
			}
			else {
				counts.put(expense.getCurrency(), expense.getCost());
			}
		}
		
		String temp = counts.toString();
		
		//convert the default toString for HashMap inot something more appropriate
		if (temp.startsWith("{") && temp.length() > 2){
			temp = temp.replace("{", "");
			temp = temp.replace("}", "");
			String[] prices = temp.split(", ");
			
			String tempTotal = "";
			for (String price : prices){
				String[] items = price.split("=");
				tempTotal+= items[1];
				tempTotal+= items[0];
				tempTotal+= ", ";
			}			
			setTotal(tempTotal);
			
			// since this is called each time an expense is made or edited this call to MainActivity's 
			// listener will update it's values and save the entire new claimslist to disk 
	    	ClaimsListController.getClaimsList().notifyListeners();
		}
	}
	
	//formats the claim and its expesnes to be emailed
	public String getEmailBody() {
		String body = "";
		DateFormat format = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
		
		body += "Claim: " + getName() + "\n" + format.format(getStartDate()) + " - " + format.format(getEndDate()) + "\n" 
				+ "Total: " + getTotal() + "\n===================================================";
		
		for (Expense e : expensesList){
			body += "\n" + format.format(e.getDate()) + " | " + e.getCategory() + " | " + e.getDesc() + " | " + e.getCost() + " " + e.getCurrency() + "\n-------------------------------";
					
		}
		return body;
	}
	
	// Returns the spinner position for each "status" in the layout. 
	public int getStatusPos() {
		if (status.equals("In progress")) 
			return 0;
		if (status.equals("Submitted"))
			return 1;
		if (status.equals("Returned"))
			return 2;
		else
			return 3;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}
