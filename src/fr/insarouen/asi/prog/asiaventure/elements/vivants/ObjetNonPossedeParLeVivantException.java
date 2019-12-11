package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.VivantException;
import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

public class ObjetNonPossedeParLeVivantException extends ASIAventureException{
  public ObjetNonPossedeParLeVivantException(String msg){
    super(msg);
  }
}
