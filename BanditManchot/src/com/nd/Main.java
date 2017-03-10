package com.nd;

import com.nd.classes.Machine;
import com.nd.classes.Player;
import com.nd.classes.Trigger;
import com.nd.classes.parameters.JAXB;
import com.nd.classes.parameters.Parameters;

public class Main {

	public static void main(String[] args) {

		Parameters param = JAXB.importParameters();
		param.getParameters();
		Machine machine = new Machine(param);
		Trigger trig = new Trigger(machine);
		trig.start();

		Player joueur = new Player("Jack", "LaChance", 75);
		joueur.play(machine);

	}
}
