package com.nd.classes;

public class Player {

	private double	amount;
	private String	name, firstName;

	public Player(String firstName, String name, double amount) {
		this.name = name;
		this.firstName = firstName;
		this.amount = amount;
	}

	public void play(Machine machine) {
		System.out.println("Bonjour " + firstName + " " + name);
		machine.setPlayer(this);
		Thread t = new Thread(machine);
		t.start();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
