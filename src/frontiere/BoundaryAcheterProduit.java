package frontiere;

import personnages.Gaulois;
import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.estVillageoisPresent(nomAcheteur)) {
			System.out.println("Je suis désolé " + nomAcheteur + " mais il faut être un habitant"
					+ " de notre village pour commercer ici.");
			return;
		}
		
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?\n");
		if (!controlAcheterProduit.estProduitEnVente(produit)) {
			System.out.println("Désolé, personne ne vend des " + produit + " au marché");
			return;
		}
		
		Gaulois[] vendeurs = controlAcheterProduit.listeVendeurs(produit);
		for (int i = 0; i < vendeurs.length; i++) {
			System.out.println((i+1) + " - " + vendeurs[i].getNom());
		}
		int identifiantVendeur = Clavier.entrerEntier("");
		identifiantVendeur -= 1; // Numéro dans la liste vendeurs
		if (identifiantVendeur < 0 || identifiantVendeur >= vendeurs.length) {
			System.out.println("Ce numéro de vendeur n'existe pas");
			return;
		}
		
		String nomVendeur = vendeurs[identifiantVendeur].getNom();
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur);
		int quantiteDemandee = Clavier.entrerEntier("Bonjour " + nomAcheteur + ", combien de " +
														produit + " voulez-vous acheter ?");
		int quantiteAchetee = controlAcheterProduit.acheterProduit(nomVendeur,quantiteDemandee);
		if (quantiteAchetee == 0) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteDemandee
					+ " " + produit + ", malheureusement il n'y en a plus !");
		} else if (quantiteAchetee == quantiteDemandee) {
			System.out.println(nomAcheteur + " achète " + quantiteAchetee
					+ " " + produit + " à " + nomVendeur);
		} else {
			System.out.println(nomAcheteur + " veut acheter " + quantiteDemandee
					+ " " + produit + ", malheureusement " + nomVendeur
					+ " n'en a plus que " + quantiteAchetee + ".\n" + nomAcheteur +
					" achète tout le stock de " + nomVendeur + ".");
		}
	}
}
