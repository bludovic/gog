package fr.banque.classes;

import fr.banque.interfaces.ICompteRemunere;

public class CompteRemunere extends Compte implements ICompteRemunere {

	private double taux;

	public CompteRemunere(int num, double solde) {
		super(num, solde);
	}

	public String toString() {
		return "CompteRemunere [Numero=" + this.getNum() + "Solde=" + this.getSolde() + " Taux=" + this.taux + "]";
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	@Override
	public double calculerInterets() {
		return this.taux * this.getSolde();
	}

	@Override
	public void verserInterets() {
		this.ajouter(this.calculerInterets());
	}

}
