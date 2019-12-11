package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException ;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import org.hamcrest.core.IsEqual;
import static org.hamcrest.core.Is.is;

public class TestObjet {
    
  public Objet objet;
  @Before
  public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
    Monde monde = new Monde("WORLD");
      objet = new Objet("unObjet",monde){
        public boolean estDeplacable(){
          return true;
        }
      };
  }
  @Test
  public void testConstructeur(){
    assertThat(objet.getNom(),IsEqual.equalTo("unObjet"));
    assertThat(objet.getMonde().getNom(),IsEqual.equalTo("WORLD"));
  }
  @Test
  public void testEstDeplacable(){
    assertTrue(objet.estDeplacable());
  }
}
