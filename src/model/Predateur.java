package model;

public abstract class Predateur {

    protected  String nom;
    protected int x,y;
    protected static Carte carte ;

    public Predateur(String nom,int x, int y,Carte carte ){
        this.nom=nom;
        this.x=x;
        this.y=y;
        Predateur.carte =carte;
    }

    // Méthode abstraite pour déplacer le prédateur
    public abstract void deplacer();

    // Méthode abstraite pour attaquer un animal
    public abstract void attaquer(Animal animal);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static boolean estPositionValide(int x, int y) {
        Case[][] grille = carte.getGrille(); // Accéder à la grille de la carte
        return x >= 0 && x < grille.length && y >= 0 && y < grille[0].length;
    }
    boolean estCaseAdjacente(Animal animal) {
        int dx = Math.abs(this.getX() - animal.getX());
        int dy = Math.abs(this.getY() - animal.getY());
        return dx == 1 && dy == 0 || dx == 0 && dy == 1; // Adjacent si la différence est de 1 case sur l'axe X ou Y
    }
}
