package frontiere;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		boolean nomVendeurConnu = controlPrendreEtal.verifierIdentite(nomVendeur);
		if (!nomVendeurConnu) {
			System.out.println("Je suis désolée " + nomVendeur + " mais il faut être un habitant de notre village pour commercer ici");
			return;
		}
		System.out.println("Bonjour " + nomVendeur + ", je vais regarder si je peux vous trouver un étal.");
		boolean etalDisponnible = controlPrendreEtal.resteEtals();
		if (!etalDisponnible) {
			System.out.println("Désolée " + nomVendeur + "je n'ai plus d'étal qui ne soit pas déjà occupé");
			return;
		}
		installerVendeur(nomVendeur);
	}

	private void installerVendeur(String nomVendeur) {
		System.out.println("C'est parfait, il me reste un étal pour vous !");
		System.out.println("Il me faudrait quelques renseignements :");
		String produit = Clavier.entrerChaine("Quel produit souhaitez vous vendre ?\n");
		int nombre = Clavier.entrerEntier("Combien souhaitez-vous en vendre ?\n");
		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nombre);
		if (numeroEtal != -1) {
			System.out.println("Le vendeur " + nomVendeur + " s'est installé à l'étal n°" + (numeroEtal+1));
		}
	}
}
