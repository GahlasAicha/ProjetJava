package model;

import java.util.List;

public class EtatAApprivoise extends EtatAnimal {
    private static EtatAApprivoise instance;

    // Constructeur privé pour appliquer le Singleton
    public  EtatAApprivoise() {
    }

    // Méthode pour obtenir l'instance unique de l'état
    public static EtatAApprivoise getInstance() {
        if (instance == null) {
            instance = new EtatAApprivoise();
        }
        return instance;
    }

    // Méthode pour gérer l'action de nourrir l'animal (pas de besoin dans cet état)
    @Override
    public void seNourrir() {
        System.out.println("L'animal est apprivoisé et n'a pas besoin de nourriture.");
    }

    // Méthode pour tenter d'apprivoiser l'animal (l'animal est déjà apprivoisé)
    @Override
    public void apprivoiser() {
        System.out.println("L'animal est déjà apprivoisé.");
    }

    // Méthode pour gérer la réception d'un coup (perd l'amitié avec le personnage)
    @Override
    public void recevoirCoup() {
        System.out.println("L'animal a été frappé et perd son amitié avec le personnage.");
        // L'animal n'est plus ami avec le personnage, changement d'état ou action si nécessaire
        // Par exemple, l'animal pourrait devenir "indifférent" ou "hostile"
        animal.setAmi(false);
    }

    // Méthode pour gérer l'action de l'animal (interagir avec l'environnement ou le personnage)
    @Override
    public void agir(Carte carte) {
        System.out.println(animal.getNom() + " est apprivoisé et reste calme.");

        // Exemple d'interaction avec le personnage ou les objets autour
        // Par exemple, si l'animal est un écureuil ou un singe et rencontre le personnage
        if (animal.estProchePersonnage(carte)) {
            System.out.println(animal.getNom() + " est content et reste proche du personnage.");
            // Interaction ou événement supplémentaire possible, comme devenir ami, etc.
        } else {
            // L'animal peut rester immobile ou se déplacer calmement, cela dépend de votre logique
            System.out.println(animal.getNom() + " se déplace tranquillement dans son environnement.");
            // Exemple de déplacement aléatoire si nécessaire
            int[] nouvellePosition = choisirCaseAleatoire(carte);
            deplacerAnimal(animal, nouvellePosition[0], nouvellePosition[1]);
        }
    }

    // Méthode pour choisir une case adjacente aléatoire (simple exemple)
    private int[] choisirCaseAleatoire(Carte carte) {
        List<int[]> casesAdjacentes = Carte.obtenirCasesAdjacentes(animal.getX(), animal.getY(), carte.getHauteur(), carte.getLargeur());
        return casesAdjacentes.get((int) (Math.random() * casesAdjacentes.size()));
    }

    // Méthode pour déplacer l'animal dans la nouvelle position
    private void deplacerAnimal(Animal animal, int x, int y) {
        animal.setPosition(x, y);
        System.out.println(animal.getNom() + " se déplace en (" + x + ", " + y + ").");
    }

    public boolean estProchePersonnage(Carte carte) {
        int[] positionPersonnage = carte.getPositionPersonnage();
        int[] positionAnimal = new int[] {animal.getX(), animal.getY()}; // Position actuelle de l'animal

        // Comparer les positions pour déterminer si l'animal est proche du personnage
        int distance = Math.abs(positionAnimal[0] - positionPersonnage[0]) +
                Math.abs(positionAnimal[1] - positionPersonnage[1]);

        // Si la distance est inférieure ou égale à 1 (proche), retourner true
        return distance <= 1;
    }

}


    /*private void gererInteractionAvecPersonnage(Animal animal) {
        // Gérer l'amitié avec le personnage si l'animal s'est nourri à proximité
        if (animal instanceof Ecureuil && animal.getCompteurNourriture() >= 2) {
            System.out.println("L'écureuil devient ami avec le personnage.");
            animal.setAmi(true);
        } else if (animal instanceof Singe && animal.getCompteurNourriture() >= 2) {
            System.out.println("Le singe devient ami avec le personnage.");
            animal.setAmi(true);
        }
    }*/


