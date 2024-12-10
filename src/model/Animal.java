package model;

public  abstract class Animal {
    private  String nom;
    private char symbole;
    private  int x, y;
    protected EtatAnimal etat;
       private boolean ami ;
     private int compteurTour=0;

    public Animal(String nom, char symbole, int x, int y) {

        this.nom = nom;
        this.symbole = symbole;
        this.x = x;
        this.y = y;
        this.setEtat(EtatAffame.getInstance());// par defaut l'animal est affamé

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

    public void agir(Carte carte) {
        etat.agir(carte);
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


    public void setEtat(EtatAnimal etat) {
        if (etat == null) {
            throw new IllegalArgumentException("L'état ne peut pas être null.");
        }
        this.etat = etat;
        this.etat.setAnimal(this); // Associe cet animal à l'état
    }


    public void incrementerCompteur() {

        compteurTour++;
    }

    public int getCompteurTour() {
        return compteurTour;
    }
    public void resetCompteur() {
        compteurTour = 0;
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
        // Récupère la position du personnage sur la carte
        int[] positionPersonnage = carte.getPositionPersonnage(); // Suppose qu'il y a une méthode dans Carte pour obtenir la position du personnage

        // Calcule la distance entre l'animal et le personnage
        int distanceX = Math.abs(this.x - positionPersonnage[0]);
        int distanceY = Math.abs(this.y - positionPersonnage[1]);

        // Si l'animal est à une case adjacente au personnage (en ligne droite ou diagonale)
        return distanceX <= 1 && distanceY <= 1;
    }

    public void setAmi(boolean b) {
        this.ami = b;
    }

    // Méthode pour obtenir si l'animal est ami ou non
    public boolean estAmi() {
        return this.ami;
    }
}