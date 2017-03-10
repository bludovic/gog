package fr.banque.classes;

public class CompteRemunere extends Compte {

	private double taux;

	public CompteRemunere(int num, double solde) {
		super(num, solde);
	}

	public String toString() {
		return "CompteRemunere [Numero=" + this.getNum() + "Solde=" + this.getSolde() + " Taux=" + this.taux + "]";
	}

	public double calculerInterets() {
		return this.taux * this.getSolde();
	}

	public void verserInterets() {
		this.ajouter(this.calculerInterets());
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

}
