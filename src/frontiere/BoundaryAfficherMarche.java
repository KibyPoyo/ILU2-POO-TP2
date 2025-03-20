package frontiere;

import controleur.ControlAfficherMarche;

public class BoundaryAfficherMarche {
	private ControlAfficherMarche controlAfficherMarche;

	public BoundaryAfficherMarche(ControlAfficherMarche controlAfficherMarche) {
		this.controlAfficherMarche = controlAfficherMarche;
	}

	public void afficherMarche(String nomAcheteur) {
		String[] infosMarche = controlAfficherMarche.donnerInfosMarche();
		if (infosMarche.length == 0) {
			System.out.println("Le marché est vide, revenez plus tard.");
			return;
		}
		
		StringBuilder message = new StringBuilder();
		message.append(nomAcheteur + ", vous trouverez au marché :\n");
		for (int i = 0; i+2 < infosMarche.length; i++) {
			message.append("- " + infosMarche[i]);
			i++;
			message.append(" qui vend " + infosMarche[i]);
			i++;
			message.append(" " + infosMarche[i] + "\n");			
		}
		System.out.println(message.toString());
	}
}
