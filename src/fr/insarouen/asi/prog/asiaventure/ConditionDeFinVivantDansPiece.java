package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;


public class ConditionDeFinVivantDansPiece extends ConditionDeFin{
  private Vivant vivant;
  private Piece piece;

  public ConditionDeFinVivantDansPiece(EtatDuJeu EtatConditionVerifiee, Vivant vivant , Piece piece){
    super(EtatConditionVerifiee);
    this.vivant = vivant;
    this.piece = piece;
  }

  public EtatDuJeu verifierCondition(){
    if(this.vivant.getPiece().equals(this.piece)){
      return this.getEtatConditionVerifiee();
    }
    else{
      return EtatDuJeu.ENCOURS;
    }

  }
}
