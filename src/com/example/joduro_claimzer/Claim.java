package com.example.joduro_claimzer;

import java.util.ArrayList;
import java.util.Date;

public class Claim {

	private String name;
	//private String desc;
	private Date startDate;
	private Date endDate;
	private String status;
	
	private ArrayList<Expense> expensesList;
	
	public Claim(String name, Date startDate, Date endDate,
			String status/*, ArrayList<Expense> expensesList*/) {
		super();
		this.name = name;
		//this.desc = desc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		//this.expensesList = expensesList;
	}

	public void addExpense(Expense expense) {
		/*
		int i = 0;
		for (; i < expensesList.size();++i){
			if(expense.getDate().before(expensesList.get(i).getDate())){
				//expensesList.add(i, expense);
			}
		}
		expensesList.add(i, expense);
		*/
		expensesList.add(expense);
	}
	
	public void removeExpense(Expense expense) {
		expensesList.remove(expense);
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
