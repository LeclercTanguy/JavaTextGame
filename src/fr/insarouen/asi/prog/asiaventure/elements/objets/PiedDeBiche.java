package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
/**
 * @author H.ETTABAA & T.LECLERC
 * @version 1.0
 * */
public class PiedDeBiche extends Objet{

  /**
	 * Constructuer de Pied de Biche
	 * @param nom le nom du Pied de Biche
	 * @param monde le Monde auquel appartient le Pied de Biche
	 * @see Monde
	 * */
  public PiedDeBiche(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
  }
  /**
	 * Methode qui retoune si le Pied de Biche est déplacable
	 * @return retourne un booléen
	 * */

  public boolean estDeplacable(){
    return true;
  }
}
