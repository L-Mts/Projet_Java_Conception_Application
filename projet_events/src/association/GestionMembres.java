package association;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe GestionMembres.
 *
 * @author Loana MOTTAIS
 */
public class GestionMembres implements InterGestionMembres, java.io.Serializable {
  
  /*-------------------------------------------*/
  /*------------- ATTRIBUTS -------------------*/
  /*-------------------------------------------*/
  
  private static final long serialVersionUID = 1L;

  /**
   * Attribut ensembleMembres.
   *
   * <p>Collection des membres de l'association
   */
  public Set<InterMembre> ensembleMembres;
  
  /**
   * Attribut president.
   *
   * <p>président de l'association
   */
  public InterMembre president;
  
  /*-----------------------------------------------*/
  /*------------- CONSTRUCTEURS -------------------*/
  /*-----------------------------------------------*/
  
  /**
   * Constructeur instance vide de la classe GestionMembres.
   * 
   */
  public GestionMembres() {
    this.ensembleMembres = new HashSet<InterMembre>();
    this.president = null;
  }
  
  /**
   * Constructeur instance initialisée de la classe GestionMembres.
   *
   * <p>this.ensembleMembres : collection des membres de l'associations <br>
   * this.president : président de l'association, initialisé à null si membre non
   *
   *@param esblMembres : collection d'élément de type InterMembre
   *@param membre : membre faisant ou non partie de la collection de type InterMembre
   */
  public GestionMembres(Set<InterMembre> esblMembres, InterMembre membre) {
    this.ensembleMembres = esblMembres;
    if (esblMembres.contains(membre)) {
      this.president = membre;
    } else {
      this.president = null;
    }
  }
  
  /*------------------------------------------*/
  /*------------- METHODES -------------------*/
  /*------------------------------------------*/
  /**
   * Méthode permettant d'ajouter un membre au gestionnaire.
   *
   *@param membre : membre à ajouter de type InterMembre
   *@return vrai si le membre à bien été ajouté faux sinon
   */
  @Override
  public boolean ajouterMembre(InterMembre membre) {
    if (this.ensembleMembres.contains(membre)) {
      return false;
    }
    this.ensembleMembres.add(membre);
    return true;
  }
  
  /**
   * Méthode permettant de supprimer un membre du gestionnaire.
   *
   *@param membre : membre à retirer du gestionnaire de type InterMembre
   *@return vrai si le membre à bien été supprimé faux sinon
   */
  @Override
  public boolean supprimerMembre(InterMembre membre) {
    if (!this.ensembleMembres.contains(membre)) {
      return false;
    }
    if (this.president.equals(membre)) {
      this.president = null;
    }
    this.ensembleMembres.remove(membre);
    return true;
  }
  
  /**
   * Méthode permettant de désigné un président.
   *
   *@param membre : membre qui sera ajouté comme président
   *@return vrai si le membre a été désigné comme président faux sinon
   */
  @Override
  public boolean designerPresident(InterMembre membre) {
    if (!this.ensembleMembres.contains(membre)) {
      return false;
    }
    this.president = membre;
    return true;
  }
  
  /**
   * Méthode getter de la collection des membres du gestionnaire.
   *
   *@return : la collection des membres du gestionnaire
   */
  @Override
  public Set<InterMembre> ensembleMembres() {
    return this.ensembleMembres;
  }
  
  /**
   * Méthode getter du président des membres du gestionnaire.
   *
   *@return : le président 
   */
  @Override
  public InterMembre president() {
    return this.president;
  }

}
