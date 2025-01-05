package model;

public class Ecureuil extends Animal {

    private boolean estEffraye = false;
    private int toursEffrayeRestants = 0;

    public Ecureuil(int positionX, int positionY) {
        super("Écureuil", 'E', positionX, positionY);
        setEtat(EtatAffame.getInstance()); // Par défaut, un écureuil commence affamé
        this.estEffraye=false;
        this.toursEffrayeRestants=0;
    }



    @Override
    public void afficher() {

        System.out.println("L'écureuil " + getNom() + " est à la position (" + getX() + ", " + getY() + ")");
    }
    @Override
    public void seNourrir() {
        if (estEffraye) {
            System.out.println("L'écureuil est trop effrayé pour se nourrir.");
        } else {
            super.seNourrir();
        }
    }

    @Override
    public void apprivoiser() {
        if (estEffraye) {
            System.out.println("Impossible d'apprivoiser un écureuil effrayé !");
        } else {
            super.apprivoiser();
        }
    }
    @Override
    public void recevoirCoup() {
        // Si un prédateur attaque sans tuer
        if (!this.estEffraye) {
            this.estEffraye = true;
            this.toursEffrayeRestants = 3;
            System.out.println(this.getNom() + " est effrayé et reste caché pendant 3 tours !");
        }
    }

    @Override
    public void agir(Carte carte) {
        if (estEffraye) {
            if (toursEffrayeRestants > 0) {
                System.out.println(this.getNom() + " reste caché dans son abri. (" + toursEffrayeRestants + " tours restants)");
                toursEffrayeRestants--;
            } else {
                estEffraye = false;
                System.out.println(this.getNom() + " n'est plus effrayé et reprend ses activités !");
            }
        } else {
            // Comportement normal (déplacement, recherche de nourriture, etc.)
            System.out.println(this.getNom() + " agit normalement.");
            super.agir(carte); // Appelle le comportement de base.
        }
    }


    public void fuirDansArbre(Carte carte) {
        // Liste des directions possibles autour de l'écureuil
        int[] directionsX = {1, -1, 0, 0};
        int[] directionsY = {0, 0, 1, -1};

        // Parcourir toutes les cases adjacentes
        for (int i = 0; i < directionsX.length; i++) {
            int newX = getX() + directionsX[i];
            int newY = getY() + directionsY[i];

            // Vérifier si la case contient un arbre
            if (carte.estCaseAvecArbre(newX, newY)) {
                setPosition(newX, newY);
                System.out.println(getNom() + " s'enfuit dans un arbre à la position (" + newX + ", " + newY + ").");
                return; // Fin de la méthode après avoir trouvé un arbre
            }
        }

        // Si aucun arbre n'est trouvé
        System.out.println(getNom() + " n'a pas trouvé d'arbre pour fuir.");
    }
    public void devenirEffraye() {
        System.out.println("L'écureuil devient effrayé et se cache !");
        this.estEffraye = true;
        this.toursEffrayeRestants = 3; // L'écureuil reste effrayé pendant 3 tours
    }

    private void decrementerEffraye() {
        if (estEffraye) {
            toursEffrayeRestants--;
            if (toursEffrayeRestants <= 0) {
                System.out.println("L'écureuil n'est plus effrayé.");
                estEffraye = false;
            }
        }
    }
    public boolean estEffraye() {
        return estEffraye;
    }
}
