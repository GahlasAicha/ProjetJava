package model;

import java.util.ArrayList;
import java.util.List;

public class Personnage {
    private String nom;
    private char symbole;
    private int vie,x,y,force;
    private ArrayList<Objet> inventaire;
    private boolean estVivant;
    private List<Case> amis; // Liste des animaux amis
    public Personnage(String nom,int x,int y) {
        this.nom=nom;
        this.inventaire=new ArrayList<>();
        this.x= x;
        this.y=y;
        this.symbole=symbole;
        this.amis=new ArrayList<>();
    }


    public void seDeplacer(int dX, int dY) {
        this.x += dX;
        this.y += dY;
    }

    // les methodes
    /* public void seDeplacer(int dX, int dY, Carte carte ) {
        // Déplacement de personnage
          int newX = x + dX;
          int newY= y + dY;
          if (newX >=0 && newX< carte.getHauteur() && newY>=0 && newY< carte.getLargeur()){
              Case destination = carte.getCase(newX, newY);
              if (!destination.isEstOccupee()) {
                  x = newX;
                  y = newY;
              } else {
                  System.out.println("La case est occupée !");
              }
          } else {
              System.out.println("Déplacement hors des limites !");
          }
   */

    public void ramasserObjet(Objet objet) {
        // Ajoute un objet
        inventaire.add(objet);
    }
    public void reposerObjet(Objet objet) {
        // Retire un objet de l'inventaire, s'il est présent
        inventaire.remove(objet);
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
            amis.add(new Case(animal.getX(), animal.getY(), animal.getSymbole()));
            System.out.println(nom + " est devenu ami avec " + animal.getNom());
        }
    }




// les getters et setters

public int getVie() {
    return vie;
}

public void setVie(int vie) {
    this.vie = vie;
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


public int getForce() {
    return force;
}

public void setForce(int force) {
    this.force = force;
}

public boolean isEstVivant() {
    return estVivant;
}

public void setEstVivant(boolean estVivant) {
    this.estVivant = estVivant;
}

public char getSymbole() {
    return symbole;
}
 public List<Case> getAmis(){
        return amis;
    }
}