package model;

public class Ecureuil extends Animal {
    // Constructeur avec position
    public Ecureuil(int positionX, int positionY) {
        super("Écureuil", 'E', positionX, positionY);
        setEtat(EtatAffame.getInstance(this)); // Par défaut, un écureuil commence affamé
    }


    public String afficherEcureuil() {
        return "Nom: " + getNom() + ", Position: (" + getX() + ", " + getY() + ")";
    }

    @Override
    public void seNourrir() {
        // Appel de la méthode de l'état actuel (affamé ou autre)
        etat.seNourrir();
    }

    @Override
    public void apprivoiser() {
        // Appel de la méthode de l'état actuel
        etat.apprivoiser();
    }

    @Override
    public void recevoirCoup() {
        // Appel de la méthode de l'état actuel
        etat.recevoirCoup();
    }

    @Override
    public void agir() {
        // L'écureuil agit selon son état actuel (affamé, rassasié, etc.)
        etat.agir();
    }

}