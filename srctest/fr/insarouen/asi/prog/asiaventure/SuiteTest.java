package fr.insarouen.asi.prog.asiaventure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import fr.insarouen.asi.prog.asiaventure.elements.SuiteTestEntite;
import fr.insarouen.asi.prog.asiaventure.elements.objets.SuiteTestObjets;
import fr.insarouen.asi.prog.asiaventure.elements.structure.SuiteTestStructures;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.SuiteTestVivant;

@RunWith(Suite.class)
@SuiteClasses({
    
SuiteTestObjets.class,
SuiteTestStructures.class,
SuiteTestVivant.class,
SuiteTestEntite.class,
SuiteTestMonde.class

    
})
 public class SuiteTest {}
