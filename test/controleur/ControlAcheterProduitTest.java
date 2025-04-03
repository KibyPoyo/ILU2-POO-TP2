package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private Village village;
	private Chef abraracourcix;
	private ControlAcheterProduit controlAcheterProduit;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,controlTrouverEtalVendeur,village);
	}
	
	@Test
	void testControlAcheterProduit() {
		assertNotNull(controlAcheterProduit, "Constructeur ne renvoie pas null");
	}
	
	
	@Test
	void testAcheterProduit() {
		Gaulois homeopatix = new Gaulois("Homeopatix",9);
		village.ajouterHabitant(homeopatix);
		village.installerVendeur(homeopatix, "sucre", 15);
		assertEquals(10,controlAcheterProduit.acheterProduit("Homeopatix", 10));
		assertEquals(5,controlAcheterProduit.acheterProduit("Homeopatix", 10));
		assertEquals(0,controlAcheterProduit.acheterProduit("Homeopatix", 10));
		assertEquals(0,controlAcheterProduit.acheterProduit("Homeopatix", 0));
		assertEquals(0,controlAcheterProduit.acheterProduit("Homeopatix", -10));
		assertEquals(-1,controlAcheterProduit.acheterProduit("Pathologix", 1));
	}
	
	@Test
	void testEstVillageoisPresent() {
		Gaulois homeopatix = new Gaulois("Homeopatix",9);
		assertFalse(controlAcheterProduit.estVillageoisPresent("Pathologix"));
		assertFalse(controlAcheterProduit.estVillageoisPresent("Homeopatix"));
		
		village.ajouterHabitant(homeopatix);
		assertTrue(controlAcheterProduit.estVillageoisPresent("Homeopatix"));
	}
	
	@Test
	void testEstProduitEnVente() {
		Gaulois asterix = new Gaulois("Asterix",5);
		Gaulois bonemine = new Gaulois("Bonemine",3);
		Gaulois homeopatix = new Gaulois("Homeopatix",9);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(homeopatix);
		village.installerVendeur(asterix, "casques", 12);
		village.installerVendeur(bonemine, "fleurs", 20);
		village.installerVendeur(homeopatix, "sucre", 100);
		
		assertTrue(controlAcheterProduit.estProduitEnVente("casques"));
		assertTrue(controlAcheterProduit.estProduitEnVente("fleurs"));
		assertTrue(controlAcheterProduit.estProduitEnVente("sucre"));
		assertFalse(controlAcheterProduit.estProduitEnVente("Kartoffelnsalad"));
		assertFalse(controlAcheterProduit.estProduitEnVente("sucres"));
	}
	
	@Test
	void testListeVendeurs() {
		Gaulois asterix = new Gaulois("Asterix",5);
		Gaulois bonemine = new Gaulois("Bonemine",3);
		Gaulois homeopatix = new Gaulois("Homeopatix",9);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(homeopatix);
		village.installerVendeur(asterix, "casques", 12);
		village.installerVendeur(bonemine, "fleurs", 20);
		village.installerVendeur(homeopatix, "sucre", 100);
		
		Gaulois[] listeVendeurs = controlAcheterProduit.listeVendeurs("sucre");
		Gaulois[] listeVendeursAttendue = new Gaulois[]{homeopatix};
		assertArrayEquals(listeVendeurs,listeVendeursAttendue);
		
		listeVendeurs = controlAcheterProduit.listeVendeurs("casques");
		listeVendeursAttendue = new Gaulois[]{asterix};
		assertArrayEquals(listeVendeurs,listeVendeursAttendue);
		
		listeVendeurs = controlAcheterProduit.listeVendeurs("homeomorphismes polygonaux");
		assertArrayEquals(listeVendeurs,null);
		
		
		Gaulois tournevix = new Gaulois("Tournevix",5);
		village.ajouterHabitant(asterix);
		village.installerVendeur(tournevix, "sucre", 84);
		
		listeVendeurs = controlAcheterProduit.listeVendeurs("sucre");
		listeVendeursAttendue = new Gaulois[]{homeopatix, tournevix};
		assertArrayEquals(listeVendeurs,listeVendeursAttendue);
	}

}
