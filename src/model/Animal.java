package model;

public  abstract class Animal {
    protected String nom;
    protected char symbole;
    protected int x, y;
    protected EtatAnimal etat;
    //   private boolean estAmi ;

    public Animal(String nom, char symbole, int x, int y) {

        this.nom = nom;
        this.symbole = symbole;
        this.x = x;
        this.y = y;
        this.etat = EtatAffame.getInstance();// par defaut l'animal est affamé
        this.etat.setAnimal(this); // Associe l'animal à l'état courant
    }



    // Méthodes abstraites
    public abstract void afficher();

    // Méthodes déléguées à l'état
    public void seNourrir() {
        etat.seNourrir();
    }

    public void apprivoiser() {
        etat.apprivoiser();
    }

    public void recevoirCoup() {
        etat.recevoirCoup();
    }

    public void agir() {
        etat.agir();
    }




    // les getters et setters

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public EtatAnimal getEtat() {
        return etat;
    }


    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }

    public char getSymbole() {
        return symbole;
    }


    public void setEtat(EtatAnimal nouvelEtat) {
        this.etat = nouvelEtat;
        this.etat.setAnimal(this); // Met à jour l'association avec l'animal
    }



}