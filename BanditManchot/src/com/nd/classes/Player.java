package com.nd.classes;

public class Player {

	private double	amount;
	private String	name, firstName;

	public Player(String firstName, String name, double amount) {
		this.name = name;
		this.firstName = firstName;
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
