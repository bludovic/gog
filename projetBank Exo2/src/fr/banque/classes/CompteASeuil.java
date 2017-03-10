package fr.banque.classes;

public class CompteASeuil extends Compte {

	private double seuil = 0.0;

	public CompteASeuil(int num, double solde) {
		super(num, solde);
	}

	@Override
	public String toString() {
		return "Compte [num=" + this.getNum() + ", solde=" + this.getSolde() + " seuil=" + this.seuil + "]";
	}

	public void retirer(double uneValeur) {
		if (uneValeur > this.getSolde()) {
			System.out.println("Retrait non autorisé");
			System.out.println("Votre solde est de " + this.getSolde());
		} else {
			super.retirer(uneValeur);
		}
	}

	public double getSeuil() {
		return seuil;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

}
