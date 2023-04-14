package association;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 * Classe GestionAssossiation.
 * 
 * <p>Elle permet la gestion des évènements et membres d'une association avec
 * un fichier qui sauvegarde l'ensemble des données des évènements et des membres.
 *
 * @author Lin Wan
 *
 */

public class GestionAssociation implements InterGestionAssociation {
  /*-------------------------------------------*/
  /*------------- ATTRIBUTS -------------------*/
  /*-------------------------------------------*/
  /**
   *Gestionnaires des évenements et des membres.
   *
   * <p>les 2 attributs servent à stocker l'ensemble 
   * des membres et evenements chargé à partir du fichier</p>
   */
  public InterGestionEvenements myEvenement;
  public InterGestionMembres myMembres;
  
  /*-------------------------------------------*/
  /*------------- CONSTRUCTEUR ----------------*/
  /*-------------------------------------------*/
  /**
   * Constructeur de GestionAssociation.
   *
   *@param gestionEvenement : gestionnaire d'évènement à ajouter
   *@param gestionMembre : gestionnaire de membre à ajouter
   */
  
  public GestionAssociation(InterGestionEvenements gestionEvenement, 
      InterGestionMembres gestionMembre) {
    this.myEvenement = gestionEvenement;
    this.myMembres = gestionMembre;
  }
  
  /**
   *Méthode getter pour le gestionnaire d'evenement.
   *
   *@return le gestionnaire d'évènement
   */
  @Override
  public InterGestionEvenements gestionnaireEvenements() {
    return this.myEvenement;
  }
  
  /**
   * Méthode getter pour le gestionnaire des membres.
   *
   *@return le gestionnaire d'évènement
   */
  @Override
  public InterGestionMembres gestionnaireMembre() {
    return this.myMembres;
  }
  
  /**
   * Méthode permettant la sauvegarde des données.
   *
   *@param nomFichier : nom du fichier de sauvegarde
   */
  @Override
  public void sauvegarderDonnees(String nomFichier) throws IOException {
    File file = new File(nomFichier);
    if (!file.exists()) {
      file.createNewFile();
    }
    try {
      FileOutputStream fos = new FileOutputStream(file); 
      ObjectOutputStream oos = new ObjectOutputStream(fos); 
      oos. writeObject(this);
      oos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * Méthode permettant la récupération des données.
   *
   *@param nomFichier : nom du fichier d'ou les données seront chargéeq
   */
  @Override
  public void chargerDonnees(String nomFichier) {
    File file = new File(nomFichier);
    
    try {
      FileInputStream fis = new FileInputStream(file); 
      ObjectInputStream ois = new ObjectInputStream(fis);
      GestionAssociation g = (GestionAssociation) ois. readObject();
      this.myEvenement = g.myEvenement;
      this.myMembres = g.myMembres;
      ois.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
