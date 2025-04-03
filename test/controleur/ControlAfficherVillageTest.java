package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private Village village;
	private Chef abraracourcix;
	private ControlAfficherVillage controlAfficherVillage;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlAfficherVillage = new ControlAfficherVillage(village);
	}
	
	@Test
	void testControlAfficherVillage() {
		assertNotNull(controlAfficherVillage, "Constructeur ne renvoie pas null");
	}
	
	
	@Test
	void testDonnerNomsVillageois() {
		Gaulois asterix = new Gaulois("Asterix",5);
		Gaulois bonemine = new Gaulois("Bonemine",3);
		Gaulois homeopatix = new Gaulois("Homeopatix",9);
		Gaulois tournevix = new Gaulois("Tournevix",5);
		
		String[] villageois = controlAfficherVillage.donnerNomsVillageois();
		String[] villageoisAttendu = new String[]{"Abraracourcix"};
		assertArrayEquals(villageois, villageoisAttendu);
		
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(homeopatix);
		
		villageois = controlAfficherVillage.donnerNomsVillageois();
		villageoisAttendu = new String[]{"Abraracourcix","Asterix","Bonemine","Homeopatix"};
		assertArrayEquals(villageois, villageoisAttendu);
		
		village.ajouterHabitant(tournevix);

		villageois = controlAfficherVillage.donnerNomsVillageois();
		villageoisAttendu = new String[]{"Abraracourcix","Asterix","Bonemine","Homeopatix","Tournevix"};
		assertArrayEquals(villageois, villageoisAttendu);
	}
	
	@Test
	void donnerNomVillage() {
		assertEquals(controlAfficherVillage.donnerNomVillage(), "le village des irréductibles");
	}
	
	@Test
	void donnerNbEtals() {
		Gaulois asterix = new Gaulois("Asterix",5);
		village.ajouterHabitant(asterix);
		
		assertEquals(5,controlAfficherVillage.donnerNbEtals());
		
		village.installerVendeur(asterix, "choses", 12);
		village.installerVendeur(asterix, "trucs", 20);

		assertEquals(5,controlAfficherVillage.donnerNbEtals());
		
		village.installerVendeur(asterix, "machins", 12);
		village.installerVendeur(asterix, "bidules", 20);
		village.installerVendeur(asterix, "bric à brac", 12);
		
		assertEquals(5,controlAfficherVillage.donnerNbEtals());

		village.installerVendeur(asterix, "cailloux entre autres", 84);
		
		assertEquals(5,controlAfficherVillage.donnerNbEtals());
	}

}
