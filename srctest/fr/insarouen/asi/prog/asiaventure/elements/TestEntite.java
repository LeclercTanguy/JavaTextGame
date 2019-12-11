package fr.insarouen.asi.prog.asiaventure.elements ;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.AllOf.allOf;
import org.hamcrest.core.IsEqual;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;

public class TestEntite{
  public Monde monde;
  public Entite e1 , e2;

    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
        monde = new Monde("monde");
        e1= new Entite("e1",monde){};
        e2=new Entite("e2",monde){};
    }
    @Test
    public void test_constructeur(){
        assertThat(e1.getNom(),IsEqual.equalTo("e1"));
        assertThat(e1.getMonde(),IsEqual.equalTo(monde));
    }
    @Test
    public void testGetMonde() {
	    assertThat(e1.getMonde(),IsEqual.equalTo(monde));
	}
	
    @Test
	public void testGetNom() {
		assertThat(e1.getNom(),IsEqual.equalTo("e1"));
	}
	
	@Test
	public void testEquals() {
		assertFalse(e1.equals(e2));
	}

}
