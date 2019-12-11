package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.core.IsEqual;

public class TestSerrure {
  public Monde monde;
  public Serrure serrure;

  @Before
  public void avantTest(){
    monde = new Monde("monde");
    serrure= new Serrure("nomSerrure",monde);
  }

  @Test
  public void test_constructeur(){
    assertThat(serrure.getNom(),IsEqual.equalTo("nomSerrure"));
    assertThat(serrure.getMonde(),IsEqual.equalTo(monde));
  }
  @Test(expected=ActivationImpossibleException.class)
  public void test_activer() {
    serrure.activer();
  }

}
