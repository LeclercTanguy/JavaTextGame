package fr.insarouen.asi.prog.asiaventure;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.JoueurHumain;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;
import fr.insarouen.asi.prog.asiaventure.EtatDuJeu;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.CommandeImpossiblePourLeVivantException;

/**
 * @author H.ETTABAA & T.LECLERC
 * @version 1.0
 * */

public class Simulateur{
    /**
     * Le monde dans lequel se passe le jeu
     * */
  private Monde monde;
  /**
   * L'état du jeu
   * */
  private EtatDuJeu etatDuJeu = EtatDuJeu.ENCOURS;
  /**
   * Liste de condition de fin pour définir le joueur à gagné ou perdu.
   * */
  private List<ConditionDeFin> conditionsDeFin = new ArrayList<>();;
  /**
   * Constructeur de simulateur
   * @param monde le Monde
   * @param cdF les condistions de fin
   * */

  public Simulateur(Monde monde, ConditionDeFin... cdF){
    this.monde=monde;
    Collections.addAll(this.conditionsDeFin, cdF);
  }
  /**
   * Constructeur de simulateur
   * @param ois ObjectInputStream pour charger une partie
   * @throws IOException
   * @throws ClassNotFoundException
   * */
  public Simulateur(ObjectInputStream ois)throws IOException,ClassNotFoundException{
    this.monde=(Monde)ois.readObject();
    //this.conditionsDeFin=(List<ConditionDeFin>)ois.readObject();
  }
  /**
   * Constructeur de simulateur
   * @param reader fichier a lire pour créer le simulateur
   * */
  public Simulateur(Reader reader)throws IOException,NomDEntiteDejaUtiliseDansLeMondeException{
    Scanner scr= new Scanner(reader);
    while(scr.hasNext()){
      String Classe=scr.next();
      switch(Classe){
        case "Monde":
          this.monde=construireMonde(scr);
        break;
        case "Piece":
          this.construirePiece(scr,this.monde);
        break;
        case "PorteSerrure":
          this.construirePorteSerrure(scr,this.monde);
        break;
        case "Porte":
          this.construirePorte(scr,this.monde);
        break;
        case "Clef":
          this.construireClef(scr,this.monde);
        break;
        case "JoueurHumain":
          this.construireJoueur(scr,this.monde);
        break;
        case "ConditionDeFinVivantDansPiece":
          construireConditionDeFinVivantDansPiece(scr, this.monde);
        break;
      }
    }
    this.etatDuJeu = EtatDuJeu.ENCOURS;
  }
  /**
   * Obtient l'état du jeu
   * */
  public EtatDuJeu getEtatDuJeu() {
		return this.etatDuJeu;
	}
    /**
     * Obtient le monde
     * */
  public Monde getMonde() {
		return this.monde;
	}


  private Monde construireMonde(Scanner scr){
    return new Monde(scr.next());
  }
  private void construirePiece(Scanner scr,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
     new Piece(scr.next(),monde);
  }
  private void construirePorteSerrure(Scanner scr,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    String nom = scr.next();
    Piece piece1 = (Piece)monde.getEntite(scr.next());
    Piece piece2 = (Piece)monde.getEntite(scr.next());
    Porte porte=new Porte (nom, monde, new Serrure(monde), piece1, piece2);
  }
  private void construirePorte(Scanner scr,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    String nom = scr.next();
    Piece piece1 = (Piece)monde.getEntite(scr.next());
    Piece piece2 = (Piece)monde.getEntite(scr.next());
    Porte porte=new Porte(nom,monde,piece1, piece2);
  }
  private void construireClef(Scanner scr,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    Porte porte=(Porte)monde.getEntite(scr.next());
    Clef cle=porte.getSerrure().creerClef();
    Piece piece=(Piece)monde.getEntite(scr.next());
    piece.deposer(cle);
  }
  private void construireJoueur(Scanner scr,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    String nom = scr.next();
    int pointVie = scr.nextInt();
    int pointForce = scr.nextInt();
    Piece piece = (Piece)monde.getEntite(scr.next());
    new JoueurHumain(nom, monde, pointVie, pointForce, piece);
  }

  private void construireConditionDeFinVivantDansPiece(Scanner scr, Monde monde) {
      if (scr.next().equals("SUCCES")){
          this.ajouterConditionDeFin(new ConditionDeFinVivantDansPiece(EtatDuJeu.SUCCES, (JoueurHumain)monde.getEntite(scr.next()), (Piece)monde.getEntite(scr.next())));
      } else {
          this.ajouterConditionDeFin(new ConditionDeFinVivantDansPiece(EtatDuJeu.ECHEC, (JoueurHumain)monde.getEntite(scr.next()), (Piece)monde.getEntite(scr.next())));
      }
  }

  public void enregistrer(ObjectOutputStream oos) throws IOException{
    oos.writeObject(this.monde);
    // oos.writeObject(this.etatDuJeu);
    // oos.writeObject(this.conditionsDeFin);
  }
  /**
   * ajoute une condition de fin
   * @param cdF la condition de fin
   * */
  public void ajouterConditionDeFin(ConditionDeFin cdf){
    this.conditionsDeFin.add(cdf);
  }
  /**
   * ajoute des conditions de fin
   * @param conditions une collection condition de fin
   * */
  public void ajouterConditionsDeFin(Collection<ConditionDeFin> conditions) {
    this.conditionsDeFin.addAll(conditions);
  }
  /**
   * execute un tour de jeu
   * @throws Throwable
   * */
  public EtatDuJeu executerUnTour() throws Throwable {
    if(getEtatDuJeu() == EtatDuJeu.ENCOURS) {
		for(Executable e : monde.getExecutables()) {
			if(e instanceof JoueurHumain) {
                this.donneOrdreExecutable(e);
			}
		}
		for(Executable e : monde.getExecutables()) {
            e.executer();
        }
        for (ConditionDeFin cond : conditionsDeFin){
            EtatDuJeu etatCdF = cond.verifierCondition();
            if (etatCdF != EtatDuJeu.ENCOURS){
                return etatCdF;
            }
        }
	}
	return getEtatDuJeu();
  }
  /**
   * execute un nombre défini de tour
   * @param n le nombre de tour
   * @throws Throwable
   * */
  public EtatDuJeu executerNbTours(int n) throws Throwable {
		for(int i = 0; i < n && getEtatDuJeu() == EtatDuJeu.ENCOURS; i++)
			this.executerUnTour();
		return getEtatDuJeu();
	}
    /**
     * execute des tours jusqu'a la fin de la partie 
     * @throws Throwable
     * */
  public EtatDuJeu executerJusquALaFin() throws Throwable {
    EtatDuJeu etat = this.getEtatDuJeu();
    while(etat == EtatDuJeu.ENCOURS){
      etat=this.executerUnTour();
    }
		return etat;
	}
    private void donneOrdreExecutable(Executable e){
        JoueurHumain joueur = (JoueurHumain)e;
        System.out.println(joueur);
        System.out.println("entrez un ordre");
        Scanner sc = new Scanner(System.in);
        joueur.setOrdre(sc.nextLine());
    }
}
