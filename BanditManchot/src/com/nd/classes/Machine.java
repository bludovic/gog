package com.nd.classes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.nd.classes.parameters.Parameters;
import com.nd.exceptions.BetException;
import com.nd.interfaces.IAction;

public class Machine implements Runnable {

	private List<IAction>	playable	= new ArrayList<>();
	private List<Thread>	threads		= new ArrayList<>();
	private boolean			running		= true;
	private boolean			playing		= true;
	private boolean			restart		= false;
	private Parameters		param;
	private double			bet;
	private Player			player;

	public Machine(Parameters param) {
		this.param = param;
		setPlayable();
	}

	@Override
	public void run() {

		while (playing) {

			System.out.println("Vous disposez de " + player.getAmount() + " euros");
			System.out.println("Combien souhaitez-vous miser?");
			this.bet = betRequest();

			restart = false;
			threads.clear();
			for (int i = 0; i < playable.size(); i++) {
				Thread thread = new Thread(playable.get(i));
				this.threads.add(thread);
				this.threads.get(i).start();
			}
			while (running) {
				running = true;
				this.display();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!playable.get(playable.size() - 1).isRolling()) {
					this.running = false;
					this.displayWinnings();
				}
			}

			// Waitint
			while (!restart) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void display() {
		String values = "", linesUp = "", linesUp2 = "", linesDown = "";

		for (int i = 0; i < playable.size(); i++) {
			linesUp = linesUp + "  ___  ";
			linesUp2 = linesUp2 + " |   | ";
			linesDown = linesDown + " |___| ";
			values = values + " | " + playable.get(i).getResult() + " | ";
		}
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println(linesUp);
		System.out.println(linesUp2);
		System.out.println(values);
		System.out.println(linesDown);
	}

	private void setPlayable() {
		for (int i = 0; i < param.getNumberPlayable(); i++) {
			IAction play = null;
			try {
				play = (IAction) param.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			playable.add(play);
		}
	}

	public void triggerNextPlayable() {

		if (running) {
			for (int i = 0; i < param.getNumberPlayable(); i++) {
				if (!playable.get(i).isSlowingDown()) {
					playable.get(i).stop();
					break;
				}
			}
		} else {
			restart = true;
			running = true;
		}
	}

	private void displayWinnings() {

		int temp, max = 1;
		for (int i = 0; i < playable.size(); i++) {
			temp = 1;
			for (int j = 0; j < i; j++) {
				if (playable.get(i).getResult() == playable.get(j).getResult()) {
					temp++;
				}
			}
			if (temp > max) {
				max = temp;
			}
		}
		System.out.println("\n Vous avez obtenu " + max + " valeurs identiques!");
		double winnings = calculateWinnings(max);

		if (max > 1) {
			System.out.println("\n Vous avez obtenu " + max + " valeur(s identiques!");
			System.out.println("Vos gains s'élevent à " + winnings + " euros.");
		} else {
			System.out.println("Perdu!!!");
		}

	}

	private double calculateWinnings(int max) {
		double gain;
		switch (max) {
		case 1:
			gain = 0;
			break;
		case 2:
			gain = bet * 1.5;
			break;
		case 3:
			gain = bet * 5;
			break;
		default:
			gain = bet * 10;
			break;
		}
		return gain;
	}

	private double betRequest() {
		boolean validating = true;
		bet = 0;
		while (validating) {
			validating = false;
			try {

				if (bet > this.getParam().getMaximumBet()) {
					throw new BetException("Le montant de votre mise dépasse le seuil autorisé (max = "
							+ this.getParam().getMaximumBet() + " euros).");
				}
				if (bet > player.getAmount()) {
					throw new BetException("Vous ne disposez pas de suffisamment de fonds.\n Il vous reste "
							+ player.getAmount() + " euros.");
				}

			} catch (InputMismatchException e) {
				validating = true;
				System.out.println("Atteniton. veuillez entrez un nombre s'il-vous-plaît.\n");
			} catch (BetException e) {
				validating = true;
				System.out.println(e.getMessage());
			}
		}
		return bet;

	}

	public Parameters getParam() {
		return param;
	}

	public void setParam(Parameters param) {
		this.param = param;
	}

	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public void setPlayer(Player joueur) {
		this.player = joueur;
	}
}
