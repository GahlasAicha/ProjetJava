package model;

public abstract class EtatAnimal  {
    protected Animal animal;

    public  void setAnimal(Animal animal) {
        this.animal = animal;
    }

    // Méthodes abstraites pour définir les comportements
    public abstract void seNourrir();

    public abstract void apprivoiser();

    public abstract void recevoirCoup();

    public abstract void agir(Carte carte); // l'algorithme principale de comportement ,depend de l'etat



}
