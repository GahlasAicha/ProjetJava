package model;

import util.Couleurs;

public  abstract class Animal {
    private String nom;
    private char symbole;
    private String couleur = Couleurs.ANSI_PURPLE + Couleurs.ANSI_YELLOW_BACKGROUND;
    private int x, y;
    protected EtatAnimal etat;
    private boolean ami = false;
    private int compteurToursRassasie = 0; // Compte les tours rassasié
    private int compteurNourrituresPersonnage = 0; // Compte les nourritures près du personnage


    public Animal(String nom, char symbole, int x, int y) {

        this.nom = nom;
        this.symbole = symbole;
        this.x = x;
        this.y = y;
        this.etat = new EtatAffame();// par defaut l'animal est affamé

    }


    public void deplacerAnimal(int x, int y, Carte carte) {
        if (carte.estPositionValide(x, y)) {
            this.x = x;
            this.y = y;
            System.out.println(this.nom + " se déplace à la position (" + x + ", " + y + ")");
        } else {
            System.out.println(this.nom + " ne peut pas se déplacer à (" + x + ", " + y + ") : hors limites.");
        }
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

    public String getCouleur() {
        return couleur;
    }

    public void setEtat(EtatAnimal etat) {
        if (etat == null) {
            throw new IllegalArgumentException("L'état ne peut pas être null.");
        }
        this.etat = etat;
        this.etat.setAnimal(this); // Associe cet animal à l'état
    }


    // Méthode pour définir la nouvelle position de l'animal
    public void setPosition(int x, int y) {
        // Mise à jour des coordonnées de l'animal
        this.x = x;
        this.y = y;
        System.out.println("La position de " + nom + " a été mise à jour : (" + x + ", " + y + ")");
    }

    // Méthode pour vérifier si l'animal est proche du personnage
    public boolean estProchePersonnage(Carte carte) {
        int[] positionPersonnage = carte.getPositionPersonnage();
        int[] positionAnimal = new int[]{this.x, this.y};

        int distance = Math.abs(positionAnimal[0] - positionPersonnage[0]) + Math.abs(positionAnimal[1] - positionPersonnage[1]);
        return distance <= 1;
    }

    // Méthodes pour gérer les compteurs et l'amitié
    public void incrementerCompteurToursRassasie() {
        compteurToursRassasie++;
    }

    public int getCompteurToursRassasie() {
        return compteurToursRassasie;
    }

    public void resetCompteurToursRassasie() {
        compteurToursRassasie = 0;
    }

    public void incrementerCompteurNourrituresPersonnage() {
        compteurNourrituresPersonnage++;
    }

    public int getCompteurNourrituresPersonnage() {
        return compteurNourrituresPersonnage;
    }

    public void setAmi(boolean ami) {
        this.ami = ami;
    }

    public boolean estAmi() {
        return this.ami;
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
        ami = false; // Si un coup est donné à l'animal, l'amitié est rompue
        System.out.println(nom + " n'est plus ami avec le personnage après avoir reçu un coup.");
    }

    public void agir(Carte carte) {
        etat.agir(carte);
    }

    // Méthode qui vérifie l'amitié en fonction de la proximité et de l'état
    public void checkAmitie(Carte carte) {
        if (estProchePersonnage(carte)) {
            if (this instanceof Ecureuil) {
                // Si un écureuil affamé se nourrit près du personnage, il devient ami immédiatement
                if (this.etat instanceof EtatAffame) {
                    ami = true;
                    System.out.println(nom + " devient ami avec le personnage !");
                }
            } else if (this instanceof Singe) {
                // Si un singe affamé se nourrit près du personnage
                if (this.etat instanceof EtatAffame) {
                    incrementerCompteurNourrituresPersonnage();
                    if (compteurNourrituresPersonnage == 1) {
                        System.out.println(nom + " commence à apprécier le personnage.");
                    } else if (compteurNourrituresPersonnage >= 2) {
                        ami = true;
                        System.out.println(nom + " devient ami avec le personnage après 2 nourritures consécutives !");
                    }
                }
            }
        } else {
            // Réinitialisation des compteurs et de l'amitié si l'animal n'est plus proche
            resetCompteurNourrituresPersonnage();
            if (ami) {
                ami = false;
                System.out.println(nom + " n'est plus proche du personnage, l'amitié est rompue.");
            }
        }
    }

    // Méthode isAmi qui retourne si l'animal est ami
    public boolean isAmi() {
        return ami;
    }

    public void resetCompteurNourrituresPersonnage() {
        this.compteurNourrituresPersonnage = 0;
        System.out.println("Le compteur de nourritures près du personnage a été réinitialisé.");
    }
}


