package fr.insarouen.asi.prog.asiaventure ;

import fr.insarouen.asi.prog.asiaventure.elements.*;
import java.util.*;
import java.io.Serializable;
/**
 * @author H.ETTABAA & T.LECLERC
 * @version 1.0
 * */


public class Monde implements Serializable {
    /**
	 * Nom du monde
	 * */

  private String nom;

    /**
	 * Dictionnaire d'entités contenues dans Monde
	 */


   private Map<String,Entite> tableEntite;

   /**
   * Constructeur de Monde
   * @param nom le nom du Monde
   *
   * */


  public Monde(String nom){
    this.nom = nom;
    this.tableEntite = new HashMap<String,Entite>();
  }
  /**
	 * Retourne le nom du Monde
	 * @return retourne le nom du monde
	 * */

  public String getNom(){
    return this.nom;
  }
  /**
	 * Retourne une Entite dont le nom est passé en paramètre
	 * @param nomEntite le nom de l'entité
	 * @return retourne Entite du tableau recherchée, null sinon
	 * @see Entite
	 * */

  public Entite getEntite(String nomEntite){
    return this.tableEntite.get(nomEntite);
  }
  /**
     * Permet d'ajouter une Entité au Monde
	 * @param ent l'Entite à ajouter au Monde
	 * @see Entite
	 * */

  public void ajouter(Entite ent) throws NomDEntiteDejaUtiliseDansLeMondeException,
                                          EntiteDejaDansUnAutreMondeException{
      if (this.getEntite(ent.getNom())!= null){
        throw new  NomDEntiteDejaUtiliseDansLeMondeException("le nom "+ent.getNom()+" est deja utilisé") ;
      }
      if (!(ent.getMonde().equals(this))){
        throw new EntiteDejaDansUnAutreMondeException("Entite "+ent.getNom()+" présente dans "+ent.getMonde().getNom());
      }
      this.tableEntite.put(ent.getNom(),ent);
  }
  /**
	 * Retourne les informations sur Monde
	 * @return retourne les informations sous forme de String
	 * */

    public String toString(){
        String chaine = nom;
        for (Entite e : this.tableEntite.values()) {
            chaine = chaine + " " + e.getNom();
        }
        return chaine;
    }
    /**
       * Permet de définir si deux mondes sont égaux
       * @param o le monde que l'on veux comparer a ce monde
       * @return vrai si ils sont egaux et faux sinon
       * */
    public boolean equals(Object o){
      if(o==this)
        return true;

      if(o==null || this.getClass() != o.getClass())
        return false;

      if (this.nom.equals(((Monde)o).getNom()))
        return true;
      return false ;
    }
    /**
       * Retourne tout les éxécutables du monde
       * @return Collection d'éxécutables.
       * @see Executable
       * */
    public Collection<Executable> getExecutables() {
        Collection<Executable> resultat = new ArrayList<Executable>();
        for(Entite e : tableEntite.values()){
            if(e instanceof Executable){
                resultat.add((Executable) e);
            }
        }
        return resultat;
  }
}
