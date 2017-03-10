package fr.banque.classes;

import java.util.Arrays;

public class Client {
	String		nom, prenom;
	int			age, numero;
	Compte[]	comptes	= new Compte[5];

	public void ajouterCompte(Compte unCompte) {
		for (int i = 0; i < comptes.length; i++) {
			if (comptes[i] == null) {
				comptes[i] = unCompte;
				break;
			}
		}
	}

	public Compte getCompte(int numero) {
		for (int i = 0; i < comptes.length; i++) {
			if (comptes[i].getNum() == numero) {
				return comptes[i];
			}
		}
		return null;
	}

	public Compte[] getComptes() {
		return comptes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setComptes(Compte[] comptes) {
		this.comptes = comptes;
	}

	@Override
	public String toString() {
		return "Client [nom=" + this.nom + ", prenom=" + this.prenom + ", age=" + this.age + ", numero=" + this.numero
				+ ", comptes=" + Arrays.toString(this.comptes) + "]";
	}

	public Client(String nom, String prenom, int age, int numero) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.numero = numero;
	}

}
