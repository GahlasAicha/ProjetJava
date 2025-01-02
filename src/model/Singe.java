package model;

public class Singe extends Animal {
    public Singe(int positionX, int positionY) {
        super("Singe", 'S', positionX, positionY);
        setEtat(EtatAffame.getInstance()); // Par défaut, un singe commence affamé
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

}