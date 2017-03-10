package fr.banque.classes;

public class Compte {

	private int		num;
	private double	solde;

	public Compte(int num, double solde) {
		super();
		this.num = num;
		this.solde = solde;
	}

	public void ajouter(double unMontant) {
		solde = solde + unMontant;
	}

	public void retirer(double unMontant) {
		solde = solde - unMontant;
	}

	public int getNum() {
		return num;
	}

	private void setNum(int num) {
		this.num = num;
	}

	public double getSolde() {
		return solde;
	}

	private void setSolde(double solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte [num=" + num + ", solde=" + solde + "]";
	}
}
