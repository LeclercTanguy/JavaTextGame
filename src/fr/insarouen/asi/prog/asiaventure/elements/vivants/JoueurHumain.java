package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.Executable;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ElementStructurel;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import java.lang.NoSuchMethodException;
import java.lang.SecurityException;
import java.lang.IllegalAccessException;
import java.lang.IllegalArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.io.Serializable;
import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

/**
 * @author H.ETTABAA & T.Leclerc
 * @version 1.0
 * */

public class JoueurHumain extends Vivant implements Executable , Serializable {
    /**
     *Ordre qu'éxécute le joueur humain.
     * */
  private String ordre;
  /**
  	 * Constructeur de JoueurHumain
  	 * * crée un JoueurHumain comme pour vivant
     * @param nom le nom du Vivant
	 * @param monde le monde ou se situe le Vivant
	 * @param pointVie le nombre de point de Vie du Vivant
	 * @param pointForce le nombre de point de Force du Vivant
	 * @param piece la Piece ou se situe le Vivant
	 * @param objets les objets que possède le Vivant
  	 * */
  public JoueurHumain(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets)throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde,pointVie,pointForce,piece,objets);
    this.ordre = String.format("");
  }
  /**
   * Permet de donner un ordre.
   * @param ordre L'odre a donné
   * */
  public void setOrdre(String ordre) {
		this.ordre = ordre;
	}


  private void commandePrendre(String nomObjet)throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
      this.prendre(nomObjet);
  }

  private void commandePoser(String nomObjet)throws ObjetNonPossedeParLeVivantException{
    if (!(this.possede(this.getObjet(nomObjet)))){
        throw new ObjetNonPossedeParLeVivantException(this.getNom() + " ne possede pas l'objet " + nomObjet);
    }
    this.deposer(nomObjet);
  }

  private void commandeFranchir(String nomPorte)throws PorteFermeException, PorteInexistanteDansLaPieceException,VivantAbsentDeLaPieceException{
    if(this.getPiece().getPorte(nomPorte)!= null){
        this.franchir(nomPorte);
    }
    else {
        throw new VivantAbsentDeLaPieceException(this.getNom()+" ne se trouve pas dans la Piece correspondante a cette porte.");
}
  }


  private void commandeOuvrirPorte(String nomPorte)throws ActivationException, ActivationImpossibleException, PorteInexistanteDansLaPieceException{
    if(this.getPiece().getPorte(nomPorte)==null){
        throw new PorteInexistanteDansLaPieceException("la Porte "+nomPorte+" ne se trouve pas dans cette piece.");
    }
    else{
        this.activerActivable(this.getPiece().getPorte(nomPorte));
    }
  }


  private void commandeOuvrirPorte(String nomPorte, String nomObjet)throws ActivationException, PorteInexistanteDansLaPieceException,ObjetNonPossedeParLeVivantException{
    Objet objet = getObjet(nomObjet);
    if(!(this.getPiece().aLaPorte(nomPorte))){
      throw new PorteInexistanteDansLaPieceException(nomPorte+" n'existe pas dans la piece");
    }
    if((this.getPiece().getPorte(nomPorte).getEtat()==Etat.FERME) || (this.getPiece().getPorte(nomPorte).getEtat()==Etat.DEVERROUILLE)){
      Porte porte =this.getPiece().getPorte(nomPorte);
      this.activerActivableAvecObjet(porte,objet);
    }

  }
  private Method obtenirMethod(String[] laChaine)throws NoSuchMethodException,SecurityException{
    Class<?> classe = this.getClass();
    String commande = "commande"+laChaine[0];
    Class[] param = new Class[laChaine.length-1];
    Arrays.fill(param,String.class);
    return classe.getDeclaredMethod(commande,param);
  }
  private Object[] obtenirParamEffectif(String[] laChaine){
      return Arrays.copyOfRange(laChaine, 1, laChaine.length);
  }
  /**
   * Exécute l'odre.
   * @see Executable
   * */
  @Override
  public void executer() throws CommandeImpossiblePourLeVivantException,Throwable{
    Class<?> classe = this.getClass();
    String[] ordreDecoupe = this.ordre.split(" ");
    Object[] param = this.obtenirParamEffectif(ordreDecoupe);
    try{
      Method methode = this.obtenirMethod(ordreDecoupe);
      methode.invoke(this,param);
    }
    catch(InvocationTargetException e){
      throw new ASIAventureException(e.getCause());
    }
    catch (Exception e){
      throw new CommandeImpossiblePourLeVivantException("Commande impossible");
    }

  }

}
