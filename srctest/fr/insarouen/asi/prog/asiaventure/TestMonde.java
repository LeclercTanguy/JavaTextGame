package fr.insarouen.asi.prog.asiaventure;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.core.IsEqual;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;


public class TestMonde{
  public Monde monde;
  public Entite e1 , e2, e3;

  @Before
  public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
    monde = new Monde("monde");
    e1= new Entite("e1",monde){};
    e2=new Entite("e2",monde){};
  }

    @Test
    public void test_constructeur(){
      assertThat(monde.getNom(),IsEqual.equalTo("monde")) ;
    }
    @Test
    public void test_ajouter_cas_normal()  throws NomDEntiteDejaUtiliseDansLeMondeException,
                                                  EntiteDejaDansUnAutreMondeException {

      assertThat(monde.getEntite("e1"),IsEqual.equalTo(e1));

    }
    @Test(expected=NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void test_ajouter_cas_exception1()  throws NomDEntiteDejaUtiliseDansLeMondeException,
                                                EntiteDejaDansUnAutreMondeException  {
    e3 = new Entite("e1",monde){};
    }

}
