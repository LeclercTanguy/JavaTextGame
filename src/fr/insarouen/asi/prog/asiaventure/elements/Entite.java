package fr.insarouen.asi.prog.asiaventure.elements ;

import java.io.Serializable;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;

/**
 * @author H.ETTABAA & T.Leclerc
 * @version 1.0
 * */

public abstract  class Entite implements Serializable{
    	/**
	 * Nom de l'Entite pas modifiable (private)
	 */
  private String nom;

	/**
	 * Monde auquel appartient l'Entite
	 */

  private Monde monde;

  private static int compteur=0;

  //public static final int MAXIMUMNOMALEATOIRE;
/**
	 * Constructeur d'Entite
	 * * crée une entité à partir de son nom, et du nom du monde auquel il appartient
	 * @param nom le nom de l'Entite
	 * @param monde le monde de l'Entite
	 * @see Monde
	 * */
  public Entite(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    try{
      this.nom=nom;
      this.monde=monde;
      monde.ajouter(this);
    } catch (EntiteDejaDansUnAutreMondeException e){
      throw new Error (" Pas possible");
    }
  }
  /**
	 * Retourne Le nom de l'Entite
	 * @return Le nom de L'Entite
	 * */

  public String getNom(){
    return this.nom;
  }
  /**
	 * Retourne Le Monde auquel appartient l'entite
	 * @return Le monde de L'Entite
	 * @see Monde
	 * */

  public Monde getMonde(){
    return this.monde;
  }
  /**
	 * Retourne les informations sur l'Entite
	 * @return retourne les informations sous forme de String
	 * */

  public String toString(){
    return "nom: "+this.getNom() + ", monde: "+this.getMonde().getNom() ;
  }
  /**
 * teste l'égalité entre deux entités
 * @param o
 *		objet qui en réalité est une instance d'Entite
 * @return vrai en cas d'égalité et faux sinon
*/

  public boolean equals(Object o){
    if(o==this)
      return true;

    if(o==null || this.getClass() != o.getClass())
      return false;

    if (this.nom.equals(((Entite)o).getNom()) && this.monde==((Entite)o).getMonde())
      return true;
    return false ;
  }
  /**
     * Creer un nom d'entite qui n'est pas déjà dans le monde
   * @param contexte le nom de base de l'objet
   * @param m le monde dans lequel on veut un nouveau nom
   * @return Un nouveau nom pour l'objet
   * @see Monde
   * */
  public static String CreerNomNonUtilise(String contexte,Monde m){
    String nouveauNom=String.format("%s %s",contexte,compteur);

    while( m.getEntite(nouveauNom) != null){
      compteur++;
      nouveauNom=String.format("%s %s",contexte,compteur);
    }
    return nouveauNom;
  }

  /**
 * permet de donner le hashcode de l'entité
 * @return un entier
 *
*/
  public int hashCode(){
    return 13*this.nom.hashCode() + 17*this.monde.hashCode();
  }

}
