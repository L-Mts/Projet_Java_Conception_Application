package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import association.GestionAssociation;
import association.GestionEvenements;
import association.GestionMembres;
import association.InterMembre;
import association.Membre;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests JUnit de la classe {@link association.GestionAssociation GestionAssociation}.
 *
 * @author Lin WAN
 * @see association.GestionAssociation
 */
public class TestGestionAssociation {
  private GestionAssociation monAssociation;
  private GestionEvenements gestionEvTest;
  private GestionMembres gestionMemTest;
  
  /**
   * Instancie un ensemble d'évènements.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    gestionEvTest = new GestionEvenements();
    gestionEvTest.creerEvenement("zew", "Bellevue", 14, Month.FEBRUARY, 2022, 16, 20, 20, 40);
    InterMembre membre1 = new Membre("Moetez", "Vincent", "14 rue du havre", 24);
    InterMembre membre2 = new Membre("Rodin", "Le pen", "135 rue Jean Jaur�s", 16);
    Set<InterMembre> collection = new HashSet<InterMembre>();
    collection.add(membre1);
    collection.add(membre2);
    gestionMemTest = new GestionMembres(collection, membre1);
    monAssociation = new GestionAssociation(gestionEvTest, gestionMemTest);
    
  }
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  
  @AfterEach
  void tearDown() throws Exception {}
  /**
   * Vérifie si le fichier est bien créé avec les ensembles de membre de d'évènements
   * bien stockés dans le fichier.
   */
  
  @Test
  void testSauvegaderetChargerDonnees() {
    try {
      monAssociation.sauvegarderDonnees("Test.dat");
      monAssociation.chargerDonnees("Test.dat");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Vérifie si la fonction gestionEvenenments marche ou pas.
   */
  @Test
  void testGestionnaireEvenenments() {
    assertNotNull(monAssociation.gestionnaireEvenements());
    assertEquals(monAssociation.gestionnaireEvenements(), gestionEvTest);
  }
  
  /**
   * Vérifie si la fonction gestionEvenenments marche ou pas.
   */
  @Test
  void testGestionnaireMembre() {
    assertNotNull(monAssociation.gestionnaireMembre());
    assertEquals(monAssociation.gestionnaireMembre(), gestionMemTest);
  }
  
}

