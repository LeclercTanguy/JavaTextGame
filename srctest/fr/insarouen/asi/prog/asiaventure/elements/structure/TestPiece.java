package fr.insarouen.asi.prog.asiaventure.elements.structure;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.core.IsEqual;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;

public class TestPiece {
  public Monde monde;
  public Vivant v1,v2;
  public PiedDeBiche pdb1,pdb2;
  public Piece piece;

  @Before
  public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
    monde = new Monde("monde");
    piece = new Piece("piece",monde);
    pdb1 = new PiedDeBiche("pdb1",monde);
    pdb2 = new PiedDeBiche("pdb2",monde);
    // v1 = new Vivant("v1",monde,100,50,piece,pdb1){};
    // v2 = new Vivant("v2",monde,100,52,piece,pdb2){};
  }
  @Test
  public void test_contientObjet_obj(){
    assertThat(piece.contientObjet(pdb1), is(false));
    piece.deposer(pdb1);
    assertThat(piece.contientObjet(pdb1), is(true));
  }
  @Test
  public void test_contientObjet_str(){
    assertThat(piece.contientObjet("pdb1"), is(false));
    piece.deposer(pdb1);
    assertThat(piece.contientObjet("pdb1"), is(true));
  }
  @Test
  public void test_retirer_cas_normal() throws ObjetAbsentDeLaPieceException,
                                            ObjetNonDeplacableException{
    piece.deposer(pdb1);
    piece.retirer(pdb1);
    assertThat(piece.contientObjet(pdb1),is(false));
  }
  @Test(expected=ObjetAbsentDeLaPieceException.class)
  public void test_retirer_cas_exception1() throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException{
    piece.retirer(pdb1);
  }
//   @Test
//   public void test_contientVivant_entrer(){
//     piece.entrer(v1);
//     assertThat(piece.contientVivant(v1),is(true));
//   }
//   @Test
//   public void test_contientVivant_sortir(){
//     piece.entrer(v1);
//     piece.entrer(v2);
//     Vivant v3=piece.sortir(v1);
//     assertThat(piece.contientVivant(v1),is(false));
//   }
//
}

