package model;

public class Objet {
    private String nom;
    //private int valeur;
    private char symbole;
    public boolean estRamassable;
    int x,y;

    public Objet(String nom,char symbol,boolean estRamassable,int x,int y ){
        this.nom=nom;
        this.symbole= symbol;
        this.estRamassable=estRamassable;
        this.x=x;
        this.y=y;
    }

    // les getters et setters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEstRamassable() {
        return estRamassable;
    }

    public void setEstRamassable(boolean estRamassable) {
        this.estRamassable = estRamassable;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }


    public void setX(int i) {
    }

    public void setY(int y) {
    }

    // les methodes
    public void afficher() {
        System.out.println("Objet: " + nom + " (symbole: " + symbole + ")");
        if (estRamassable) {
            System.out.println("Cet objet peut être ramassé.");
        } else {
            System.out.println("Cet objet ne peut pas être ramassé.");
        }
    }

    // Méthode pour définir la nouvelle position de l'objet
    public void setPosition(int nouvelleX, int nouvelleY) {
        this.x = nouvelleX;  // Mettre à jour la position X
        this.y = nouvelleY;  // Mettre à jour la position Y
    }
}
