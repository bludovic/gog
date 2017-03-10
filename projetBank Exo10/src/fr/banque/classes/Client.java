package fr.banque.classes;

import java.util.Hashtable;
import java.util.Map;

import fr.banque.exception.BanqueException;

public class Client {
	String					nom, prenom;
	int						age, numero;
	Map<Integer, Compte>	comptes	= new Hashtable<Integer, Compte>();

	public void ajouterCompte(Compte unCompte) throws BanqueException {
		if (comptes.size() <= 5) {
			comptes.put(unCompte.getNum(), unCompte);
		} else {
			throw new BanqueException("Nombre de compte maximum atteind");
		}
	}

	public Compte getCompte(int numero) {
		for (int i = 0; i < 5; i++) {
			if (comptes.get(i).getNum() == numero) {
				return comptes.get(i);
			}
		}
		return null;
	}

	public Map<Integer, Compte> getComptes() {
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

	public void setComptes(Map<Integer, Compte> comptes) {
		this.comptes = comptes;
	}

	public Client(String nom, String prenom, int age, int numero) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.numero = numero;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", age=");
		builder.append(age);
		builder.append(", numero=");
		builder.append(numero);
		builder.append(", comptes=");
		builder.append(comptes);
		builder.append("]");
		return builder.toString();
	}

}
