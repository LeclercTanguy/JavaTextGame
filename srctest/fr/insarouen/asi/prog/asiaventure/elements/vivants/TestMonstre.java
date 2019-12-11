package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Coffre;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;

import static org.junit.Assert.*;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Ignore;
import java.awt.Color;
import static org.hamcrest.core.Is.is;


public class TestMonstre {
  public Piece piece1, piece2;
  public Monstre monstre;
  public Monde monde;
  public Objet objet1,objet2,objet3;


  @Before
	public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException {
    this.monde=new Monde("monde");
    this.piece1 = new Piece("piece",monde);
    this.piece2 = new Piece("piece",monde);
    this.objet1 = new PiedDeBiche("PiedDeBiche",monde);
    this.objet2 = new Coffre("Coffre",monde);
    this.objet3 = new Objet("Objet3", monde) {
			public boolean estDeplacable() {
				return true;
			}
		};
    this.monstre = new Monstre("monstre", monde, 12, 6, piece1, objet1, objet2);
  }
  @Test
	public void testConstructeur() throws NomDEntiteDejaUtiliseDansLeMondeException {
    assertThat(monstre.getNom(), IsEqual.equalTo("monstre"));
		assertThat(monstre.getMonde(), IsEqual.equalTo(monde));
		assertThat(monstre.getPiece(), IsEqual.equalTo(piece1));
    assertThat(monstre.getPointVie(), IsEqual.equalTo(10));
		assertThat(monstre.getPointForce(), IsEqual.equalTo(6));
		assertThat(monstre.possede(objet1), IsEqual.equalTo(true));
		assertThat(monstre.possede(objet2), IsEqual.equalTo(true));
    assertThat(monstre.possede(objet3), IsEqual.equalTo(false));
  }
  @Test
  public void testFranchirPorte() throws PorteFermeException, ActivationImpossibleException, ActivationException, PorteInexistanteDansLaPieceException, VivantAbsentDeLaPieceException {
    assertThat(this.monstre.getPiece().equals(piece1), is(true));
    this.monstre.franchirPorte();
    assertThat(this.monstre.getPiece().equals(piece1), is(false));
    Piece nouvellePiece = this.monstre.getPiece();
    this.monstre.franchirPorte();
    assertThat(this.monstre.getPiece().equals(nouvellePiece), is(false));
    Piece nouvellePiece2 = this.monstre.getPiece();
    this.monstre.franchirPorte();
    assertThat(this.monstre.getPiece().equals(nouvellePiece2), is(false));
  }
  @Test
 public void testToutPrendre() throws ObjetNonPossedeParLeVivantException, ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
   assertThat(this.monstre.possede(objet2), is(true));
   assertThat(this.monstre.possede(objet1), is(true));
   assertThat(this.monstre.possede(objet3), is(false));
   monstre.toutPrendre();
   assertThat(this.monstre.possede(objet1), is(false));
   assertThat(this.monstre.possede(objet2), is(false));

 }
 @Test
 public void testExecuter() throws ObjetNonPossedeParLeVivantException, ObjetAbsentDeLaPieceException, ObjetNonDeplacableException, PorteFermeException, ActivationImpossibleException, ActivationException, PorteInexistanteDansLaPieceException, VivantAbsentDeLaPieceException {
    assertThat(this.monstre.getPointVie()==12, is(true));
    monstre.executer();
    assertThat(this.monstre.getPointVie()==11, is(true));
  }


}


