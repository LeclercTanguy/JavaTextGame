package fr.insarouen.asi.prog.asiaventure;

import java.lang.Throwable;
import java.util.Scanner;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

public class Main{
  private static Simulateur sim = null;

 public static void main(String[] args) throws Throwable{
   try{
     boolean continuer = true;
     while(continuer){
       System.out.println("--Menu--");
       System.out.println("1/ jouer");
       System.out.println("2/ charger un fichier de description");
       System.out.println("3/ sauver la partie actuelle");
       System.out.println("4/ charger une partie");
       System.out.println("5/ quitter");
       String chemin;
       Scanner sc = new Scanner(System.in);
       int choix =0;
       try {
         choix=sc.nextInt();
       } catch (InputMismatchException e) {System.out.println("Veuillez saisir un chiffre entre 1 et 5");}
       switch(choix){
         case 1 :
            jouer(sim);
            break;
         case 2:
            chemin = CheminDuFichier(sc);
            sim = chargerFichier(chemin);
            break;
         case 3:
          if (sim==null){
           }
           else {
               chemin = CheminDuFichier(sc);
               sauvegarder(sim, chemin);
           }
           break;
         case 4:
           chemin = CheminDuFichier(sc);
           sim = chargerPartie(chemin);
           break;
         case 5:
           continuer=false;
           break;
         }
       }
     } catch (IOException e){System.out.println("Il y a eu une erreur lors du chargement du fichier"); e.printStackTrace();}
   }
   private static void jouer(Simulateur sim) throws Throwable{
       Scanner sc = new Scanner(System.in);
       EtatDuJeu etat = EtatDuJeu.ENCOURS;
       do {
           try {
               etat = sim.executerUnTour();
               if (etat == EtatDuJeu.ENCOURS){
                   System.out.println("Rejouez ? (y/n)");
               }
            } catch(ASIAventureException e){
                System.out.println(e.getMessage());
                System.out.println("Rejouez ? (y/n)");
            }
        } while((sc.next().equals("y")) && (etat == EtatDuJeu.ENCOURS));
       if(etat == EtatDuJeu.SUCCES){
           System.out.println("Vous avez gagné");
       }
       else{
           System.out.println("Perdu");
       }
    }



   private static Simulateur chargerFichier(String chemin) throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {
     Reader reader = new FileReader(new File(chemin));
     Simulateur simulateur = new Simulateur(reader);
     reader.close();
     return simulateur;
   }
   private static Simulateur chargerPartie(String chemin) throws IOException, ClassNotFoundException{
     ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chemin));
     Simulateur simulateur = new Simulateur(ois);
     ois.close();
     return simulateur;
   }
   private static void sauvegarder(Simulateur simulateur, String chemin) throws IOException{
     ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(chemin));
     simulateur.enregistrer(oos);
     oos.close();
   }
   private static String CheminDuFichier(Scanner scr){
     System.out.println("Veuillez préciser le chemin du fichier ou vous voulez effectuer cette action ?");
     return scr.next();
   }
}
