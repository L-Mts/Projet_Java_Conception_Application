package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import association.InterMembre;
import association.Membre;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
   * Tests JUnit de la classe {@link association.Evenement
   * Evenement}.
   *
   * @author Anouk GOUHIER DUPUIS
   * @see association.Evenement
   */
public class TestEvenement {
   
  /**
   * Un évènement (constructeur avec : nom lieu date duree nbParticipantsMax participants).
   */
  private Evenement ev1;
  
  /**
   * Un évènement (constructeur avec : 
   * nom lieu jour mois annee heure minutes duree nbParticipantsMax participants).
   */
  private Evenement ev2;
  private Evenement ev3; 
  Set<InterMembre> nouveau = new HashSet<InterMembre>();  
  
  /**
   * Instancie les evenements complets pour les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
    void setUp() throws Exception {
    
      
    this.ev1 = new Evenement("Guerre de l'étoile", "Espace", null, 2, 15, nouveau);
    LocalDateTime date = ev1.creationDate(12, Month.DECEMBER, 2022, 10, 10);
    
    this.ev1.setDate(date);
    this.ev2 = new Evenement("Bataille de naboo", "Naboo", 12, Month.APRIL,
        2023, 15, 00, 2, 25, nouveau);
    this.ev3 = new Evenement("Un tour sur tatoine", "Naboo", 12, Month.APRIL,
        2023, 16, 00, 1, 12, nouveau);
  }
    
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
    void tearDown() throws Exception {}
    
  /**
  * Vérifie la bonne création des date par CreationDate.
  */
  @Test
     void testCreationDate() {
    LocalDateTime temp = this.ev1.creationDate(23, Month.JANUARY, 2023, 12, 0);
    LocalDateTime temp2 = LocalDateTime.of(2023, Month.JANUARY, 23, 12, 0);
    assertTrue(temp != null);
    assertEquals(temp, temp2);
    assertTrue(temp != null);
  }
  
  /**
   * Vérifie que le bon fonctionnement des méthodes pasDeChevauchement.
   */
  @Test
      void testChevauchement() {
    assertTrue(this.ev1.pasDeChevauchementLieu(this.ev2));
    assertFalse(this.ev2.pasDeChevauchementLieu(this.ev3));
    assertTrue(this.ev3.pasDeChevauchementLieu(this.ev1));
  }
  
  /**
   * Vérifie que la fonction addParticipant ajoute correctement les membres.
   */
  @Test
      void testaddParticipant() {
    assertFalse(this.ev1.addparticipant(null));
    InterMembre mbr = new Membre("Sky", "Luc", "10 rue des mirabelle", 25);
    assertTrue(this.ev1.addparticipant(mbr));
    assertTrue(this.ev1.getParticipants().size() == 1);
    assertTrue(this.ev1.getParticipants().contains(mbr));
    assertFalse(this.ev1.addparticipant(mbr));
    assertTrue(this.ev1.getParticipants().size() == 1);
  }
  
  /**
   * Vérifie que la fonction delParticipant enlève correctement les membres.
   */
  @Test
      void testdelParticipant() {
    assertFalse(this.ev1.delparticipant(null));
    InterMembre mbr = new Membre("Sky", "Luc", "10 rue des mirabelle", 25);
    assertTrue(this.ev1.addparticipant(mbr));
    assertTrue(this.ev1.delparticipant(mbr));
    assertTrue(this.ev1.getParticipants().size() == 0);
    assertFalse(this.ev1.delparticipant(mbr));
  }
}
