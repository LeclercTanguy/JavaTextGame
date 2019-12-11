package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.Executable;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;

import java.util.*;
import java.util.Collections;

/**
 * @author H.ETTABAA & T.Leclerc
 * @version 1.0
 * */

public class Monstre extends Vivant implements Executable {
    /**
  	 * liste de Porte près du monstre
    * */

  private List<Porte> lesPortes;

  /**
   * liste d'Objet du monstre
  * */


  private List<Objet> lesObjets;
  /**
   * Constructeur de Monstre
   * @see Vivant
  * */

  public Monstre(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
    super(nom,monde,pointVie,pointForce,piece,objets);
    this.lesPortes = new ArrayList<Porte>(this.getPiece().getPortes());
  }
  /**
   * Fait franchir une porte aleatoire au monstre.
   * @throws PorteFermeException
   * @throws PorteInexistanteDansLaPieceException
  * */
  public void franchirPorte() throws PorteFermeException, PorteInexistanteDansLaPieceException {
      Collections.shuffle(this.lesPortes);
      this.franchir(lesPortes.get(0));
  }
  /**
   * Fait rammaser tout les objets de la piece au monstre.
   * @throws ObjetAbsentDeLaPieceException
   * @throws ObjetNonDeplacableException
  * */
  public void toutPrendre() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
    this.lesObjets = new ArrayList<Objet>(this.getPiece().getObjets());
    for(Objet lObjet : this.lesObjets){
      this.prendre(lObjet);
    }
  }
  /**
   * Le monstre dépose tout ses objets
   * @throws ObjetNonPossedeParLeVivantException
  * */
  public void toutDeposer()throws ObjetNonPossedeParLeVivantException {
    for (Objet lObjet: this.lesObjets){
      this.deposer(lObjet);
    }
  }
  /**
   * éxécute le monstre le fait se deplacer et gere ses objets.
   * @see Activable
   * @throws PorteFermeException
   * @throws ObjetAbsentDeLaPieceException
   * @throws ObjetNonDeplacableException
   * @throws ObjetNonPossedeParLeVivantException
   * @throws PorteInexistanteDansLaPieceException
  * */
  @Override
  public void executer() throws PorteFermeException, ObjetAbsentDeLaPieceException, ObjetNonDeplacableException, ObjetNonPossedeParLeVivantException, PorteInexistanteDansLaPieceException {
    if(!this.estMort()){
      this.toutPrendre();
      this.franchirPorte();
      this.toutDeposer();
      this.setPointsDeVie(this.getPointVie()-1);
    }
  }
}
