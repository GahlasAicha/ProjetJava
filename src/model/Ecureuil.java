package model;

public class Ecureuil extends Animal {



    public Ecureuil(int positionX, int positionY) {
        super("Écureuil", 'E', positionX, positionY);
        setEtat(EtatAffame.getInstance()); // Par défaut, un écureuil commence affamé
    }



    @Override
    public void afficher() {

        System.out.println("L'écureuil " + getNom() + " est à la position (" + getX() + ", " + getY() + ")");
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
    public void agir(Carte carte) {
        // L'écureuil agit selon son état actuel (affamé, rassasié, etc.)
        etat.agir(carte);
    }

}