package model;

public class Renard extends Predateur {
     private Carte carte;
    private Ecureuil ecureuil;

    public Renard(int x, int y,Carte carte ) {
        super("Renard", x, y,carte);

    }

    @Override
    public void deplacer() {
        // Liste des directions possibles
        int[] directionsX = {1, -1, 0, 0};
        int[] directionsY = {0, 0, 1, -1};

        // Mélange aléatoire des directions pour éviter les biais
        for (int i = 0; i < 4; i++) {
            // Choisir une direction aléatoire
            int direction = (int) (Math.random() * 4);
            int newX = x + directionsX[direction];
            int newY = y + directionsY[direction];

            // Vérifier si la case est vide avant de déplacer le renard
            if (carte.estCaseVide(newX, newY)) {
                x = newX;
                y = newY;
                System.out.println(nom + " se déplace à la position (" + x + ", " + y + ")");
                return; // Le renard se déplace et la méthode termine
            }
        }

        // Si aucune case vide n'est trouvée, le renard ne se déplace pas
        System.out.println(nom + " ne peut pas se déplacer (pas de case vide adjacente).");
    }


    @Override
    public void attaquer(Animal animal) {
        // Vérifier si l'animal est un écureuil
        if (animal instanceof Ecureuil) {
            Ecureuil ecureuil = (Ecureuil) animal;
            if (ecureuil.estEffraye()) {
                System.out.println(nom + " ne peut pas attaquer, " + ecureuil.getNom() + " est déjà effrayé.");
                return; // L'écureuil est effrayé, l'attaque échoue
            }
            // on doit verifier si le renard est proche d'un ecureuil
            if (Math.abs(getX() - ecureuil.getX()) <= 1 && Math.abs(getY() - ecureuil.getY()) <= 1) {

                // Parcourir les cases adjacentes pour vérifier la présence d'un arbre
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        // Vérifier si une case adjacente contient un arbre
                        if (carte.estCaseAvecArbre(ecureuil.getX() + i, ecureuil.getY() + j)) {
                            // Faire fuir l'écureuil dans l'arbre
                            ecureuil.fuirDansArbre(carte);
                            System.out.println(nom + " fait fuir " + ecureuil.getNom() + " dans un arbre.");
                            return; // Terminer l'attaque car l'écureuil a fui
                        }
                    }
                }
            }
            // Si aucune case avec un arbre n'est trouvée, tuer l'écureuil
            System.out.println(nom + " attaque et tue " + ecureuil.getNom() + ".");
            carte.supprimerAnimal(ecureuil); // Suppression de l'écureuil de la carte
        } else {
            System.out.println(nom + " ne peut pas attaquer que des écureuils.");
            System.out.println("L'attaque du renard a échoué, l'écureuil s'échappe !");
            ecureuil.devenirEffraye();
        }
    }

    // Méthode pour obtenir la position X du renard
    public int getX() {
        return x;
    }

    // Méthode pour obtenir la position Y du renard
    public int getY() {
        return y;
    }

    // Méthode pour afficher la position du renard
    public void afficherPosition() {
        System.out.println(nom + " est à la position (" + x + ", " + y + ")");
    }
}

