package fr.insarouen.asi.prog.asiaventure.elements.structure;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;

/**
 * @author H.ETTABAA & T.LECLERC
 * @version 1.0
 * */


public abstract class ElementStructurel extends Entite  {

    /**
		 * Constructeur d'ElementStructurel
		 * @param nom le nom de l'ElementStructurel
		 * @param monde le Monde auquel appartient l'ElementStructurel
		 * @see Monde
		 * */

    public ElementStructurel(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }
}
