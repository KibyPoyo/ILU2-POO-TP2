package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean estVillageoisPresent(String nom) {
		return controlVerifierIdentite.verifierIdentite(nom);
	}
	
	public boolean estProduitEnVente(String produit) {
		return village.rechercherVendeursProduit(produit) != null;
	}
	
	public Gaulois[] listeVendeurs(String produit) {
		return village.rechercherVendeursProduit(produit);
	}
	
	public int acheterProduit(String nomVendeur, int quantite) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		if (etal == null) {
			return -1;
		}
		int quantiteeAchetee = etal.acheterProduit(quantite);
		return quantiteeAchetee;
	}
}
