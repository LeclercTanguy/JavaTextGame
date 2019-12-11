package fr.insarouen.asi.prog.asiaventure.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.SuiteTestVivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.SuiteTestStructure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.SuiteTestObjets;

@RunWith(Suite.class)
@SuiteClasses({
  SuiteTestVivant.class
  SuiteTestStructure.class
  TestEntite.class


})

public class SuiteTestElements{

}
