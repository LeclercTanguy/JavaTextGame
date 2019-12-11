package fr.insarouen.asi.prog.asiaventure;


import java.io.Serializable;

public abstract class ConditionDeFin implements Serializable {
 private EtatDuJeu etatDuJeu;
  public ConditionDeFin(EtatDuJeu etatDuJeu){
     this.etatDuJeu=etatDuJeu;
  }

  public EtatDuJeu getEtatConditionVerifiee(){
    return etatDuJeu;
  }

  public abstract EtatDuJeu verifierCondition();
  
  public String toString() {
		return this.getClass().toString();
	}


}
