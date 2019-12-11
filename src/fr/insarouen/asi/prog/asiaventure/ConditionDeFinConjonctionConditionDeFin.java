package fr.insarouen.asi.prog.asiaventure;

public class ConditionDeFinConjonctionConditionDeFin extends ConditionDeFin {
  private ConditionDeFin[] cfs;

  public ConditionDeFinConjonctionConditionDeFin(EtatDuJeu etatDuJeu, ConditionDeFin... cfs) {
    super(etatDuJeu);
    this.cfs = cfs;
  }

  public  EtatDuJeu	verifierCondition() {
      for( ConditionDeFin condition : this.cfs ) {
        if(condition.verifierCondition() == EtatDuJeu.ENCOURS)
          return EtatDuJeu.ENCOURS;
      }
      return this.getEtatConditionVerifiee();
    }

}
