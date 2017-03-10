package fr.banque.classes;

import fr.banque.exception.BanqueException;
import fr.banque.interfaces.ICompteASeuil;

public class CompteASeuil extends Compte implements ICompteASeuil {

	private double seuil = 0.0;

	public CompteASeuil(int num, double solde) {
		super(num, solde);
	}

	@Override
	public String toString() {
		return "Compte [num=" + this.getNum() + ", solde=" + this.getSolde() + " seuil=" + this.seuil + "]";
	}

	public void retirer(double uneValeur) throws BanqueException {
		if (uneValeur > this.getSolde()) {
			throw new BanqueException("Retrait non autorisé!");
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
