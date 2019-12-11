package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;


public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFin {

	private ConditionDeFinConjonctionConditionDeFin c;
	
	public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu edj, Vivant v, Piece p, Objet... tab) {
		super(edj);
        c = new ConditionDeFinConjonctionConditionDeFin(edj,new ConditionDeFinVivantDansPiece(edj, v, p),new ConditionDeFinVivantPossedeObjets(edj, v, tab));
	}

	public EtatDuJeu verifierCondition() {
		return c.verifierCondition();
	}
	
}
