package com.nd.classes;

import java.util.Scanner;

public class Trigger extends Thread {

	private Machine	machine;
	private Scanner	sc;

	public Trigger(Machine machine) {
		this.machine = machine;
	}

	@Override
	public void run() {

		while (true) {
			sc = new Scanner(System.in);
			sc.nextLine();
			machine.triggerNextPlayable();
		}
	}

}