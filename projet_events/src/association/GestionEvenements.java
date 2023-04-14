package association;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Classe GestionEvenement.
 *
 * <p>Elle permet la gestion des différents évènements de l'association
 *
 * @author Anouk GOUHIER DUPUIS
 *
 */

public class GestionEvenements implements InterGestionEvenements {
  
  
  /**
   * Liste de tous les évènements de l'association.
   */
  public List<Evenement> mesEvenement;
  
  
  
  /**
   * Constructeur instance vide de la classe GestionEvenements.
   * 
   */
  public GestionEvenements() {
    this.mesEvenement = new ArrayList<Evenement>();
  }

  

  
  /**
   * Méthode instanciation initialisée de la classe Evenement.
   *
   * <p>this.ensembleEvenement : collection des évènements de l'associations
   * <br>Un évènement peut être ajouté même si sa date est déjà passée
   *
   *@param nom : nom de lévènement
   *@param lieu : lieu ou se déroule l'évènement
   *@param jour : entier qui décrit le jour par son numéro dans le mois 1-31
   *@param mois : attribut de type Month qui décrit le mois
   *@param annee : entier sur 4 chiffres qui décrit l'année ou se déroule l'évènement 
   *@param heure : entier sur 2 chiffres décrivant l'heure à laquelle débute l'évènement
   *@param minutes : entier sur 2 chiffres 
   *@param duree : entier qui décrit la durée de l'évènement
   *@param nbParticipants : nombre de participant max de l'évènement 
   *
   *@return l'élément ajouter à la liste des évènements
   */
  @Override
  public Evenement creerEvenement(String nom, String lieu, int jour, Month mois,
      int annee, int heure, int minutes, int duree, int nbParticipants) {
    LocalDateTime date;
    try {
      date = LocalDateTime.of(annee, mois, jour, heure, minutes);
    } catch (DateTimeException e) {
      return null;
    }
    Set<InterMembre> mesMembres = new HashSet<InterMembre>();
    Evenement ev =
        new Evenement(nom, lieu, date, duree, nbParticipants, mesMembres);
    for (Evenement evt : this.mesEvenement) {
      if (!ev.pasDeChevauchementLieu(evt)) {
        return null;
      }
    }
    this.mesEvenement.add(ev);
    return ev;
    
  }
  
  /**
 * Méthode permettant la suppression d'un évènement.
 *
 * <p>L'évènement est retiré des listes d'évènement de ses membres 
 * puis retiré de la liste d'évènement du gestionnaire
 *
 *@param evt : évènement à supprimer du gestionnaire
 */
  @Override
  public void supprimerEvenement(Evenement evt) {
    if (this.mesEvenement.contains(evt)) {
      Set<InterMembre> neParticipePlus = evt.getParticipants();
      Iterator<InterMembre> iterator = neParticipePlus.iterator();
      while (iterator.hasNext()) {
        iterator.next().ensembleEvenements().remove(evt);
        iterator.next().ensembleEvenementsAvenir().remove(evt);
      }
      this.mesEvenement.remove(evt);
    }
  }
  
  /**
   * Méthode get de la liste des évènements du gestionnaire.
   *
   *@return : la liste des évènements du gestionnaire
   */
  @Override
  public List<Evenement> ensembleEvenements() {
    return this.mesEvenement;
  }
  
  /**
   * Méthodes get de la liste des évènement à avenir du gestionnaire.
   *
   * @return retourne le liste des évènement à venir du gestionnaire
   */
  @Override
  public List<Evenement> ensembleEvenementAvenir() {
    List<Evenement> mesEvenementAvenir = new ArrayList<Evenement>();
    if (this.mesEvenement == null) {
      return null;
    }
    for (int i = 0; i < this.mesEvenement.size(); i++) {
      if (this.mesEvenement.get(i).getDate().isAfter(LocalDateTime.now())) {
        mesEvenementAvenir.add(this.mesEvenement.get(i));
      }
    }
    return mesEvenementAvenir;
  }

  
  /**
   * Méthode permettant d'inscrire un membre à un évènement du gestionnaire.
   *
   *@param evt : évènement auquel on veut inscrire le membre
   *@param mbr : membre que l'on veut inscrire
   *
   *@return : vrai si le memebre à bien été inscrip faux sinon
   */
  @Override
  public boolean inscriptionEvenement(Evenement evt, InterMembre mbr) {
    if (this.mesEvenement.contains(evt)) {
      for (Evenement ev : mbr.ensembleEvenementsAvenir()) {
        if (!evt.pasDeChevauchementLieu(ev)) {
          return false;
        }  
      }
      if (evt.addparticipant(mbr)) {
        if (mbr.ensembleEvenements().add(evt)) {
          return true;
        }
      }
    }
    return false;
  }
  

  /**
   * Méthode permettant d'annuler un évènement pour un membre particulier.
   * 
   * <p>L'évènement est surppimé de la liste d'évènement du membre 
   * puis le membre est surppimé de la liste des membres de l'évènement
   *
   *@param evt : évènement que l'on veut annuler
   *@param mbr : memebre pour lequel on veut annuler l'évènement
   *
   *@return vrai si le membre faisait partie de l'évènement faux sinon
   */
  @Override
  public boolean annulerEvenement(Evenement evt, InterMembre mbr) {
    if (evt.getParticipants().contains(mbr)) {
      mbr.ensembleEvenements().remove(evt);
      mbr.ensembleEvenementsAvenir().remove(evt);
      evt.delparticipant(mbr);
      
      return true;
    }
    return false;
  }
  
  /**
   * Méthode auto générée hashCode.
   *
   *@return int
   */
  @Override
  public int hashCode() {
    return Objects.hash(mesEvenement);
  }
  
  /**
   * Méthode auto générée equals.
   *
   *@param obj : objet à comparer à l'objet actuel
   *
   *@return vrai si les objets comparé sont égal faux sinon
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    GestionEvenements other = (GestionEvenements) obj;
    return Objects.equals(mesEvenement, other.mesEvenement);
  }
  
  /**
   * Méthodes getter retournant le taille de la liste des évènements du gestionnaire.
   *
   * @return : un int la taille de la liste des évènements
   */
  public int get_size() {
    return this.mesEvenement.size();
  }
  
}
