package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.core.IsEqual;
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
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;

public class TestVivant{

	public Monde monde;
	public Piece piece;
	public Vivant vivant1, vivant2;
	public Objet objet1, objet2;


	@Before
	public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException {
	    monde = new Monde("MONDE");
	    piece = new Piece("PIECE", monde);
	    objet1 = new Objet("Objet1", monde) {
			public boolean estDeplacable() {
				return true;
			}
		};
		objet2 = new Objet("Objet2", monde) {
			public boolean estDeplacable() {
				return false;
			}
		};
  //   vivant1 = new Vivant("Vivant1",monde,100,15,piece,objet1){};
  //   vivant2 = new Vivant("Vivant2",monde,50,11,piece,objet1,objet2){};
	//
	}
	//
	// @Test
	// public void testConstructeur() throws NomDEntiteDejaUtiliseDansLeMondeException {
	//   assertThat(vivant1.getNom(), IsEqual.equalTo("Vivant1"));
	// 	assertThat(vivant1.getMonde(), IsEqual.equalTo(monde));
	// 	assertThat(vivant1.getPiece(), IsEqual.equalTo(piece));
	// 	assertThat(vivant1.getPointVie(), IsEqual.equalTo(100));
	// 	assertThat(vivant1.getPointForce(), IsEqual.equalTo(15));
	// 	assertThat(vivant1.possede(objet1), IsEqual.equalTo(true));
	// 	assertThat(vivant1.possede(objet2), IsEqual.equalTo(false));
	// }
	// @Test
  //   public void testPossede() {
  //       assertThat(vivant1.possede(objet2), is(false));
  //       assertThat(vivant2.possede(objet1), is(true));
	//
  //  }
	//
	//

  //public static void main(String[] args){
    //try{
      //Monde monde = new Monde("monde");
      //Piece piece = new Piece("piece",monde);
      //Objet[] objets = new Objet[0];
      //Vivant v1 = new Vivant("Tao",monde,100,7,piece,objets){};
      //System.out.println(v1);
      //PiedDeBiche pb = new PiedDeBiche("pb",monde);
      //PiedDeBiche pb2 = new PiedDeBiche("pb2",monde);
      //piece.deposer(pb);
      //System.out.println(piece.contientObjet(pb));
    //} catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
      //System.out.println("Le test ne passe pas");
    //}
    //v1.prendre(pb);
    //System.out.println(v1.possede(pb));
    //System.out.println(!(v1.possede(pb2)));
  //}
}
