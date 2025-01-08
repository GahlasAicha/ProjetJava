package model;

import util.Couleurs;

import java.util.Random;

public class Scorpion extends Predateur{
    private boolean sousRocher = false;  // Si le scorpion est caché sous un rocher
    private int compteurToursSousRocher = 0;  // Compteur de tours sous le rocher
    private int toursSansAttaque = 0;
    private char sy=' ';
    public Scorpion(String nom ,int x, int y,Carte carte ) {
        super("Scorpion ",x, y,carte);
    }

    @Override
    public void deplacer() {
        if (compteurToursSousRocher > 0) {
            // Si le scorpion est sous le rocher, il ne se déplace pas
            compteurToursSousRocher--;
            System.out.println(nom + " reste caché sous le rocher. Tours restants : " + compteurToursSousRocher);
            return;
        }
        // Définir les directions possibles (haut, bas, gauche, droite)
        int[] directionsX = {-1, 1, 0, 0}; // Déplacement sur l'axe X : -1 (gauche), 1 (droite), 0 (pas de déplacement)
        int[] directionsY = {0, 0, -1, 1}; // Déplacement sur l'axe Y : -1 (haut), 1 (bas), 0 (pas de déplacement)

        Random random = new Random();
        int direction = random.nextInt(4); // Choix aléatoire parmi 4 directions

        // Calculer la nouvelle position
        int newX = this.getX() + directionsX[direction];
        int newY = this.getY() + directionsY[direction];
        if (carte.estCaseVide(newX, newY) || estPetiteCaseRocher(newX, newY)) {
            if (estPetiteCaseRocher(newX, newY)) {
                // Si le scorpion arrive sur un petit rocher, il se cache sous le rocher
                sousRocher = true;
                compteurToursSousRocher = 5;  // Le scorpion reste sous le rocher pendant 5 tours
                System.out.println(nom + " est caché sous un petit rocher pendant 5 tours.");
            }
            carte.setCaseContenu(x,y,sy);
            x = newX;
            y = newY;
            sy= carte.getContenuCase(x,y);
            carte.setCaseContenu(x,y,'D');
            System.out.println(nom + " se déplace à la position (" + x + ", " + y + ")");
            return; // Le renard se déplace et la méthode termine
        } else {
            System.out.println(nom + " ne peut pas se déplacer à la position (" + newX + ", " + newY + ")");
        }
    }

    private boolean estPetiteCaseRocher(int newX, int newY) {
        Case caseCible = carte.getCase(newX, newY);
        return caseCible != null && caseCible.getContenu() == 'P';  // Si la case contient un rocher (représenté par 'P')
    }


    @Override
    public void attaquer(Animal animal) {
        if (toursSansAttaque > 0) {
            // Le scorpion ne peut pas attaquer pendant les tours sans attaque
            System.out.println(nom + " ne peut pas attaquer pendant " + toursSansAttaque + " tours.");
            toursSansAttaque--;
            return; // Le scorpion ne peut pas attaquer pour le moment
        }
        if (animal instanceof Singe) {
            Singe singe = (Singe) animal;

            // Vérifie si l'animal est adjacent au scorpion
            if (estCaseAdjacente( singe)) {
                carte.supprimerAnimal(singe);  // Le singe meurt immédiatement
                System.out.println("Un singe a été piqué par le scorpion et est mort.");
            }
            // Vérifie si le scorpion est sous un rocher et attaque un singe réfugié sous le rocher
            if (sousRocher &&  Case.estSousRocher(singe.getCase())) {
                carte.supprimerAnimal(singe);  // Le singe meurt immédiatement
                System.out.println("Un singe a été piqué par un scorpion caché sous un rocher et est mort.");
            }
            // Après l'attaque, le scorpion ne peut plus attaquer pendant 2 tours
            toursSansAttaque = 2;

            System.out.println(Couleurs.colorerTexte(nom + " change de couleur en  après avoir attaqué.", Couleurs.ANSI_PURPLE));
        }
    }

}






