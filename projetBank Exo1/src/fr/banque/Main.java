package fr.banque;

import fr.banque.classes.Client;
import fr.banque.classes.Compte;

public class Main {

	public static void main(String[] args) {
		Client client1 = new Client("Bob", "Marley", 27, 1);
		client1.ajouterCompte(new Compte(1245, 20045.32));
		Compte compte = new Compte(1683, 107.62);
		client1.ajouterCompte(compte);

		System.out.println(client1.toString());
		System.out.println(compte);

		System.out.println(client1.getCompte(1245));

	}

}
