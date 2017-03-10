package com.nd.classes;

import java.util.Scanner;

public class Trigger extends Thread {

	private Machine machine;

	public Trigger(Machine machine) {
		this.machine = machine;
	}

	@Override
	public void run() {

		for (int i = 0; i < machine.getParam().getNumberPlayable(); i++) {
			this.machine.setSc(new Scanner(System.in));
			this.machine.getSc().nextLine();
			this.machine.triggerNextPlayable();
		}
	}

}
