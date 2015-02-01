package com.example.joduro_claimzer;

import java.util.Date;

public class Expense {
	
	private Date date;
	private String desc;
	private String category;
	private double cost;
	private String currency;

	public String toString() {
		return getDesc();
	}
	
	public Expense(Date date, String desc, /*String category,*/ double cost,
			String currency) {
		super();
		this.date = date;
		this.desc = desc;
		//this.category = category;
		this.cost = cost;
		this.currency = currency;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
