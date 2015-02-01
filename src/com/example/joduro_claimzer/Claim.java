package com.example.joduro_claimzer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.util.Log;

public class Claim {

	private String name;
	//private String desc;
	private Date startDate;
	private Date endDate;
	private String status;
	
	private ArrayList<Expense> expensesList;
	
	private String total;
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
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
		
		if (temp.startsWith("{") && temp.length() > 2){
			temp = temp.replace("{", "");
			temp = temp.replace("}", "");
			//temp = temp.substring(1, -1);
			String[] prices = temp.split(", ");
			
			String tempTotal = "";
			for (String price : prices){
				String[] items = price.split("=");
				tempTotal+= items[1];
				tempTotal+= items[0];
				tempTotal+= ", ";
			}			
			
			//tempTotal = tempTotal.substring(0, -1);
			
			setTotal(tempTotal);
			
	    	ClaimsListController.getClaimsList().notifyListeners();
		}
	}
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
	
	public Claim(String name, Date startDate, Date endDate,
			String status/*, ArrayList<Expense> expensesList*/) {
		super();
		this.name = name;
		//this.desc = desc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.total = "0";
		this.expensesList = new ArrayList<Expense>();
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
	
	public void removeExpense(Expense expense) {
		expensesList.remove(expense);
	}
	
	public ArrayList<Expense> getExpenses() {
		return expensesList;
	}
	
	public void updateExpense(int x, Expense expense) {
		expensesList.set(x, expense);
	}
	
	public String toString() {
		
		DateFormat format = new SimpleDateFormat("EEE MMM DD yyyy", Locale.ENGLISH);
		
		return format.format(getStartDate()) + " - " + getName() + "\n" + "Total: " + getTotal() + "\n" + getStatus();
	}
	
	/*
	public void loadClaim()
	{
		
	}
	
	public void saveClaim()
	{
		
	}
	*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
*/
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
}
