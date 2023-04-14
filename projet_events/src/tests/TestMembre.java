package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import association.GestionEvenements;
import association.Membre;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Junit de la classe {@link assocaition.Membre Membre}.
 *
 * @author Lin WAN
 * @see association.Membre
 *
 */
public class TestMembre {
  /**
   * Un membre demi complet : prénom, nom, adresse, age.
   */
  private Membre membredemi;

  /**
   * Un membre complet : prénom, nom, adresse, age, list des évenements non vide.
   */
  private Membre membrecomplet;
  
  /**
   * Un évenement pour l'ensemble.
   */
  private Evenement evTest1;
  
  /**
   * Un ensemble d'évenement.
   */
  private GestionEvenements geTest;
  
  /**
   * Instancie un membre basique, un membre demi complet et un membre complet pour les tests.
   *
   * @throws Exception ne peut pas être levé ici
   */
  @BeforeEach
  void setUp() throws Exception {
    membredemi = new Membre("Vincent", "Bernard", "27 rue sully prudhomme", 20);
    membrecomplet = new Membre("Le pen", "Marie", "15 rue Jean jaurès", 30);
    geTest = new GestionEvenements();
    evTest1 = geTest.creerEvenement("Reunion tupperware", "Ploufragan", 11,
       Month.DECEMBER, 2022, 11, 00, 2, 20);
    
  }
  
  /**
   * Ne fait rien apr�s les tests : � modifier au besoin.
   *
   * @throws Exception ne peut pas �tre lev�e ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
    * Test méthode setlistEvenement.
    */
  @Test
  void testSetlistEvenement() {
    membrecomplet.setlistEvenement(geTest.ensembleEvenements());
    assertNotNull(membrecomplet.ensembleEvenements());
    assertTrue(membredemi.ensembleEvenements().isEmpty());
    assertFalse(membrecomplet.ensembleEvenements().isEmpty());
  }
  
  /**
   * Test méthode setEvenement.
   */
  @Test
  void testSetEvenement() {
    membredemi.setEvenement(evTest1);
    assertNotNull(membredemi.ensembleEvenements());
    assertFalse(membredemi.ensembleEvenements().isEmpty());
  }
  
  /**
   * Test de la méthode ensembleEvenementsAvenir.
   * pas réussi
   */
  @Test
  void testEnsembleEvenementsAvenir() {
    membrecomplet.setlistEvenement(geTest.ensembleEvenements());
    assertFalse(membrecomplet.ensembleEvenements().isEmpty());
    assertFalse(membrecomplet.ensembleEvenementsAvenir().isEmpty());
  }
  
  /**
   * Test de la méthode definirInformationPersonnelle.
   */
  @Test
  void testDefinirInfoPer() {
    membrecomplet.definirInformationPersonnnelle(membredemi.getInformationPersonnelle());
    assertTrue(membrecomplet.getInformationPersonnelle()
        .equals(membredemi.getInformationPersonnelle()));
  }
  
}
