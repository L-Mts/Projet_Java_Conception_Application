package association;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe Membres.
 *
 * @author Anouk GOUHIER DUPUIS
 * @author Loana MOTTAIS 
 * @author Lin WAN
 *
 */

public class Membre implements java.io.Serializable, InterMembre {
  
  /*-------------------------------------------*/
  /*------------- ATTRIBUTS -------------------*/
  /*-------------------------------------------*/

  private static final long serialVersionUID = 1L;

  public InformationPersonnelle infoperso;

  public List<Evenement> evenements;
  
  public List<Evenement> avenir;
  
  /*-----------------------------------------------*/
  /*------------- CONSTRUCTEURS -------------------*/
  /*-----------------------------------------------*/
  
  /**
   *Constructeur de la classe membre.
   *
   *<p>Initialise les informations personnelles du membre,
   *la liste de tout ses evenements et celle de ceux à venir
   *
   *@param nom : nom du membre à ajouter  string
   *@param prenom : prenom du membre  string
   *@param adresse : adresse du membre  string
   *@param age : age du membre  int
   */
  public Membre(String nom, String prenom, String adresse, int age) {
 
    this.infoperso = new InformationPersonnelle(nom, prenom, adresse, age);
    this.evenements = new ArrayList<Evenement>();
    this.avenir = new ArrayList<Evenement>();
  }
  
  
  /*------------------------------------------*/
  /*------------- METHODES -------------------*/
  /*------------------------------------------*/

  /**
   * Pour initialiser l'ensemble d'évenement.
   *
   * @param nouveau : ensemble d'évenement à attribuer au membre
   */
  public void setlistEvenement(List<Evenement> nouveau) {
    this.evenements = nouveau;
  }
  
  /**
   * Rajouter un évenement dans l'ensemble d'évenement.
   * L'évènement est ajouter seulement s'il n'est pas déjà présent dans
   * les évènements du membre 
   *
   * @param evenement : l'évènement à ajouter à la liste d'évènement du membre
   */
  public void setEvenement(Evenement evenement) {
    if (this.evenements.isEmpty()) {
      this.evenements.add(evenement);
    } else {
      int p = 1;
      for (Evenement e : this.evenements) {
        if (e.equals(evenement)) {
          p = 0;
        }
      }
      if (p == 1) {
        this.evenements.add(evenement);
      }
    }
    
  }
  
  /**
   * Méthode get.
   *
   *@return la liste de tout les evenements du membre.
   */
  
  public List<Evenement> ensembleEvenements() {
    return this.evenements;
  }
  
  /**
   * Méthode get.
   *
   *@return : la liste de tout les evenement à venir du membre
   */
  
  public List<Evenement> ensembleEvenementsAvenir() {
    if (this.evenements.isEmpty()) {
      return this.avenir;
    }
    for (Evenement e : this.evenements) {
      if (e.getDate().isAfter(LocalDateTime.now())) {  
        this.avenir.add(e); // ajoute l'évènement à venir dans la liste avenir
      }
    }
    return this.avenir;
  }
  /**
   * Méthode set.
   * Modifie les informations personnelles du membre 
   *
   * @param info : instance de la classe information personnelle
   */
 
  public void definirInformationPersonnnelle(InformationPersonnelle info) {
    this.infoperso = info;  
  }

  /**
   * Méthode get.
   *
   *@return : les informatiion personnelles d'un membre 
   */
  public InformationPersonnelle getInformationPersonnelle() {
    return this.infoperso;
  }

  /**
 * Méthode auto générée hashCode.
 *
 * @return int
 */
  @Override
  public int hashCode() {
    return Objects.hash(avenir, evenements, infoperso);
  }

  /**
 * Méthode auto générée equals.
 *
 * @param obj : objet avec lequel le membre actuel est comparé
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
    Membre other = (Membre) obj;
    return Objects.equals(avenir, other.avenir) && Objects.equals(evenements, other.evenements)
         && Objects.equals(infoperso, other.infoperso);
  }
}