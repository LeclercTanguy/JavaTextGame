package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class Porte extends ElementStructurel implements Activable{
    /**
     * piece que relie la porte
     * */
  private Piece pieceA , pieceB;
  /**
   * l'etat de la porte
   * */
  private Etat etat;
  /**
   * la serrure si elle en a une
   * */
  private Serrure serrure;
  /**
   * constructeur de la porte
   * @param nom
   * @param monde
   * @param pieceA
   * @param pieceB
   * */
  public Porte(String nom,Monde monde,Piece pieceA,Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
    this.pieceA=pieceA;
    this.pieceB=pieceB;
    this.etat = Etat.FERME;
    this.serrure = null;
    this.pieceA.addPorte(this);
    this.pieceB.addPorte(this);
  }
  /**
   * constructeur de la porte
   * @param nom
   * @param monde
   * @param serrure
   * @param pieceA
   * @param pieceB
   * */

public Porte(String nom, Monde monde,Serrure serrure, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
  super(nom,monde);
  this.pieceA=pieceA;
  this.pieceB=pieceB;
  this.etat = Etat.VERROUILLE;
  this.serrure=serrure;
  this.pieceA.addPorte(this);
  this.pieceB.addPorte(this);
}
/**
 * verifie si elle est activable avec un objet
 * @param obj l'objet à vérifier
 * @return Vrai si activable avec l'objet ,Faux sinon
 * */
  @Override
  public boolean activableAvec(Objet obj){
    if ((obj instanceof PiedDeBiche) || (obj instanceof Clef)){
        return true;
    }
    else{
      return false;
    }
  }
  /**
   * active la porte modifie son etat
   * @throws ActivationImpossibleException
   * */
  @Override
  public void activer()throws ActivationImpossibleException{
    if(this.getEtat()==Etat.VERROUILLE){
      throw new ActivationImpossibleException(this.getNom() + " est verouillee");
    }
    else if (this.etat == Etat.FERME){
      this.etat = Etat.OUVERT;
    }
    else{
      this.etat = Etat.FERME;
    }
  }
  /**
   * retourn l'etat de la porte
   * @return l'etat de la porte
   * */
  public Etat getEtat(){
    return this.etat;
  }
  /**
   * Retourne la porte de l'autre coté de la porte par rapport au vivant
   * @return une pièce.
   * */
  public Piece getPieceAutreCote(Piece piece){
    if (this.pieceB == piece){
      return this.pieceA;
    }
    else if(piece == this.pieceA){
      return this.pieceB;
    }
    return null;

  }
  /**
   * Active la porte selon l'objet utilisé
   * @param obj l'objet avec lequel utilisé la porte
   * */
  @Override
  public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException,ActivationImpossibleException{
    if ((obj instanceof PiedDeBiche) && (this.etat != Etat.OUVERT)){
      this.etat = Etat.CASSE;
    }
    else if ((obj instanceof Clef)&& (this.etat!= Etat.CASSE)){
      if ((this.etat==Etat.OUVERT) || (this.etat==Etat.FERME)){
        this.etat=Etat.VERROUILLE;
      }
      else{
        this.etat=Etat.OUVERT;
      }
    }
  }
  /**
   * obtient la serrure de la porte
   * @return la serrure de la porte
   * */
  public Serrure getSerrure(){
    return this.serrure;
  }
}
