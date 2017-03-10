package fr.banque.interfaces;

import fr.banque.exception.BanqueException;

public interface ICompteASeuil {

	public void retirer(double uneValeur) throws BanqueException;

	public double getSeuil();

	public void setSeuil(double seuil);

}
