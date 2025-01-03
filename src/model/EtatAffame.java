package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EtatAffame extends EtatAnimal {
    private static EtatAffame instance;

    // Constructeur privé pour empêcher l'instanciation directe
    public EtatAffame() {}

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
        System.out.println(animal.getNom() + " a été frappé.");
        animal.setAmi(false);  // Perdre l'amitié si frappé par le personnage
        System.out.println(animal.getNom() + " perd son amitié avec le personnage.");
    }

    @Override
    public void agir(Carte carte) {
        if (animal == null || carte == null) {
            throw new IllegalStateException("L'animal ou la carte est null.");
        }
        System.out.println("Nombre d'animaux avant l'action : " + carte.compterAnimaux());
        System.out.println(animal.getNom() + " cherche de la nourriture.");

        char[] nourritures = {'G', 'C'};  // Définir les nourritures possibles
        List<int[]> casesAdjacentes = Carte.obtenirCasesAdjacentes(animal.getX(), animal.getY(), carte.getHauteur(), carte.getLargeur());

        // Définir les nourritures spécifiques pour les animaux
        if (animal instanceof Ecureuil) {
            nourritures = new char[] {'G', 'C'}; // Nourritures pour écureuil
        } else if (animal instanceof Singe) {
            nourritures = new char[] {'b', 'C'}; // Nourritures pour singe
        }

        // Chercher la nourriture parmi les cases adjacentes
        for (char nourriture : nourritures) {
            for (int[] caseAdj : casesAdjacentes) {
                if (Carte.estCaseValide(caseAdj[0], caseAdj[1], carte.getHauteur(), carte.getLargeur()) &&
                        carte.getContenuCase(caseAdj[0], caseAdj[1]) == nourriture) {
                    consommerNourriture(nourriture, caseAdj, carte);
                    System.out.println("Nombre d'animaux après consommation : " + carte.compterAnimaux());
                    return; // Action terminée
                }
            }
        }

        // Si aucun aliment n'est trouvé, l'animal se déplace aléatoirement
        System.out.println(animal.getNom() + " ne trouve rien et tente de se déplacer aléatoirement.");
        int[] nouvellePosition = choisirCaseAleatoire(casesAdjacentes,carte);
        if (Carte.estCaseValide(nouvellePosition[0], nouvellePosition[1], carte.getHauteur(), carte.getLargeur()) &&
                carte.getContenuCase(nouvellePosition[0], nouvellePosition[1]) == ' ') {
            deplacerAnimal(animal, nouvellePosition[0], nouvellePosition[1], carte);
        } else {
            System.out.println(animal.getNom() + " ne peut pas se déplacer : la case choisie est invalide ou occupée.");
        }

        // Afficher le nombre exact d'animaux après l'action
        System.out.println("Nombre d'animaux dans la carte après l'action : " + carte.compterAnimaux());
    }

    private void consommerNourriture(char nourriture, int[] position, Carte carte) {
        switch (nourriture) {
            case 'G':
                System.out.println(animal.getNom() + " mange un gland.");
                break;
            case 'C':
                System.out.println(animal.getNom() + " mange un champignon.");
                break;
            case 'b':
                System.out.println(animal.getNom() + " mange une banane.");
                break;
        }

        carte.setCaseContenu(position[0], position[1], ' '); // Consomme la nourriture
        deplacerAnimal(animal, position[0], position[1], carte); // Déplace l'animal
        animal.setEtat(EtatARassasie.getInstance()); // Change d'état
    }


    // Méthode pour choisir une case aléatoire parmi les cases adjacentes
    private int[] choisirCaseAleatoire(List<int[]> casesAdjacentes, Carte carte) {
        List<int[]> casesValides = new ArrayList<>();

        // Vérifier chaque case adjacente
        for (int[] caseAdj : casesAdjacentes) {
            if (Carte.estCaseValide(caseAdj[0], caseAdj[1], carte.getHauteur(), carte.getLargeur())) {
                casesValides.add(caseAdj);
            }
        }

        // Si aucune case valide, rester sur place
        if (casesValides.isEmpty()) {
            System.out.println(animal.getNom() + " ne peut pas se déplacer : aucune case adjacente valide.");
            return new int[] {animal.getX(), animal.getY()}; // Rester sur place
        }

        // Choisir une case valide aléatoire
        Random random = new Random();
        return casesValides.get(random.nextInt(casesValides.size()));
    }





    private void deplacerAnimal(Animal animal, int x, int y, Carte carte) {
        // Vérifie si les nouvelles coordonnées sont valides et à l'intérieur des limites de la carte
        if (!Carte.estCaseValide(x, y, carte.getHauteur(), carte.getLargeur())) {
            System.out.println(animal.getNom() + " ne peut pas se déplacer hors des limites.");
            return; // L'animal ne bouge pas si la case n'est pas valide, il reste sur place
        }

        // Si le déplacement est valide, libère la case de l'ancienne position
        carte.setCaseContenu(animal.getX(), animal.getY(), ' ');

        // Met à jour les coordonnées de l'animal
        animal.setX(x);
        animal.setY(y);

        // Place l'animal dans la nouvelle case
        carte.setCaseContenu(x, y, animal.getSymbole());
        System.out.println(animal.getNom() + " se déplace à la position (" + x + ", " + y + ").");
    }



}


