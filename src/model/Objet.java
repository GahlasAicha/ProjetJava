package model;

public class Objet {
    private String nom;
    //private int valeur;
    private char symbole;
    public boolean estRamassable;

    public Objet(String nom,char symbol,boolean estRamassable ){
        this.nom=nom;
        this.symbole= symbol;
        this.estRamassable=estRamassable;
    }

    // les getters et setters


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

    // les methodes
    public void afficher() {
        System.out.println("Objet: " + nom + " (symbole: " + symbole + ")");
        if (estRamassable) {
            System.out.println("Cet objet peut être ramassé.");
        } else {
            System.out.println("Cet objet ne peut pas être ramassé.");
        }
    }


}
