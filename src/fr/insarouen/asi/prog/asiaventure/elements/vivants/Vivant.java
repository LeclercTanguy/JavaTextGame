package fr.insarouen.asi.prog.asiaventure.elements.vivants;

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
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;






import java.util.*;
/**
 * @author H.ETTABAA & T.LECLERC
 * @version 1.0
 * */


public abstract class Vivant extends Entite implements Executable{
    /**
	 * Nombre de point de vie du vivant
	 * */

  private int pointVie;
  /**
	 * Nombre de point de force du vivant
	 * */

  private int pointForce;
  /**
 * Pièce dans laquelle se trouve le vivant
*/

  private Piece piece;
  /**
	 * Le dictionnaire d'Objet du Vivant
	 * */


  private Map<String,Objet> nsac;
  /**
	 * Constructeur de Vivant
	 *
	 * @param nom le nom du Vivant
	 * @param monde le monde ou se situe le Vivant
	 * @param pointVie le nombre de point de Vie du Vivant
	 * @param pointForce le nombre de point de Force du Vivant
	 * @param piece la Piece ou se situe le Vivant
	 * @param objets les objets que possède le Vivant
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 *
	 * */

  public Vivant(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
    super(nom,monde);
    this.pointVie=pointVie;
    this.pointForce=pointForce;
    this.piece=piece;
    this.piece.entrer(this);
    this.nsac = new HashMap<String,Objet>();
    for (int i=0;i<objets.length ;i++ ) {
      this.nsac.put(objets[i].getNom(),objets[i]);
    }
  }
  /**
	 * Permet de déposer un Objet dans la pièce actuelle du Vivant
	 * @param nomObj Le nom de l'objet a déposer
	 * @see Objet
	 * */

  public void deposer(String nomObj){
    this.piece.deposer(this.nsac.get(nomObj));
    this.nsac.remove(nomObj);
  }
  /**
	 * Permet de déposer un objet que possède le Vivant dans la Piece
	 * @param obj L'Objet a déposer
	 * @see Objet
	 * */
  public void deposer(Objet obj){
    this.deposer(obj.getNom());
  }
  /**
	 * Retourne l'Objet dont le nom est passé en paramètre
	 * @param nomObj le nom de l'Objet
	 * @return retourne l'Objet dont le nom est passé en paramètre
	 * @see Objet
	 * */

  public Objet getObjet(String nomObj){
    return this.nsac.get(nomObj);
  }
  /**
	 * Retourne le tableau d'Objet
	 * @return retourne le tableau d'Objet
	 * @see Objet
	 * */

  public Map<String,Objet> getObjets(){
    return this.nsac;
  }
/**
	 * Retourne un booleen pour savoir si le Vivant possède l'objet
	 * @param obj Objet à chercher
	 * @return Retourne true si l'Objet est trouvé, false sinon
	 * @see Objet
	 * */

  public boolean possede(Objet obj){
    return this.nsac.containsValue(obj);
  }
  /**
	 * Permet de prendre un Objet dans la pièce du Vivant à partir de son nom
	 * @param nomObj nom de l'Objet à prendre
	 * @see Objet
	 * @see Piece
     * @throws ObjetAbsentDeLaPieceException
     * @throws ObjetNonDeplacableException
	 * */

  public void prendre(String nomObj) throws ObjetAbsentDeLaPieceException,
                                            ObjetNonDeplacableException{
    Objet obj = this.piece.retirer(nomObj);
    this.nsac.put(nomObj,obj);
  }
  /**
	 * Permet de prendre un Objet dans la pièce du Vivant
	 * @param obj L'objet à prendre
	 * @see Objet
     * @throws ObjetAbsentDeLaPieceException
     * @throws ObjetNonDeplacableException
	 * */

  public void prendre(Objet obj) throws ObjetAbsentDeLaPieceException,
                                            ObjetNonDeplacableException{
    this.prendre(obj.getNom());
  }
  /**
	 * Retourne la Piece ou se trouve Le Vivant
	 * @return retourne la Pièce
	 * @see Piece
	 * */

  public Piece getPiece(){
    return this.piece;
  }

 /**
    * Permet au vivant de franchir une porte à partir de son nom
    * @param nomPorte nom de la porte a franchir
    * @throws PorteFermeException
    * @throws PorteInexistanteDansLaPieceException
    */
  public void franchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
      if (!getPiece().aLaPorte(nomPorte)) {
         throw new PorteInexistanteDansLaPieceException("La porte "+ nomPorte + " n'est pas dans cette piece.");
      }

      Porte porte = getPiece().getPorte(nomPorte);

      if (porte.getEtat() == Etat.FERME) {
		throw new PorteFermeException("La porte "+nomPorte+" est fermée");
      }
      this.piece=porte.getPieceAutreCote(this.piece);
      this.piece.entrer(this);
  }

   /**
    * Permet au vivant de franchir une porte
    * @param porte
    *   Porte à franchir
    */
  public void franchir(Porte porte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
		franchir(porte.getNom());
  }
  /**
	 * Permet de savoir si un Vivant est mort
	 * @return Vrai si mort , Faux sinon
	 */

  public boolean estMort() {
		return this.getPointVie() <= 0;
	}
    /**
       * Permet d'obtenir les points de vie du vivants
       * @return les points de force
       * */
  public int getPointVie(){
    return this.pointVie;
  }
  /**
   * Permet d'obtenir les points de force du vivant.
   * @return les points de force
   * */

  public int getPointForce(){
    return this.pointForce;
  }
  /**
   * Permet de fixer les points de vie du vivant.
   * */
  public void setPointsDeVie(int pv){
      this.pointVie = pv;
  }

  public String toString(){
    String nomsObjets="";
    Set<String> s = nsac.keySet();
    for (String e: s){
      nomsObjets = nomsObjets + " " + e;
    }
    return this.getNom() + nomsObjets + this.getPiece().toString();
   }
   /**
   * Activer l'activable.
   * @param activable l'activable à activer
   * @see Activable
   * @throws ActivationException
   * */
   public void activerActivable(Activable activable) throws ActivationException{
       activable.activer();
   }
   /**
   * Activer l'activable avec un objet.
   * @param activable l'activable à activer
   * @param objet l'objet avec lequel activer l'activable
   * @see Activable
   * @see Object
   * @throws ActivationException
   * */
   public void activerActivableAvecObjet(Activable activable, Objet objet) throws ActivationException{
        activable.activerAvec(objet);
   }


}
