/*
 Copyright 2015 Joduro

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES 
OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

// Stores data associated with an expense item

package com.example.joduro_claimzer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Expense {
	
	private Date date;
	private String desc;
	private String category;
	private double cost;
	private String currency;

	public Expense(Date date, String desc, String category, double cost,
			String currency) {
		super();
		this.date = date;
		this.desc = desc;
		this.category = category;
		this.cost = cost;
		this.currency = currency;
	}

	public String toString() {
		
		DateFormat format = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
		
		return format.format(getDate()) + " | " + getDesc() + "\n" + getCategory() + " | " + getCost() + " " + getCurrency();
		
	}
	

	// getters and setters
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
