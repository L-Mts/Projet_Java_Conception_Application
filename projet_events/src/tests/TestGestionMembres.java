package tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.GestionMembres;
import association.InterMembre;
import association.Membre;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests sur la classe GestionMembres {@link association.GestionMembres
 * GestionMembres}.
 *
 * @author Loana MOTTAIS
 * @see association.GestionMembres
 */
public class TestGestionMembres {

  private GestionMembres gestionVideTest;
  
  private GestionMembres gestionComplete;
  
  /**
   * Avant chaque test, instantciation des attributs de la classe.
   * 
   * <p>gestionVideTest : gestionnaire de membre vide.
   * <br>gestionComplete : gestionnaire de membre avec 4 membres, dont 1 président.
   *
   * @throws Exception ne peut pas être levée
   */
  @BeforeEach
  void setUp() throws Exception {
    gestionVideTest = new GestionMembres();
    InterMembre membre1 = new Membre("Mathieu", "Rodrigo", "42 boulevard La Martine", 32);
    InterMembre membre2 = new Membre("Morgana", "Le Fay", "Chateau de Camelot", 25);
    InterMembre membre3 = new Membre("Arthur", "Pendragon", "Chateau de Camelot", 26);
    InterMembre membre4 = new Membre("Merlin", "Emrys", "Chateau de Camelot", 24);
    Set<InterMembre> collection = new HashSet<InterMembre>();
    collection.add(membre1);
    collection.add(membre2);
    collection.add(membre3);
    collection.add(membre4);
    gestionComplete = new GestionMembres(collection, membre1);
  }

  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}

  /**
   * Test de la méthode ajouterMembre.
   */
  @Test
  void testAjouterMembre() {
    InterMembre membre1 = new Membre("Olive", "Uno", "6 allée des Magnolias", 25);
    assertTrue(gestionVideTest.ajouterMembre(membre1));
    assertTrue(gestionVideTest.ensembleMembres.contains(membre1));
    assertTrue(gestionComplete.ajouterMembre(membre1));
    assertTrue(gestionComplete.ensembleMembres.contains(membre1));
    assertFalse(gestionComplete.ajouterMembre(membre1));
  }
  
  /**
   * Test de la méthode supprimerMembre.
   */
  @Test
  void testSupprimerMembre() {
    InterMembre membre = new Membre("Morgana", "Le Fay", "Chateau de Camelot", 25);
    assertFalse(gestionVideTest.supprimerMembre(membre));
    //assertTrue(gestionComplete.supprimerMembre(membre));
    assertTrue(gestionComplete.ensembleMembres.contains(membre));
    InterMembre president = new Membre("Mathieu", "Rodrigo", "42 boulevard La Martine", 32);
    assertTrue(gestionComplete.supprimerMembre(president));
    assertFalse(gestionComplete.ensembleMembres.contains(president));
    assertNull(gestionComplete.president);
  }
  
  /**
   * Test de la méthode designerPresident.
   */
  @Test
  void testDesignerPresident() {
    InterMembre membre = new Membre("Morgana", "Le Fay", "Chateau de Camelot", 25);
    InterMembre membreAbsent = new Membre("Peter", "Pan", "Ile des enfants perdus", 12);
    assertFalse(gestionVideTest.designerPresident(membre));
    assertFalse(gestionVideTest.designerPresident(membreAbsent));
    assertTrue(gestionComplete.designerPresident(membre));
    assertFalse(gestionComplete.designerPresident(membreAbsent));
  }
  
  /**
   * Test du getter ensembleMembres().
   */
  @Test
  void testEnsembleMembres() {
    Set<InterMembre> vide = new HashSet<InterMembre>();
    assertEquals(vide, gestionVideTest.ensembleMembres());
    assertEquals(gestionComplete.ensembleMembres, gestionComplete.ensembleMembres());
  }
  
  /**
   * Test de getter president().
   */
  @Test
  void testPresident() {
    assertNull(gestionVideTest.president());
    assertEquals(gestionComplete.president, gestionComplete.president());
  }

}
