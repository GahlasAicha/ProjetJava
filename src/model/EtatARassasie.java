package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EtatARassasie extends EtatAnimal {
    private static EtatARassasie instance; // Singleton pour l'état rassasié
    private static final int TOURS_ECUREUIL = 5;   // Tours avant que l'écureuil redevienne affamé
    private static final int TOURS_SINGE = 3;      // Tours avant que le singe redevienne affamé

    private EtatARassasie() {}

    public static EtatARassasie getInstance() {
        if (instance == null) {
            instance = new EtatARassasie();
        }
        return instance;
    }

    private void gererDeplacement(Animal animal, Carte carte ,List<int[]> casesAdjacentes, Random rand, int toursAvantAffame) {
        // Incrémente le compteur de tours pour cet animal
        animal.incrementerCompteurToursRassasie();

        // Vérifie si l'animal doit redevenir affamé
        if (animal.getCompteurToursRassasie() >= toursAvantAffame) {
            animal.resetCompteurToursRassasie();
            animal.setEtat(EtatAffame.getInstance());


        }

        // Déplacement dans une case vide si possible
        List<Case> casesVides = new ArrayList<>();
        for (int[] coord : casesAdjacentes) {
            Case c = carte.getCase(coord[0], coord[1]); // Accéder à la case en utilisant les coordonnées
            if (c.getAnimal() == null) {
                casesVides.add(c);
            }
        }

        if (!casesVides.isEmpty()) {
            Case caseChoisie = casesVides.get(rand.nextInt(casesVides.size()));
            int x = animal.getX();
            int y = animal.getY();
            animal.setX(caseChoisie.getX());
            animal.setY(caseChoisie.getY());
            caseChoisie.setContenu(animal.getSymbole());
            //caseChoisie.setAnimal(animal);// Associer l'animal à la nouvelle case
            carte.getCase(x,y).viderCase();
            System.out.println(animal.getNom() + " se déplace à la position (" + x + ", " + y + ").Rass.");

        }
        animal.incrementerCompteurToursRassasie();
    }

    @Override
    public void seNourrir() {
        System.out.println("L'animal est déjà rassasié, il n'a pas besoin de manger.");
    }

    @Override
    public void apprivoiser() {
        System.out.println("L'animal rassasié est plus facile à apprivoiser.");

    }

    @Override
    public void recevoirCoup() {
        System.out.println("L'animal rassasié devient affamé après avoir reçu un coup.");
        animal.setEtat(EtatAffame.getInstance());
    }

    @Override
    public void agir(Carte carte) {
        // Récupérer les cases adjacentes sous forme de coordonnées
        List<int[]> casesAdjacentes = Carte.obtenirCasesAdjacentes(animal.getX(), animal.getY(),carte.getHauteur(),carte.getLargeur());
        Random rand = new Random();

        // Si l'animal est un écureuil et est rassasié, il se déplace vers une case vide
        int toursAvantAffame = (animal instanceof Ecureuil) ? TOURS_ECUREUIL : TOURS_SINGE;
        gererDeplacement(animal, carte, casesAdjacentes, rand, toursAvantAffame);

        // Si l'animal dépasse le nombre de tours avant de redevenir affamé, il redevient affamé
        if (animal.getCompteurToursRassasie() >= toursAvantAffame) {
            System.out.println(animal.getNom()+ "est affame !");
            animal.setEtat(EtatAffame.getInstance());
            animal.resetCompteurToursRassasie();
        }

       /* System.out.println(animal.getNom() + " se deplace");
        int[] nouvellePosition = choisirCaseAleatoire(casesAdjacentes);
        if (Carte.estCaseValide(nouvellePosition[0], nouvellePosition[1], carte.getHauteur(), carte.getLargeur()) &&
                carte.getContenuCase(nouvellePosition[0], nouvellePosition[1]) == ' ') {
            gererDeplacement(animal, carte, casesAdjacentes,rand, toursAvantAffame);
        } else {
            System.out.println(animal.getNom() + " ne peut pas se déplacer : la case choisie est invalide ou occupée.");
        }*/

    }

    // Méthode pour gérer l'intelligence artificielle d'un écureuil en forêt
    private void agirEcureuil(Ecureuil ecureuil, Carte carte, List<int[]> casesAdjacentes, Random rand) {
        // L'écureuil ne mange pas quand il est rassasié, il se déplace vers une case vide
        gererDeplacement(ecureuil, carte, casesAdjacentes, rand, TOURS_ECUREUIL);
    }

    // Méthode pour gérer l'intelligence artificielle d'un singe en jungle
    private void agirSinge(Singe singe, Carte carte, List<int[]> casesAdjacentes, Random rand) {
        // Le singe ne mange pas quand il est rassasié, il se déplace vers une case vide
        gererDeplacement(singe, carte, casesAdjacentes, rand, TOURS_SINGE);
    }

    // Méthode pour gérer les actions spécifiques aux animaux qui mangent
    public void gererNourriture(Animal animal, Carte carte, List<int[]> casesAdjacentes, String typeNourriture) {
        for (int[] coord : casesAdjacentes) {
            Case c = carte.getCase(coord[0], coord[1]);  // Accéder à la case en utilisant les coordonnées
            if (c.getObjet() != null && c.getObjet().getNom().equals(typeNourriture)) {
                System.out.println(animal.getNom() + " mange un " + typeNourriture + ".");
                c.setObjet(null);  // L'animal mange l'objet
                animal.setEtat(EtatARassasie.getInstance());  // L'animal devient rassasié
                animal.resetCompteurToursRassasie();
                return;
            }
        }
    }

    private int[] choisirCaseAleatoire(List<int[]> casesAdjacentes) {
        if (casesAdjacentes.isEmpty()) {
            System.out.println(animal.getNom() + " ne peut pas se déplacer : aucune case adjacente valide.");
            return new int[] {animal.getX(), animal.getY()}; // Rester sur place
        }

        Random random = new Random();
        return casesAdjacentes.get(random.nextInt(casesAdjacentes.size()));
    }
}

