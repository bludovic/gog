package com.nd.classes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.nd.classes.parameters.Parameters;
import com.nd.exceptions.BetException;
import com.nd.interfaces.IAction;

public class Machine implements Runnable {

	private List<IAction>	playable	= new ArrayList<>();
	private List<Thread>	threads		= new ArrayList<>();
	private boolean			running, playing = true;
	private Parameters		param;
	private Player			player;
	private double			bet;
	private Scanner			sc;

	public Machine(Parameters param) {
		this.param = param;
		this.setPlayable();
	}

	@Override
	public void run() {

		System.out.println("Bonjour " + this.player.getFirstName() + " " + this.player.getName());

		while (this.playing) {

			this.running = true;

			this.betRequest();

			Trigger trigger = new Trigger(this);
			trigger.start();

			this.threads.clear();
			for (int i = 0; i < this.playable.size(); i++) {
				this.threads.add(new Thread(this.playable.get(i)));
				this.threads.get(i).start();
			}

			while (this.running) {
				this.display();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!this.playable.get(this.playable.size() - 1).isRolling()) {
					this.running = false;
					this.displayWinnings();
				}
			}
			this.requestRestart();

		}

	}

	private void requestRestart() {
		if (player.getAmount() == 0) {
			System.out.println("Vous êtes fauché!\nAu revoir.");
			this.playing = false;
			this.sc.close();
			return;
		}
		String resp = "";
		while (!(resp.toLowerCase().equals("y") || resp.toLowerCase().equals("n"))) {
			System.out.println("Rejouer? (y/n)");
			this.sc = new Scanner(System.in);
			resp = this.sc.nextLine();

		}
		if (resp.toLowerCase().equals("n")) {
			this.playing = false;
			System.out.println("Au revoir!");
			this.sc.close();
		}
	}

	public void display() {
		String values = "", linesUp = "", linesUp2 = "", linesDown = "";
		for (int i = 0; i < this.playable.size(); i++) {
			linesUp = linesUp + "  ___  ";
			linesUp2 = linesUp2 + " |   | ";
			linesDown = linesDown + " |___| ";
			values = values + " | " + this.playable.get(i).getResult() + " | ";
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
			this.playable.add(play);
		}
	}

	public void triggerNextPlayable() {
		for (int i = 0; i < this.param.getNumberPlayable(); i++) {
			if (!this.playable.get(i).isSlowingDown()) {
				this.playable.get(i).stop();
				break;
			}
		}
	}

	private void displayWinnings() {
		int nbIdenticalResults = this.countIdenticalResults();
		double winnings = this.calculateWinnings(nbIdenticalResults);
		if (nbIdenticalResults == 1) {
			System.out.println("Vous avez perdu!");
		} else {
			player.setAmount(this.player.getAmount() + winnings);
			System.out.println("\n Vous avez obtenu " + nbIdenticalResults + " valeurs identiques!");
			System.out.println("Vos gains s'élèvent à " + winnings + " euros.");
		}

	}

	private int countIdenticalResults() {
		int temp, nbIdenticalResults = 1;
		for (int i = 0; i < this.playable.size(); i++) {
			temp = 1;
			for (int j = 0; j < i; j++) {
				if (this.playable.get(i).getResult() == this.playable.get(j).getResult()) {
					temp++;
				}
			}
			if (temp > nbIdenticalResults) {
				nbIdenticalResults = temp;
			}
		}
		return nbIdenticalResults;
	}

	private double calculateWinnings(int nbIdenticalResults) {
		double winnings = 0;
		switch (nbIdenticalResults) {
		case 1:
			winnings = 0;
			break;
		case 2:
			winnings = this.bet * 1.5;
			break;
		case 3:
			winnings = this.bet * 5;
			break;
		default:
			winnings = this.bet * 10;
			break;
		}
		return winnings;
	}

	private void betRequest() {

		System.out.println("Combien souhaitez-vous miser? (" + this.player.getAmount()
				+ " euros sont disponibles sur votre compte)");

		boolean validating = true;
		while (validating) {
			validating = false;
			try {
				this.sc = new Scanner(System.in);
				this.bet = this.sc.nextDouble();
				if (this.bet > this.param.getMaximumBet()) {
					throw new BetException("Le montant de votre mise dépasse le seuil autorisé (max = "
							+ this.param.getMaximumBet() + " euros).");
				}
				if (this.bet > this.player.getAmount()) {
					throw new BetException("Vous ne disposez pas de suffisamment de fonds.\n Il vous reste "
							+ player.getAmount() + " euros.");
				}
				if (this.bet == 0) {
					throw new BetException("Pas de mise, pas de prise...");
				}

			} catch (InputMismatchException e) {
				validating = true;
				System.out.println("Attention. veuillez entrer un nombre.\n");
			} catch (BetException e) {
				validating = true;
				System.out.println(e.getMessage());
			}
		}
		this.player.setAmount(this.player.getAmount() - this.bet);
	}

	public Parameters getParam() {
		return this.param;
	}

	public void setParam(Parameters param) {
		this.param = param;
	}

	public double getBet() {
		return this.bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Scanner getSc() {
		return this.sc;
	}

	public void setSc(Scanner scanner) {
		this.sc = scanner;
	}
}
