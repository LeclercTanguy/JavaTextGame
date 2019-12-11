package fr.insarouen.asi.prog.asiaventure.elements.objets;


import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
/**
 * @author H.ETTABAA & T.LECLERC
 * @version 1.0
 * */

public abstract class Objet extends Entite{
  /**
	 * Constructeur de l'Objet
	 * @param nom le nom de l'Objet
	 * @param monde le Monde auquel appartient l'Objet
	 * @see Monde
	 * */
  public Objet(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
  }

  /**
	 * Méthode abstaite permettant de savoir si l'Objet est déplacable
	 * */
  public abstract boolean estDeplacable();


}
