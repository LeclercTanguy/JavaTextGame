package fr.insarouen.asi.prog.asiaventure.elements.structure;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.core.IsEqual;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;


public class TestPorte{
  public Monde monde;;
  public Piece pieceA, pieceB;
  public Porte porte;
  public Serrure serrure;


  @Before
  public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
    monde = new Monde("monde");
    pieceA = new Piece("pieceA",monde);
    pieceB = new Piece("pieceB",monde);
    serrure = new Serrure("serrure",monde);
    porte = new Porte("porte",monde ,serrure ,pieceA ,pieceB);

  }

  @Test
  public void test_constructeur(){
    assertThat(porte.getMonde(), IsEqual.equalTo(monde));
    assertThat(porte.getNom(), IsEqual.equalTo("porte"));
    assertThat(porte.getEtat(), IsEqual.equalTo(Etat.FERME));
    assertThat(porte.getPieceAutreCote(pieceA), IsEqual.equalTo(pieceB));
    assertThat(porte.getPieceAutreCote(pieceB), IsEqual.equalTo(pieceA));
    assertThat(porte.getSerrure(), IsEqual.equalTo(serrure));
  }

}
