package model;

public abstract class FabriqueElements {


    public abstract Animal creerAnimal(char symbole, int x, int y);

    public abstract Predateur creerPredateur(char symbole, int x, int y, Carte carte);

    public abstract Objet creerObjet(char symbole, int x, int y);
    public abstract char[] getSymbolesAutorises();
}