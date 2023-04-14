package association;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;
import java.util.Set;

/**
 * Classe Evenement. 
 *
 * @author Lin WAN
 * @author Anouk GOUHIER
 * @author Loana MOTTAIS
 *
 */
public class Evenement implements java.io.Serializable {

  /*-------------------------------------------*/
  /*------------- ATTRIBUTS -------------------*/
  /*-------------------------------------------*/
  
  private static final long serialVersionUID = 1L;

  private String nom;

  private String lieu;
 
  private LocalDateTime date;

  private int duree;

  private int nbParticipantsMax;

  public Set<InterMembre> participants;
  
  /*-----------------------------------------------*/
  /*------------- CONSTRUCTEURS -------------------*/
  /*-----------------------------------------------*/
  
  /**
  * Constructeur Evenement avec paramètre de type LocalDateTime.
  *
  * @param nom : le nom
  * @param lieu : le lieu
  * @param date : la date
  * @param duree : la duree
  * @param nbParticipantsMax : le nombre maximum de participants
  * @param participants : la liste des participants
  */
  public Evenement(String nom, String lieu, LocalDateTime date, int duree, int nbParticipantsMax, 
      Set<InterMembre> participants) {
    super();
    this.nom = nom;
    this.lieu = lieu;
    this.date = date;
    this.duree = duree;
    this.nbParticipantsMax = nbParticipantsMax;
    this.participants = participants;
  }
  
  /**
   * Constructeur Evenement sans paramètre de type LocaDateTime. 
   *
   * @param nom : le nom
   * @param lieu : le lieu
   * @param jour : le jour
   * @param mois : le mois
   * @param annee : l'annee
   * @param heure : l'heure
   * @param minutes : les minutes
   * @param duree : la durée
   * @param nbParticipantsMax : le nombre maximum de participants
   * @param participants : la liste des participants
   */
  public Evenement(String nom, String lieu, int jour, Month mois, int annee, int heure, int minutes,
      int duree, int nbParticipantsMax, Set<InterMembre> participants) {
    super();
    this.nom = nom;
    this.lieu = lieu;
    this.date = creationDate(jour, mois, annee, heure, minutes);
    this.duree = duree;
    this.nbParticipantsMax = nbParticipantsMax;
    this.participants = participants;
  }

  /*------------------------------------------*/
  /*------------- METHODES -------------------*/
  /*------------------------------------------*/
  
  /**
  * Vérification que 2 évènements ne sont pas dans le même lieu au même moment.
  *
  * @param evt : l'évènement à comparer avec this
  *
  *@return vrai si l'évènement n'as ne chavauche pas un autre sur le temps et le lieu faux sinon
  */
  public boolean pasDeChevauchementLieu(Evenement evt) {
    if (this.lieu == evt.lieu) {
      return this.pasDeChevauchementTemps(evt);
    }
    return true;
  }

  /**
  * Vérification que 2 évènements n'ont pas lieu au même moment.
  *
  * @param evt : l'évènement à comparer avec this
  *
  *@return vrai si l'évènement ne chevauche pas un autre au niveau du temps faux sinon
  */
  public boolean pasDeChevauchementTemps(Evenement evt) {
    LocalDateTime thisdebut = this.date;
    LocalDateTime thisfin = this.date.plusHours(duree);
    
    LocalDateTime evtdebut = evt.date;
    LocalDateTime evtfin = evt.date.plusHours(duree);
    
    if (evtdebut.isAfter(thisfin) || thisdebut.isAfter(evtfin)) {
      return true;
    }
    return false;
  }

  /**
   * Constructeur de la date avec LocalDateTime.
   *
   * @param jour : le jour
   * @param mois : le mois
   * @param annee : l'annee
   * @param heure : l'heure
   * @param minutes : les minutes
   *
   *@return retourne une date en format localDateTime
   */
  public LocalDateTime creationDate(int jour, Month mois, int annee, int heure, int minutes) {
    LocalDateTime date;
    try {
      date = LocalDateTime.of(annee, mois, jour, heure, minutes);
    } catch (DateTimeException e) {
      return null;
    }
    return date;
  }
  
  /**
   * Fonction d'ajout d'un participant à l'évènement.
   *
   *@param mbr : membre à ajouter à l'évènement de type InterMembre
   *
   * @return vrai si le membre est bien ajouté faux sinon
   */
  
  public boolean addparticipant(InterMembre mbr) {
    if (mbr != null) {
      if (!this.participants.contains(mbr)) {
        return this.participants.add(mbr);
      }
    }
    return false;
  }
  
  /**
  *Fonction de suppression d'un participant de l'évènement.
  *
  *@param mbr : membre à supprimer de l'évènement de type InterMembre
  *
  *@return vrai si le memebre est surpprimer de l'évènement faux sinon
  */
  
  public boolean delparticipant(InterMembre mbr) {
    if (mbr != null) {
      return this.participants.remove(mbr);
    }
    return false; 
  }
  
  
  /**
   *Fonction qui récupère le nombre de participants.
   *
   *@return le nombre de participant de l'évènement
   */
  public int getNbinscri() {
    return this.participants.size();
  }
  
  
  /**
   * Getter attribut nom.
   *
   * @return nom
   */
  public String getNom() {
    return nom;
  }

  /**
   * Setter attribut nom.
   *
   * @param nom : le nom à mettre
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Getter attribut lieu.
   *
   * @return lieu
   */
  public String getLieu() {
    return lieu;
  }

  /**
   * Setter attribut lieu.
   *
   * @param lieu : le lieu à mettre
   */
  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  /**
   * Getter attribut date.
   *
   * @return date
   */
  public LocalDateTime getDate() {
    return date;
  }

  /**
   * Setter attribut date.
   *
   * @param date : la date à ajouter
   */
  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  /**
   * Getter attribut duree.
   *
   * @return duree
   */
  public int getDuree() {
    return duree;
  }

  /**
   * Setter attribut duree.
   *
   * @param duree : la duree à mettre
   */
  public void setDuree(int duree) {
    this.duree = duree;
  }

  /**
   * Getter attribut NbParticipantsMax.
   *
   * @return nbParticipantsMax
   */
  public int getNbParticipantsMax() {
    return nbParticipantsMax;
  }

  /**
   * Setter attribut nbParticipantsMax.
   *
   * @param nbParticipantsMax : le nbParticipantsMax à mettre
   */
  public void setNbParticipantsMax(int nbParticipantsMax) {
    this.nbParticipantsMax = nbParticipantsMax;
  }

  /**
   * Getter attribut participants.
   *
   * @return the participants
   */
  public Set<InterMembre> getParticipants() {
    return participants;
  }

  /**
   * Setter attribut participants.
   *
   * @param participants :la liste de participants à mettre
   */
  public void setParticipants(Set<InterMembre> participants) {
    this.participants = participants;
  }

  /**
   * Méthodes hashCode auto générée.
   *
   *@return int
   */
  @Override
  public int hashCode() {
    return Objects.hash(nom, lieu, date, duree, nbParticipantsMax);
   
  }
  
  /**
   * Méthode equals auto générée.
   *
   *@param obj à comparer à l'objet actuel
   *
   *@return vrai si les deux objet sont identiques faux sinon
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
    Evenement other = (Evenement) obj;
    return Objects.equals(nom, other.nom) && lieu == other.lieu
        && Objects.equals(date, other.date)
        && Objects.equals(duree, other.duree)
        && Objects.equals(nbParticipantsMax, other.nbParticipantsMax);
  }
 
  /**
   * Méthode to string auto générée puis modifiée pour un affichage cohérent.
   */
  @Override
  public String toString() {
    
    return nom + " auras lieu à " + lieu + " à la date  " + date 
        + " pour une duree de " + duree + "qui contient " + nbParticipantsMax + "participants";
  }

  
  
}