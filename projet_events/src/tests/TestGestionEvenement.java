package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import association.GestionEvenements;
import association.InterMembre;
import association.Membre;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Tests classe GestionEvenements {@link association.GestionEvenement
 * GestionEvenement}.
 *
 * @author Loana MOTTAIS
 * @see association.GestionEvenement
 *
 */
public class TestGestionEvenement {
  
  /* ATTRIBUTS */
  
  private GestionEvenements mesPremierEvenement;
  
  private GestionEvenements mesSecondEvenement;
  
  
  /**
   * Instanciation, avant chaque test, des attributs de la classe.
   * 
   * <p>mesPremierEvenement : gestionnaire d'évènement vide.
   * <br>mesSecondEvenement : gestionnaire d'évènement avec 2 évènement (avec et sans participants).
   *
   * @throws Exception ne peut pas être levée
   */
  @BeforeEach
  void setUp() throws Exception {
    mesPremierEvenement = new GestionEvenements();
   
    mesSecondEvenement = new GestionEvenements();
    
    /*Evenement 1 avec des participants*/
    Set<InterMembre> participantsEvt = new HashSet<InterMembre>();
    Evenement ev1 = new Evenement("Réunion tupperware", "Ploufragan", 11,
        Month.NOVEMBER, 2022, 11, 00, 2, 20, participantsEvt);
    InterMembre membre1 = new Membre("Sky", "Luc", "10 rue des mirabelle", 25);
    InterMembre membre2 = new Membre("Dor", "John", "25 dans le foret", 78);
    ev1.addparticipant(membre1);
    ev1.addparticipant(membre2);
    
    /*Evenement 2 sans participants*/
    Evenement ev2 = new Evenement("Soirée du 12", "Trémuson", 12, 
        Month.DECEMBER, 2022, 20, 00, 3, 150, participantsEvt);
    
    mesSecondEvenement.mesEvenement.add(ev1);
    mesSecondEvenement.mesEvenement.add(ev2);
    
  }
  
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Test de la méthode creerEvenement.
   */
  @Test
  void testCreationEvenement() {
    /*Création d'un évènement dans le gestionnaire vide*/
    assertTrue(mesPremierEvenement.mesEvenement.isEmpty());
    mesPremierEvenement.creerEvenement("Soirée post-partiels", "Last Player", 18,
        Month.DECEMBER, 2022, 20, 00, 3, 50);
    assertFalse(mesPremierEvenement.mesEvenement.isEmpty());
    
    /*Création d'un évènement dans le gestionnaire qui contient déjà 2 évènements*/
    assertTrue(mesSecondEvenement.mesEvenement.size() == 2);
    mesSecondEvenement.creerEvenement("Repas de noël", "Trémuson", 10,
        Month.APRIL, 2022, 14, 12, 4, 10);
    assertTrue(mesSecondEvenement.mesEvenement.size() == 3);
    
    /*Création d'évènements non corrects (dates ou lieu non correct)*/
    /*Même date & lieu qu'évènement créé juste avant*/
    mesSecondEvenement.creerEvenement("Anniversaire Marion", "Trémuson", 10,
        Month.APRIL, 2022, 14, 12, 4, 10);
    assertTrue(mesSecondEvenement.mesEvenement.size() == 3);
    
    /*date incorrect*/
    mesSecondEvenement.creerEvenement("Soirée du nouvel an", "Paris", 35,
        Month.DECEMBER, 20222, 14, 12, 8, 10);
    assertTrue(mesSecondEvenement.mesEvenement.size() == 3);
    
    /*Evènement déjà dans le gestionnaire*/
    mesSecondEvenement.creerEvenement("Réunion tupperware", "Ploufragan", 11,
        Month.NOVEMBER, 2022, 11, 00, 2, 20);
    assertTrue(mesSecondEvenement.mesEvenement.size() == 3);
  }
  
  /**
   * Test de la méthode supprimerEvenement.
   */
  @Test
  void testSuppressionEvenement() {
    Evenement evt = mesSecondEvenement.mesEvenement.get(1);
    assertTrue(mesSecondEvenement.mesEvenement.size() == 2);
    mesSecondEvenement.supprimerEvenement(evt);
    assertTrue(mesSecondEvenement.mesEvenement.size() == 1);
    
    /*Vérification suppression du même evenènement une seconde fois*/
    mesSecondEvenement.supprimerEvenement(evt);
    assertTrue(mesSecondEvenement.mesEvenement.size() == 1);
  }
  
  /**
   * Test du getter ensembleEvenementAvenir().
   */
  @Test
  void testAvenirEvenement() {
    assertEquals(mesSecondEvenement.ensembleEvenementAvenir().size(), 1);
    assertEquals(mesPremierEvenement.ensembleEvenementAvenir().size(), 0);
  }
  
  /**
   * Test de la méthode inscriptionEvenement.
   */
  @Test
  void testInscriptionEvenement() {
    /*Récupération de l'évènement*/
    Evenement evt = mesSecondEvenement.mesEvenement.get(0);
    
    /*Vérification du nombre de participants inscrits à l'évènement*/
    assertEquals(evt.getNbinscri(), 2);
    
    /*Inscription d'un inconnu à l'évènement*/
    InterMembre inconnu = new Membre("this", "is", "56 the test", 35);
    assertTrue(mesSecondEvenement.inscriptionEvenement(evt, inconnu));
    assertEquals(evt.getNbinscri(), 3);

    
    /*Inscription d'un membre déjà inscrit à l'évènement */
    InterMembre participant = new Membre("Sky", "Luc", "10 rue des mirabelle", 25);
    assertFalse(mesSecondEvenement.inscriptionEvenement(evt, participant));
    assertEquals(evt.getNbinscri(), 3);
    
    /*Inscription à un évènement ne faisant pas partie du gestionnaire*/
    Set<InterMembre> participantsEvt = new HashSet<InterMembre>();
    Evenement evtInconnu = new Evenement("Bowling", "Toulouse", 15,
        Month.JANUARY, 2023, 11, 00, 2, 20, participantsEvt);
    assertFalse(mesSecondEvenement.inscriptionEvenement(evtInconnu, inconnu));
  }
  
  /**
   * Test de la méthode annulerEvenement.
   */
  @Test
  void testAnnulerEvenement() {
    /*Récupération de l'évènement*/
    Evenement evt = mesSecondEvenement.mesEvenement.get(0);
    
    /*Vérification du nombre de participants inscrits à l'évènement*/
    assertEquals(evt.getNbinscri(), 2);
    
    /*Annulation de l'évènement pour un membre inscrit*/
    InterMembre participant = new Membre("Sky", "Luc", "10 rue des mirabelle", 25);
    assertTrue(mesSecondEvenement.annulerEvenement(evt, participant));
    assertEquals(evt.getNbinscri(), 1);
    
    /*Annulation de l'évènement pour un inconnu*/
    InterMembre inconnu = new Membre("this", "is", "56 the test", 35);
    assertFalse(mesSecondEvenement.annulerEvenement(evt, inconnu));
    assertEquals(evt.getNbinscri(), 1);
  }
  
}


 
