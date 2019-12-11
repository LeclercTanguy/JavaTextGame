package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;

public class Coffre extends Objet implements Activable {
    private Etat etat;
    public Coffre (String nom, Monde m) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, m);
    this.etat = Etat.valueOf("FERME");
	}
	public boolean estDeplacable() {
		return false;
	}
    public void activer()throws ActivationException{
        if (this.etat.equals(Etat.valueOf("FERME"))){
            this.etat = Etat.valueOf("OUVERT");
        }else {
          if (this.etat.equals(Etat.valueOf("OUVERT"))){
              this.etat = Etat.valueOf("FERME");
        }else {
            throw new ActivationException(this.getNom()+" ne peut pas être activé avec un objet");
        }
        }
    }
    @Override
    public void activerAvec(Objet obj)throws ActivationException{
        throw new ActivationException(this.getNom() + " ne peut pas être activé avec un objet");
    }
    public boolean activableAvec(Objet obj){
      return false;
    }
    public Etat getEtat(){
      return this.etat;
    }

    public String toString(){
      return String.format(" Le nom du coffre est : %s, son monde est : %s, et son état est : %s ",this.getNom(),this.getMonde(),this.getEtat());
    }
}
