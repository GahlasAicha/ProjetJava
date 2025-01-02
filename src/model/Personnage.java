package model;

import util.Couleurs;

import java.util.ArrayList;

public class Personnage {
    private String nom;
    private final char symbole;
    private final String couleur= Couleurs.ANSI_PURPLE + Couleurs.ANSI_WHITE_BACKGROUND;
    private int x,y;
    private ArrayList<Objet> inventaire;
    private boolean estVivant;
    private ArrayList<Animal> amis; // Liste des animaux amis
    public Personnage(String nom,int x,int y) {
        this.nom=nom;
        this.inventaire=new ArrayList<>();
        this.amis=new ArrayList<Animal>();
        this.x= x;
        this.y=y;
        this.symbole='@';
    }


    public void seDeplacer(int dX, int dY) {
        this.x += dX;
        this.y += dY;
    }


    // les methodes

    public boolean estEnface(int x,int y){
        return this.x + 1 == x && this.y == y;
    }
    public void ramasserObjet(Objet objet) {
        // Ajoute un objet
        inventaire.add(objet);
        objet.setX(-1);
        objet.setY(-1);
    }
    public Objet reposerObjet(int index) {
        // Retire un objet de l'inventaire, s'il est présent
        Objet objet= inventaire.remove(index);
        objet.setX(this.x+1);
        objet.setY(this.y);
        return objet;
    }
    public Objet jeterObjet(int index) {
        Objet objet= inventaire.remove(index);
        objet.setX(this.x+3);
        objet.setY(this.y);
        return objet;
    }

    public void nourrirAnimal(Objet nourriture) {
        // si la nouriture est presente
        if (inventaire.contains(nourriture)) {
            System.out.println(nom + " nourrit l'animal avec " + nourriture);
            inventaire.remove(nourriture);

        } else {
            System.out.println("Nourriture non disponible !");
        }
    }

    public void attaquerAnimal() {
        System.out.println(nom + " attaque l'animal !");
    }

    public void devenirAmi(Animal animal) {
        if (!amis.contains(animal)) {
            amis.add(animal);
            System.out.println(nom + " est devenu ami avec " + animal.getNom());
        }
    }

// les getters et setters


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

    public String getCouleur() {
        return couleur;
    }

    public boolean isEstVivant() {
        return estVivant;
    }

    public void setEstVivant(boolean estVivant) {
        this.estVivant = estVivant;
    }


    public ArrayList<Animal> getAmis(){
        return amis;
    }


    public ArrayList<Objet> getInventaire() {
        return inventaire;
    }

    public void setInventaire(ArrayList<Objet> inventaire) {
        this.inventaire = inventaire;
    }

}