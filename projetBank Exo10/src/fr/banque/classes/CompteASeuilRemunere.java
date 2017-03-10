package fr.banque.classes;

import fr.banque.exception.BanqueException;
import fr.banque.interfaces.ICompteRemunere;

public class CompteASeuilRemunere extends CompteASeuil implements ICompteRemunere {

	private double taux = 0.0;

	public CompteASeuilRemunere(int num, double solde) {
		super(num, solde);
	}

	public void retirer(double uneValeur) throws BanqueException {
		if (uneValeur > this.getSolde()) {
			System.out.println("Retrait non autorisé");
			System.out.println("Votre solde est de " + this.getSolde());
		} else {
			super.retirer(uneValeur);
		}
	}

	@Override
	public double calculerInterets() {
		return this.taux * this.getSolde();
	}

	@Override
	public void verserInterets() {
		this.ajouter(this.calculerInterets());
	}

	@Override
	public double getTaux() {
		return taux;
	}

	@Override
	public void setTaux(double unTaux) {
		this.taux = unTaux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompteASeuilRemunere [taux=");
		builder.append(taux);
		builder.append("]");
		return builder.toString();
	}

}
