package model;

import java.util.List;
import java.util.Random;



/// desin pattern singleton pour chaque  chaque etat
public class EtatAffame extends EtatAnimal {
     private static EtatAffame instance; // singleton




     // Constructeur privé pour empêcher l'instanciation directe
     private  EtatAffame( ) {

     }

     // Méthode pour obtenir l'instance unique
     public static EtatAffame getInstance() {
         if (instance == null) {
             instance = new EtatAffame();
         }
         return instance;
     }

     @Override
     public void seNourrir() {
         if (animal == null) {
             throw new IllegalStateException("L'animal associé à l'état est null.");
         }
         System.out.println(animal.getNom() + " se nourrit et devient rassasié.");
         animal.setEtat(EtatARassasie.getInstance()); // Passe à l'état rassasié
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
     public void agir(Carte carte) {
         System.out.println(animal.getNom() + " cherche de la nourriture.");

         // Liste des nourritures à chercher : Gland, Champignon, Banane
         char[] nourritures = {'G', 'C', 'B'}; // Gland, Champignon, Banane

         // Recherche de la nourriture autour de l'animal
         List<int[]> casesAdjacentes = Carte.obtenirCasesAdjacentes(animal.getX(), animal.getY());
         for (char nourriture : nourritures) {
             for (int[] caseAdj : casesAdjacentes) {
                 if (carte.getContenuCase(caseAdj[0], caseAdj[1]) == nourriture) {
                     System.out.println(animal.getNom() + " mange de la nourriture et devient rassasié.");
                     deplacerAnimal(animal, caseAdj[0], caseAdj[1]);
                     animal.setEtat(EtatARassasie.getInstance()); // Passer à l'état rassasié
                     return;
                 }
             }
         }

         // Si aucune nourriture n'est trouvée, se déplacer aléatoirement
         System.out.println(animal.getNom() + " ne trouve rien et se déplace aléatoirement.");
         int[] nouvellePosition = choisirCaseAleatoire(casesAdjacentes);
         deplacerAnimal(animal, nouvellePosition[0], nouvellePosition[1]);
     }

    // Méthode pour choisir une case aléatoire parmi les cases adjacentes
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
