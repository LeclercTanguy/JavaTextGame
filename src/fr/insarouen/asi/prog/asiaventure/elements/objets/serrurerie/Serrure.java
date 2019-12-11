package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;


public class Serrure extends Objet implements Activable {
  private Etat etat;
  private Clef clef;
  private boolean demande;
  public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    this(CreerNomNonUtilise("Serrure",monde),monde);
  }
  public Serrure(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
    this.etat = Etat.VERROUILLE;
    this.clef = new Clef("clef " + this.getNom(),monde);
    this.demande = false;
  }
  @Override
  public boolean estDeplacable(){
    return false;
  }
  @Override
  public void activer() throws ActivationImpossibleException {
        throw new ActivationImpossibleException("La serrure est cassé");
    }
  @Override
  public boolean activableAvec(Objet obj){
    return ((obj instanceof Clef) && (this.clef==obj));
  }
  @Override
  public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException{
    if (!(obj instanceof Clef) || !(this.clef == obj)){
      throw new ActivationImpossibleAvecObjetException("Serrure non activable avec cet objet");
    }
    else {
      if (this.etat == Etat.VERROUILLE){
        this.etat = Etat.DEVERROUILLE;
      }
      else{
        this.etat = Etat.VERROUILLE;
      }
    }
  }

  public final Clef creerClef(){
    if (this.demande == false){
      return this.clef;
    }
    else{
      return null;
    }
  }
  public Etat getEtat(){
    return this.etat;
  }
}
