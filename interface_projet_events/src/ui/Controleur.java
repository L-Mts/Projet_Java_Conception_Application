package ui;

import association.Evenement;
import association.GestionAssociation;
import association.GestionEvenements;
import association.GestionMembres;
import association.InterMembre;
import association.Membre;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Controleur des interactions avec l'interface.
 *
 * @author Anouk GOUHIER DUPUIS
 * @author Lin WAN
 * @author Loana MOTTAIS
 */

public class Controleur implements Initializable {
  
  @FXML
  private TextField entreAdresseMembre;
  
  @FXML
  private TextField entreAgeMembre;
  
  @FXML
  private TextField entreeDateEvt;
  
  @FXML
  private TextField entreeDureeEvt;
  
  @FXML
  private TextField entreeHeureEvt;
  
  @FXML
  private TextField entreeLieuEvt;
  
  @FXML
  private TextField entreeMaxParticipantsEvt;
  
  @FXML
  private TextField entreeNomEvt;
  
  @FXML
  private TextField entreeNomMembre;
  
  @FXML
  private TextField entreePrenomMembre;
  
  @FXML
  private Label labelListeAfficheeEvt;
  
  @FXML
  private Label labelListeAfficheeMembre;
  
  @FXML
  private ListView<String> listeEvenements;
  
  @FXML
  private ListView<String> listeMembres;
  
  @FXML
  private TextArea message;
  
  @FXML
  private Font x1;
  
  @FXML
  private Color x2;
  
  GestionMembres gestionnaireMbrs;
  GestionEvenements gestionnaireEvts;
  GestionAssociation gestionnaireAsso;

  
  
  /**
   *Affichage des données personnelles d'un membre selectionné.
   *
   *<p>Si un membre est sélectionné dans la liste de gauche, affiche ses
   * informations personnelles dans les 4 champs en haut à gauche de la fenêtre
   */
  @FXML
  void actionBoutonAfficherMembreSelectionneMembre(ActionEvent event) {
    String strMbr = listeMembres.getSelectionModel().getSelectedItem();
    String[] tabStrMbr = strMbr.split(" ");
    for (InterMembre m : this.gestionnaireMbrs.ensembleMembres()) {
      if (m.getInformationPersonnelle().getNom().equals(tabStrMbr[0]) 
          && m.getInformationPersonnelle().getPrenom().equals(tabStrMbr[1])) {
        entreeNomMembre.setText(m.getInformationPersonnelle().getNom());
        entreePrenomMembre.setText(m.getInformationPersonnelle().getPrenom());
        entreAdresseMembre.setText(m.getInformationPersonnelle().getAdresse());
        entreAgeMembre.setText(String.valueOf(m.getInformationPersonnelle().getAge()));
        break;
      }
    }
  }

  /**
   * Affichage de tout les participants d'un évènement sélectionné.
   *
   * <p>Affiche dans la liste de gauche, les participants inscrits à
   * l’événement sélectionné dans la liste de droite.
   */
  @FXML
  void actionBoutonAfficherParticipantsEvt(ActionEvent event) {
    String strEv = listeEvenements.getSelectionModel().getSelectedItem();
    this.listeEvenements.getItems().clear();
    this.listeMembres.getItems().clear();
    for (Evenement ev : this.gestionnaireEvts.mesEvenement) {
      if (ev.getNom().equals(strEv)) {
        this.listeEvenements.getItems().add(strEv);
        for (InterMembre mbr : ev.participants) {
          this.listeMembres.getItems().add(mbr.getInformationPersonnelle().getNom()
                + " " + mbr.getInformationPersonnelle().getPrenom());
        }
        break;
      }
    }
  }

  /**
   * Affichage de tout les membres de l'association.
   *
   * <p>Affiche dans la liste de gauche tous les membres de l’association
   */
  @FXML
  void actionBoutonAfficherTousMembresMembre(ActionEvent event) {
    this.listeMembres.getItems().clear();
    this.message.clear();
    this.labelListeAfficheeMembre.setText("...");
    if (this.gestionnaireMbrs.ensembleMembres.isEmpty()) {
      this.message.setText("L'association n'a aucun membre");
    } else {
      this.labelListeAfficheeMembre.setText("Liste de tous les membres de l'association");
      for (InterMembre mbr : this.gestionnaireMbrs.ensembleMembres()) {
        String nom = mbr.getInformationPersonnelle().getNom();
        String prenom = mbr.getInformationPersonnelle().getPrenom();
        this.listeMembres.getItems().add(nom + " " + prenom);
      }
    }
  }
  
  /**
   * Affichage des informations d'un évènement selectionné.
   *
   * <p>Si un événement est sélectionné dans la liste de droite, 
   * affiche ses informations dans les champs en haut de la fenêtre
   */
  @FXML
  void actionBoutonEvenementSelectionneEvt(ActionEvent event) {
    String strEv = listeEvenements.getSelectionModel().getSelectedItem();
    for (Evenement e : this.gestionnaireEvts.ensembleEvenements()) {
      if (e.getNom().equals(strEv)) {
        this.entreeDureeEvt.setText(String.valueOf(e.getDuree()));
        this.entreeLieuEvt.setText(e.getLieu());
        this.entreeNomEvt.setText(e.getNom());
        this.entreeHeureEvt.setText(String.valueOf(e.getDate().getHour()));
        this.entreeMaxParticipantsEvt.setText(String.valueOf(e.getNbParticipantsMax()));
        this.entreeDateEvt.setText(String.valueOf(e.getDate()));
      }
    }
  }
  
  /**
   * Affichage des évènement à venir de l'association.
   *
   * <p>Affiche dans la liste de droite tous les événements à venir de l’association.
   */
  @FXML
  void actionBoutonEvenementsFutursAssociation(ActionEvent event) {
    this.listeEvenements.getItems().clear();
    this.labelListeAfficheeEvt.setText("...");
    this.message.clear();
    if (this.gestionnaireAsso.gestionnaireEvenements().ensembleEvenementAvenir().isEmpty()) {
      this.message.setText("Il n'y a pas d'évenement à venir dans cette association!");
    } else {
      this.labelListeAfficheeEvt.setText("Liste des évènements à venir de l'association");
      for (Evenement e : this.gestionnaireAsso.myEvenement.ensembleEvenementAvenir()) {
        listeEvenements.getItems().add(e.getNom());
      }
    }
  }

  /**
   * Affichage des évènement à venir du membre sélectionné.
   *
   * <p>Affiche dans la liste de droite tous les évènements à venir 
   * du membre sélectionné dans la liste de gauche.
   */
  @FXML
  void actionBoutonEvenementsFutursMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    String strMbr = listeMembres.getSelectionModel().getSelectedItem();
    String[] tabStrMbr = strMbr.split(" ");
    InterMembre m = null;
    for (InterMembre mbr : this.gestionnaireMbrs.ensembleMembres) {
      if (mbr.getInformationPersonnelle().getNom().equals(tabStrMbr[0]) 
            && mbr.getInformationPersonnelle().getPrenom().equals(tabStrMbr[1])) {
        m = mbr;
        break;
      }
    }
    if (m.ensembleEvenementsAvenir().isEmpty()) {
      this.message.setText("Il n'y a pas d'évenement à venir pour le membre selectionné");
    } else {
      for (Evenement e : m.ensembleEvenementsAvenir()) {
        listeEvenements.getItems().add(e.getNom());
      }
    }
    
  }
  
  /**
   * Affichage de tous les évènement d'un membre.
   *
   * <p>Affiche dans la liste de droite tous les événements du
   * membre sélectionné dans la liste de gauche.
   *
   */
  @FXML
  void actionBoutonEvenementsMembreMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    String strMbr = listeMembres.getSelectionModel().getSelectedItem();
    String[] tabStrMbr = strMbr.split(" ");
    InterMembre m = null;
    for (InterMembre mbr : this.gestionnaireMbrs.ensembleMembres) {
      if (mbr.getInformationPersonnelle().getNom().equals(tabStrMbr[0]) 
           && mbr.getInformationPersonnelle().getPrenom().equals(tabStrMbr[1])) {
        m = mbr;
        break;
      }
    }
    if (m.ensembleEvenements().isEmpty()) {
      this.message.setText("Il n'y a pas d'évenement pour le membre selectionné");
    } else {
      for (Evenement e : m.ensembleEvenements()) {
        listeEvenements.getItems().add(e.getNom());
      }
    }
  }
  
  /**
   * Désinscription d'un membre à un évènement.
   *
   * <p>Désinscrit le membre sélectionné dans la liste de
   * gauche de l'événement sélectionné dans la liste de droite,
   * si ce membre fait parti des participants de cet évènement.
   */
  
  @FXML /*l'erreur de checkstyle vient ici du nom de la fonction*/
  void actionBoutonIDesiscrireMembreEvenement(ActionEvent event) {
    String strMbr = listeMembres.getSelectionModel().getSelectedItem();
    String[] tabStrMbr = strMbr.split(" ");
    String strEv = listeEvenements.getSelectionModel().getSelectedItem();
    Evenement ev = null;
    InterMembre m = null;
    for (Evenement evt : this.gestionnaireEvts.mesEvenement) {
      if (evt.getNom().equals(strEv)) {
        ev = evt;
      }
    }
    for (InterMembre mbr : this.gestionnaireMbrs.ensembleMembres) {
      if (mbr.getInformationPersonnelle().getNom().equals(tabStrMbr[0]) 
          && mbr.getInformationPersonnelle().getPrenom().equals(tabStrMbr[1])) {
        m = mbr;
      }
    }
    this.gestionnaireEvts.annulerEvenement(ev, m);
    this.message.clear();
    this.message.setText("Le membre choisi vient d'être désinscrit de l'évènement choisi.");
  }
  
  /**
   * Inscription d'un membre à un évènement.
   * 
   * <p>Si un membre est sélectionné dans la liste de gauche et
   * un événement est sélectionné dans la liste de droite,
   * le membre est inscrit à cet événement (dans la limite des places disponibles).
   */
  @FXML
  void actionBoutonInscrireMembreEvenement(ActionEvent event) {
    String strMbr = listeMembres.getSelectionModel().getSelectedItem();
    String[] tabStrMbr = strMbr.split(" ");
    String strEv = listeEvenements.getSelectionModel().getSelectedItem();
    for (Evenement evt : this.gestionnaireEvts.mesEvenement) {
      if (evt.getNom().equals(strEv)) {
        for (InterMembre mbr : this.gestionnaireMbrs.ensembleMembres) {
          if (mbr.getInformationPersonnelle().getNom().equals(tabStrMbr[0]) 
              && mbr.getInformationPersonnelle().getPrenom().equals(tabStrMbr[1])) {
            if (this.gestionnaireEvts.inscriptionEvenement(evt, mbr)) {
              this.message.clear();
              this.message.setText("Le membre choisi vient d'être inscrit à l'évènement choisi.");
            } else {
              this.message.clear();
              this.message.setText("Le membre n'a pas pu être inscrit à l'évènement choisi.");
            }
          }
        }
      }
    }
  }
  
  
  /**
   * Remise à zéro du contenu des champs de saisie de l'evenement.
   *
   * <p>Efface le contenu des champs de saisie d’un événement afin de
   * pouvoir ajouter un nouvel évènement.
   */
  @FXML
  void actionBoutonNouveauEvt(ActionEvent event) {
    entreeNomEvt.clear();
    entreeLieuEvt.clear();
    entreeDateEvt.clear();
    entreeHeureEvt.clear();
    entreeDureeEvt.clear();
    entreeMaxParticipantsEvt.clear();
    this.message.clear();
    this.message.setText("Les champs de saisie d'un évènement ont été réinitialisés.");
  }
  
  /**
   * Remise à zéro du contenu des champs de saisie du membre. 
   *
   * <p>Efface le contenu des champs de saisie d’un membre
   * afin de rajouter un nouveau membre.
   */
  @FXML
  void actionBoutonNouveauMembre(ActionEvent event) {
    entreeNomMembre.clear();
    entreePrenomMembre.clear();
    entreAdresseMembre.clear();
    entreAgeMembre.clear();
    this.message.setText("Les champs de saisie d'un membre ont été réinitialisés.");
  }
  
  /**
   * Suppression d'un évènement.
   *
   * <p>Efface de la liste des évènement, l'évènement sélectionné.
   * <br>Retire l'évènement des listes d'évènements de tous les membres qui y participait.
   */
  @FXML
  void actionBoutonSupprimerEvt(ActionEvent event) {
    String strEv = listeEvenements.getSelectionModel().getSelectedItem();
    for (Evenement e : this.gestionnaireEvts.ensembleEvenements()) {
      if (e.getNom().equals(strEv)) {
        this.gestionnaireEvts.supprimerEvenement(e);
        break;
      }
    }
    this.message.clear();
    this.message.setText("L'évènement vient d'être supprimé,"
        + "\nréaffichez tous les évènements de l'association pour voir le changement");
  }
  
  /**
   * Suppression d'un membre.
   *
   * <p>Efface de la liste des membres, le membre sélectionné
   * <br>Retire ce membre des listes de participants de tous les évènements
   * auxquels il était inscrit.
   */
  @FXML
  void actionBoutonSupprimerMembre(ActionEvent event) {
    String strMbr = listeMembres.getSelectionModel().getSelectedItem();
    String[] tabStrMbr = strMbr.split(" ");
    for (InterMembre mbr : this.gestionnaireMbrs.ensembleMembres()) {
      if (mbr.getInformationPersonnelle().getNom().equals(tabStrMbr[0]) 
          && mbr.getInformationPersonnelle().getPrenom().equals(tabStrMbr[1])) {
        if (!mbr.ensembleEvenements().isEmpty()) {
          for (Evenement e : mbr.ensembleEvenements()) {
            e.delparticipant(mbr);
          }
        }
        this.gestionnaireMbrs.supprimerMembre(mbr);
        break;
      }
    }
    this.message.clear();
    this.message.setText("Le membre vient d'être supprimé,"
        + "\nréaffichez tous les membres de l'association pour voir le changement");
      
  }


  /**
   * Affichage de tous les évènements de l'association.
   *
   * <p>Affiche, dans la liste de droite, tous les évènements de l'association.
   */
  @FXML
  void actionBoutonTousEvenementsAssociationEvt(ActionEvent event) {
    this.message.clear();
    this.listeEvenements.getItems().clear();
    this.labelListeAfficheeEvt.setText("...");
    if (this.gestionnaireAsso.gestionnaireEvenements().ensembleEvenements().isEmpty()) {
      this.message.setText("L'association n'a aucun évènement !");
    } else {
      this.labelListeAfficheeEvt.setText("Liste de tous les évènements de l'association");
      for (Evenement e : this.gestionnaireAsso.myEvenement.ensembleEvenements()) {
        listeEvenements.getItems().add(e.getNom());
      }
    }
  }
  
  
  /**
   * Lecture et validation des champs de saisie d'un évènement.
   *
   * <p>Si l'évènement existait déjà (nom déjà présent dans la liste des évènement
   * de l'association), ses informations sont mises à jour selon les informations
   * entrées dans les champs de saisie.
   * <br>Si l'évènement n'existe pas, et que le lieu et la date ne rentre pas en conflit
   * avec un évènement de l'association, un nouvel évènement est créé.
   */
  
  @FXML
  void actionBoutonValiderEvt(ActionEvent event) { 
    DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
    DateTimeFormatter formatHeure = DateTimeFormatter.ISO_LOCAL_TIME;
    LocalDate dateEntree = LocalDate.parse(this.entreeDateEvt.getText(), format);
    int jour = dateEntree.getDayOfMonth();
    Month mois = dateEntree.getMonth();
    int annee = dateEntree.getYear();
    LocalTime heureEntree = LocalTime.parse(this.entreeHeureEvt.getText(), formatHeure);
    int heure = heureEntree.getHour();
    int minutes = heureEntree.getMinute();
    LocalDateTime date = LocalDateTime.of(dateEntree, heureEntree);
    int duree = Integer.valueOf(this.entreeDureeEvt.getText());
    String lieu = this.entreeLieuEvt.getText();
    int nbMaxPart = Integer.valueOf(this.entreeMaxParticipantsEvt.getText());
    String nom = this.entreeNomEvt.getText();
    boolean present = false;
    for (Evenement ev : this.gestionnaireEvts.mesEvenement) {
      if (ev.getNom().equals(nom)) {
        ev.setDate(date);
        ev.setLieu(lieu);
        ev.setDuree(duree);
        ev.setNbParticipantsMax(nbMaxPart);
        present = true;
        this.message.setText("Les données de l'évènement ont bien été mise à jour.");
      }
    }
    if (!present) {
      Evenement evt = this.gestionnaireEvts.creerEvenement(nom, lieu, jour, mois, annee,
          heure, minutes, duree, nbMaxPart);
      if (evt != null) {
        this.message.setText("L'évènenement a bien été créé.");
      } else {
        this.message.setText("Conflit avec un autre évènement, l'évènement n'a pas pu être créé");
      }
    }
  }
  
  /**
   * Lecture et validation des champs de saisie d'un membre.
   *
   * <p>Si le membre existait déjà (nom et prénom présent dans la liste des membres),
   * ses informations sont mises à jour selon les données entrées dans les champs de saisie.
   * <br>Si le membre n'existe pas, un nouveau membre est créé.
   */
  
  @FXML
  void actionBoutonValiderMembre(ActionEvent event) {
    String nom = this.entreeNomMembre.getText();
    String prenom = this.entreePrenomMembre.getText();
    String adresse = this.entreAdresseMembre.getText();
    int age = Integer.valueOf(this.entreAgeMembre.getText());
    boolean present = false;
    for (InterMembre m : this.gestionnaireMbrs.ensembleMembres) {
      if (m.getInformationPersonnelle().getNom().equals(nom) 
          && m.getInformationPersonnelle().getPrenom().equals(prenom)) {
        m.getInformationPersonnelle().setAdresse(adresse);
        m.getInformationPersonnelle().setAge(age);
        present = true;
        this.message.setText("Les données du membre ont bien été mises à jour.");
      }
    }
    if (!present) {
      InterMembre mbr = new Membre(nom, prenom, adresse, age);
      this.gestionnaireMbrs.ajouterMembre(mbr);
      this.message.setText("Le nouveau membre a bien été créé.");
    }
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    this.message.clear();
    message.setText("Projet NimporteQuoi\nAuteurs : Loana MOTTAIS, Anouk GOUHIER DUPUIS, Lin WAN");
  }

  /**
   * Chagrement des membres et événements de l’association depuis un fichier.
   *
   * <p>Charges les données des membres et événements de l’association à partir d’un fichier.
   * <br>Une fois chargées, les deux listes affichent tous les membres et tous les événements
   */
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    listeEvenements.getItems().clear();
    listeMembres.getItems().clear();
    this.gestionnaireAsso.chargerDonnees("monAsso.dat");
    for (InterMembre mbr : this.gestionnaireMbrs.ensembleMembres()) {
      this.listeMembres.getItems().add(mbr.getInformationPersonnelle().getNom()
            + " " + mbr.getInformationPersonnelle().getPrenom());
    }
    for (Evenement ev : this.gestionnaireEvts.mesEvenement) {
      this.listeEvenements.getItems().add(ev.getNom());
    }
    this.message.clear();
    message.setText("Chargement des données à partir du fichier 'monAsso'.");
  }
  
  
  /**
   * Remise à zéro des données de l'association.
   * 
   * <p>Efface tous les évènements et membres chargés en mémoire.
   */
  @FXML
  void actionMenuNouveau(ActionEvent event) {
    this.entreAdresseMembre.clear();
    this.entreAgeMembre.clear();
    this.entreAdresseMembre.clear();
    entreAgeMembre.clear();
    entreeDateEvt.clear();
    entreeDureeEvt.clear();
    entreeHeureEvt.clear();
    entreeLieuEvt.clear();
    entreeMaxParticipantsEvt.clear();
    entreeNomEvt.clear();
    entreeNomMembre.clear();
    entreePrenomMembre.clear();
    labelListeAfficheeEvt = null;
    labelListeAfficheeMembre = null;
    listeEvenements.getItems().clear();
    listeMembres.getItems().clear();
    message.clear();
    this.gestionnaireEvts = null;
    this.gestionnaireMbrs = null;
  }
  
  /**
   * Ferme l'application. 
   */
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    Platform.exit();
  }

  /**
   * Sauvegarde des membres et événements de l’association.
   *
   * <p>Les informations de l’association sont sauvegardées dans un
   * fichier.
   */
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    try {
      this.gestionnaireAsso.sauvegarderDonnees("monAsso.dat");
    } catch (IOException e) {
      this.gestionnaireAsso.myEvenement = null;
      this.gestionnaireAsso.myMembres = null;
    }
    this.message.clear();
    this.message.setText("Les données ont bien été sauvagerdées dans le fichier 'monAsso'");
  }
  
  /**
   * Initialisation des objets de l'association.
   * 
   * <p>Création et instantiation des 3 gestionnaires de l'association.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Initialisation de l'interface");
    this.gestionnaireMbrs = new GestionMembres();
    this.gestionnaireEvts = new GestionEvenements();
    this.gestionnaireAsso = new GestionAssociation(gestionnaireEvts, gestionnaireMbrs);
  }
  
}
