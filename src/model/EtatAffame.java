package model;

import java.util.List;
import java.util.Random;

/// desin pattern singleton et strategie pour chaque etat
public class EtatAffame extends EtatAnimal {
     private static EtatAffame instance; // singleton


     // Constructeur privé pour empêcher l'instanciation directe
     private  EtatAffame() {}

     // Méthode pour obtenir l'instance unique
     public static EtatAffame getInstance() {
         if (instance == null) {
             instance = new EtatAffame();
         }
         return instance;
     }

     @Override
     public void seNourrir() {
         System.out.println(animal.getNom() + "se nourrit et devient rassasié ");
         animal.setEtat(EtatARassasie.getInstance());
     }

     @Override
     public void apprivoiser() {
         System.out.println(animal.getNom() + " ne peut pas être apprivoisé en étant affamé.");
     }

     @Override
     public void recevoirCoup() {
         System.out.println(animal.getNom() + " est effrayé mais reste affamé.");
     }


     @Override
     public void agir() {
         System.out.println(animal.getNom() + " cherche de la nourriture.");

         // Liste des nourritures à chercher dans l'ordre de priorité
         char[] nourritures = {'G', 'C', 'B'}; // Gland, Champignon, Banane

         // Rechercher de la nourriture autour de l'écureuil
         List<int[]> casesAdjacentes = obtenirCasesAdjacentes(animal.getX(), animal.getY());
         for (char nourriture : nourritures) {
             for (int[] caseAdj : casesAdjacentes) {
                 if (getContenuCase(caseAdj[0], caseAdj[1]) == nourriture) {
                     System.out.println(animal.getNom() + " mange de la nourriture et devient rassasié.");
                     deplacerAnimal(animal, caseAdj[0], caseAdj[1]);
                     animal.setEtat(EtatARassasie.getInstance(animal));
                     return;
                 }
             }
         }

         // Si aucune nourriture n'est trouvée, se déplacer aléatoirement
         System.out.println(animal.getNom() + " ne trouve rien et se déplace aléatoirement.");
         int[] nouvellePosition = choisirCaseAleatoire(casesAdjacentes);
         deplacerAnimal(animal, nouvellePosition[0], nouvellePosition[1]);
     }

    private int[] choisirCaseAleatoire(List<int[]> casesAdjacentes) {
           Random random = new Random();
           return casesAdjacentes.get(random.nextInt(casesAdjacentes.size()));
     }

    private void deplacerAnimal(Animal animal, int i, int i1) {
        animal.setX(i); // Met à jour la position x de l'animal
        animal.setY(i1); // Met à jour la position y de l'animal
        System.out.println(animal.getNom() + " se déplace à la position (" + i + ", " + i1 + ").");
    }



}
