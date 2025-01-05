package model;

public class Singe extends Animal {
    private boolean estEffraye = false;
    private int toursEffrayeRestants = 0;
    private Case caseSinge;
    public Singe(int positionX, int positionY) {
        super("Singe", 'S', positionX, positionY);
        setEtat(EtatAffame.getInstance()); // Par défaut, un singe commence affamé
        this.caseSinge = new Case(positionX, positionY,'S');
    }
    @Override
    public void apprivoiser() {
        if (estEffraye) {
            System.out.println("Impossible d'apprivoiser un écureuil effrayé !");
        } else {
            super.apprivoiser();
        }
    }


    public String afficherSinge() {
        return "Nom: " + getNom() + ", Position: (" + getX() + ", " + getY() + ")";
    }
    @Override
    public void afficher() {
        System.out.println(afficherSinge());
    }

    @Override
    public void agir(Carte carte) {
        etat.agir(carte);
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
    public void setCase(Case caseSinge) {
        this.caseSinge = caseSinge;
    }

    public Case getCase() {
        return this.caseSinge;
    }
}