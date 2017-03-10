package fr.banque;

import fr.banque.classes.Client;
import fr.banque.classes.Compte;
import fr.banque.classes.CompteASeuil;
import fr.banque.exception.BanqueException;

public class Main {

	public static void main(String[] args) {
		Client client1 = new Client("Bob", "Marley", 27, 1);
		try {
			client1.ajouterCompte(new Compte(1245, 20045.32));
		} catch (BanqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CompteASeuil compte = new CompteASeuil(1683, 107.62);
		try {
			client1.ajouterCompte(compte);
		} catch (BanqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(client1.toString());
		try {
			compte.retirer(300);
		} catch (BanqueException e) {
			e.printStackTrace();
		}
		System.out.println(compte);

		System.out.println(client1.getCompte(1245));

	}

}
