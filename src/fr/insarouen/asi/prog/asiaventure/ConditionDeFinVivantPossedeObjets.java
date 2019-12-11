package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;


public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin {
  private Vivant vivant;
  private Objet[] objets;

  public ConditionDeFinVivantPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Objet[] objets) {
    super(etatConditionVerifiee);
    this.vivant = vivant;
    this.objets = objets;
  }

  public EtatDuJeu verifierCondition() {
    for( Objet objet : this.objets ) {
      if(!this.vivant.possede(objet)) {
        return EtatDuJeu.ENCOURS;
      }
    }
    return this.getEtatConditionVerifiee();
  }

}
