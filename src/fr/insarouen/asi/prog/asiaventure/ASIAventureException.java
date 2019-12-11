package fr.insarouen.asi.prog.asiaventure;

public class ASIAventureException extends Exception{
  public ASIAventureException(String msg){
    super(msg);
  }

  public ASIAventureException(Throwable t){
      super(t);
  }
}
