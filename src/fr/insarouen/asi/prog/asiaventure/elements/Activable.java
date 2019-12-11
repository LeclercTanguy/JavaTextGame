package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
public interface Activable {
   boolean activableAvec(Objet obj);
   void activer() throws ActivationException;
   void activerAvec(Objet obj) throws ActivationException;
}
