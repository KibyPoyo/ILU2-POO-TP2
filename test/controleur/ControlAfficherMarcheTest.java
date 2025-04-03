package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private Village village;
	private Chef abraracourcix;
	private ControlAfficherMarche controlAfficherMarche;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlAfficherMarche = new ControlAfficherMarche(village);
	}
	
	@Test
	void testControlAfficherMarche() {
		assertNotNull(controlAfficherMarche, "Constructeur ne renvoie pas null");
	}
	
	@Test
	void testDonnerInfosMarche() {	
		Gaulois asterix = new Gaulois("Asterix",5);
		Gaulois bonemine = new Gaulois("Bonemine",3);
		Gaulois homeopatix = new Gaulois("Homeopatix",9);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(homeopatix);
		village.installerVendeur(asterix, "casques", 12);
		village.installerVendeur(bonemine, "fleurs", 20);
		village.installerVendeur(homeopatix, "sucre", 100);
		
		String[] infosMarche = controlAfficherMarche.donnerInfosMarche();
		
		String[] expectedResult = new String[]{"Asterix","12","casques","Bonemine","20","fleurs","Homeopatix","100","sucre"};
		assertArrayEquals(infosMarche,expectedResult);
	}


}
