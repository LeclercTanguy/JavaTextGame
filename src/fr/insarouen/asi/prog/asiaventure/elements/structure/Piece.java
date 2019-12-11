package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ElementStructurel;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import java.util.*;

/**
 * @author H.ETTABAA & T.LECLERC
 * @version 1.0
 * */


public class Piece extends ElementStructurel{
  /**
	 * tableau d'Objet de la Piece
  * */


  private Map<String,Objet> objets;
  /**
	 * tableau de Vivant de la Piece
	 * */

  private Map<String,Vivant> vivants;
  private Map<String,Porte> portes;
  /**
	 * Constructeur de Piece
	 * crée une Piece à partir d'un nom, et du nom du monde auquel elle appartient
	 * @param nom le nom de la Piece
	 * @param monde le monde auquel appartient la Piece
	 * @see Monde
	 * */

  public Piece(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{

      super(nom,monde);
      this.objets= new HashMap<String,Objet>();
      this.vivants= new HashMap<String,Vivant>();
      this.portes= new HashMap<String,Porte>();
  }
  /**
	 * Permet de tester si un objet est présent ou pas à partir d'un Objet
	 * @param obj L'objet à tester
	 * @return retourne un booleen
	 * @see Objet
	 * */

  public boolean contientObjet(Objet obj){
      String nomObj = obj.getNom();
      return contientObjet(nomObj);


  }
  /**
     * Permet de tester si un objet est présent ou pas à partir du nom de l'objet
	 * @param nomObj L'objet à tester
	 * @return retourne un booleen
	 * @see Objet
	 * */

  public boolean contientObjet(String nomObj){
    return this.objets.containsKey(nomObj);

  }
  /**
	 * Permet de deposer L'objet dans la Piece qui est passé en paramètre
	 * @param obj L'objet à déposer
	 * @see Objet
	 * */
  public void deposer(Objet obj){
    this.objets.put(obj.getNom(),obj);
  }
  /**
	 * Permet de retirer l'Objet de la Piece qui est passé en paramètre
	 * @param obj L'Objet à retirer
	 * @return retourne L'objet s'il est dans la Piece, sinon il renvoie null
	 * @see Objet
	 * */
  public Objet retirer(Objet obj) throws ObjetAbsentDeLaPieceException,
                                            ObjetNonDeplacableException{
    Objet objet = null;
    try{
        objet =this.retirer(obj.getNom());
    }
    catch (ObjetAbsentDeLaPieceException e){
        throw new ObjetAbsentDeLaPieceException("abesnt rajouter explication");
    }
    catch (ObjetNonDeplacableException e){
        throw new ObjetNonDeplacableException("non deplacable");
    }
    return objet;

  }
    /**
	 * Permet de retirer l'Objet de la Piece dont le nom est passé en paramètre
	 * @param obj L'Objet à retirer
	 * @return retourne L'objet s'il est dans la Piece, sinon il renvoie null
	 * @see Objet
	 * */
  public Objet retirer(String nomObj)throws ObjetAbsentDeLaPieceException,
                                            ObjetNonDeplacableException{
    if(!(this.contientObjet(nomObj))){
        throw new ObjetAbsentDeLaPieceException(nomObj+" pas dans cette pièce");
    }
    Objet objet = this.objets.remove(nomObj);
      if(!(objet.estDeplacable())) {
              throw new ObjetNonDeplacableException(" non déplacable ");
          }
    return objet;

  }
  /**
	 * Permet de tester si la Piece contient le nom du Vivant
	 * @param nomVivant Le nom du vivant à tester
	 * @return retourne un booleen
	 * @see Vivant
	 * */
  public boolean contientVivant(String nomVivant){
    return this.vivants.containsKey(nomVivant);
  }
    /**
	 * Permet de tester si la Piece contient un Vivant
	 * @param vivant Le Vivant à tester
	 * @return retourne un booleen
	 * @see Vivant
	 * */
  public boolean contientVivant(Vivant vivant){
    return contientVivant(vivant.getNom());
  }
  /**
	 * Ajoute un Vivant dans la Piece
	 * @param vivant Le Vivant à ajouter
	 * @see Vivant
	 * */
  public void entrer(Vivant vivant){
    this.vivants.put(vivant.getNom(),vivant);
  }
  /**
	 * Retire le Vivant de la Piece dont le nom est passé en paramètre
	 * @param nomVivant le nom du Vivant à enlever de la Piece
	 * @see Vivant
	 * */
  public Vivant sortir(String nomVivant){
    return this.vivants.remove(nomVivant);
  }
  /**
	 * Retire le Vivant de la Piece
	 * @param vivant le Vivant à enlever de la Piece
	 * @see Vivant
	 * */
  public Vivant sortir(Vivant vivant){
    return this.sortir(vivant.getNom());
  }
  /**
    * Ajoute une porte a la liste
    * @param porte la porte a ajouter
    * @see Porte
    * */
  protected void addPorte(Porte porte){
    this.portes.put(porte.getNom(),porte);
  }
  /**
    * Verifie si une porte est dans la piece
    * @param porte le nom de la porte à vérifier
    * @return Vrai si elle présente, Faux sinon.
    * @see Porte
    * */
  public boolean aLaPorte(String nomPorte){
    return this.portes.containsKey(nomPorte);
  }
  /**
    * Verifie si une porte est dans la piece
    * @param porte la porte à vérifier
    * @return Vrai si elle présente, Faux sinon.
    * @see Porte
    * */
  public boolean aLaPorte(Porte porte){
    return this.aLaPorte(porte.getNom());
  }
  /**
    * Obtient une porte
    * @param nomPorte le nom de la porte
    * @return la Porte voulue.
    * @see Porte
    * */
  public Porte getPorte(String nomPorte){
    return this.portes.get(nomPorte);
  }
  /**
    * obtient toutes les portes de la piece
    * @return La collection de porte
    * @see Porte
    * */
  public Collection<Porte> getPortes(){
    return this.portes.values();
  }
  /**
    * obtient tout les objets de la piece
    * @return La collection d'objets
    * @see Objets
    * */
  public Collection<Objet> getObjets(){
    return this.objets.values();
  }
  public String toString(){
      String nomsPorte="";
      Collection<Porte> s = portes.values();
      for (Porte p: s){
        nomsPorte = nomsPorte + "La " + p.getNom() + " est " + p.getEtat() + ", ";
      }
      String nomsObjets="";
      Set<String> so = objets.keySet();
      for (String o: so){
        nomsObjets = nomsObjets + " " + o;
      }
      return this.getNom() + nomsPorte + nomsObjets;
  }

}
