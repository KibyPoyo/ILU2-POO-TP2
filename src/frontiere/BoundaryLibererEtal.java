package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		boolean vendeurReconnu = controlLibererEtal.isVendeur(nomVendeur);
		if (!vendeurReconnu) {
			System.out.println("Mais vous n'êtes pas inscrit sur notre marché aujourd'hui !");
			return;
		}
		String[] donneesEtal = controlLibererEtal.libererEtal(nomVendeur);
		if (Boolean.parseBoolean(donneesEtal[0])) {
			System.out.println("Vous avez vendu " + donneesEtal[4] + 
					" sur " + donneesEtal[3] + " " + donneesEtal[2] + "");
			System.out.println("Au revoir " + donneesEtal[1] + ", passez une bonne journée !");			
		}
	}

}
