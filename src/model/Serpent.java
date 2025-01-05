package model;

import util.Couleurs;

import java.util.Random;

public class Serpent extends Predateur {
    private boolean enDigestion = false;
    private int compteurDigestion = 0;
    public Serpent(String nom, int x, int y, Carte carte) {
        super("Serpent", x, y, carte);
    }

    @Override
    public void deplacer() {
        // Vérifie si le serpent peut se déplacer
        if (enDigestion) {
            System.out.println(Couleurs.colorerTexte("Le serpent est en digestion et ne peut pas se déplacer.", Couleurs.ANSI_RED));
            return;
        }

        Random random = new Random();
        int direction = random.nextInt(4); // Choix aléatoire parmi 4 directions


        // on  sauvegrade les coordonnéees avant le deplcanement
        int ancienneX = this.getX();
        int ancienneY = this.getY();
        switch (direction) {
            case 0: // Déplacement vers le haut
                this.setY(this.getY() - 2);
                break;
            case 1: // Déplacement vers le bas
                this.setY(this.getY() + 2);
                break;
            case 2: // Déplacement vers la gauche
                this.setX(this.getX() - 2);
                break;
            case 3: // Déplacement vers la droite
                this.setX(this.getX() + 2);
                break;
        }
        // Vérification si la nouvelle position est valide sur la carte
        if (!estPositionValide(this.getX(), this.getY())) {
            // Si la position est invalide, revenir à la position précédente
            this.setX(ancienneX);
            this.setY(ancienneY);
            System.out.println("Le hibou a tenté de sortir de la carte, il revient à sa position initiale.");
        } else {
            System.out.println("Le hibou se déplace de 2 cases dans la direction " + direction);
        }
    }

    @Override
    public void attaquer(Animal animal) {
        // Vérifier si l'animal est un singe et s'il est dans une case adjacente
        if (animal instanceof Singe) {
            Singe singe = (Singe) animal;

            if (enDigestion) {
                // Le serpent est en digestion et ne peut pas se déplacer
                System.out.println(Couleurs.colorerTexte("Le serpent est en digestion et reste sur place.", Couleurs.ANSI_RED));
                return; // Sortir de la méthode pour empêcher le déplacement
            }
            if (singe.estEffraye()) {
                // Le serpent est effrayé et se cache
                System.out.println(Couleurs.colorerTexte("Le serpent est effrayé et reste caché dans un cocotier.", Couleurs.ANSI_BLUE));
                return; // Sortir de la méthode
            }
            //verifier si le singe est dans une case adjacente au serprent
            if (estCaseAdjacente(singe)) {// Vérifier s'il y a un cocotier dans une case adjacente au singe
                boolean cocotierProche = false;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        // Vérifier si une case adjacente au singe contient un cocotier
                        if (caseContientCocotier(singe.getX() + dx, singe.getY() + dy)) {
                            cocotierProche = true;
                            break;
                        }
                    }
                    if (cocotierProche) break;
                }

                // Si aucun cocotier n'est trouvé, le serpent mange le singe
                if (!cocotierProche) {
                    System.out.println("Le serpent mange le singe !");
                    carte.supprimerAnimal(singe);

                    entrerEnDigestion();
                } else {
                    // Si un cocotier est proche, le serpent fait fuir le singe dans la direction du cocotier
                    System.out.println("Le serpent fait fuir le singe dans un cocotier !");
                    deplacerSingeVersCocotier(singe);
                    singe.devenirEffraye();               }


            }

        }
    }


    private void deplacerSingeVersCocotier(Singe singe) {
        // Vérifier les cases adjacentes pour voir si un cocotier est présent
        int[] directionsX = {-1, 1, 0, 0}; // Direction horizontale (gauche, droite)
        int[] directionsY = {0, 0, -1, 1}; // Direction verticale (haut, bas)
        boolean cocotierTrouve = false;
        for (int i = 0; i < directionsX.length; i++) {
            int nouvelleX = singe.getX() + directionsX[i];
            int nouvelleY = singe.getY() + directionsY[i];

            // Vérifier si la case est valide et contient un cocotier
            if (estPositionValide(nouvelleX, nouvelleY) && caseContientCocotier(nouvelleX, nouvelleY)) {
                // Si un cocotier est trouvé, déplacer le singe vers cette case
                singe.setX(nouvelleX);
                singe.setY(nouvelleY);
                cocotierTrouve = true;
                System.out.println("Le singe a été déplacé vers le cocotier en position (" + nouvelleX + ", " + nouvelleY + ")");
                break;
            }
        }

        // Si aucun cocotier n'est trouvé autour du singe
        if (!cocotierTrouve) {
            System.out.println("Aucun cocotier trouvé autour du singe.");
        }
    }

    // Vérifier si une case contient un cocotier
    public boolean caseContientCocotier(int x, int y) {
        if (x >= 0 && y >= 0 && x < carte.getLargeur() && y < carte.getHauteur()) {
            Case caseActuelle = carte.getCase(x, y);
            return caseActuelle.contientCocotier();
        }
        return false;
    }



    private void entrerEnDigestion() {
        enDigestion = true;
        compteurDigestion = 3; // Le serpent reste en digestion pendant 3 tours
        String message = Couleurs.colorerTexte("Le serpent entre en digestion et change de couleur !", Couleurs.ANSI_YELLOW);
        System.out.println(message);
    }

    public void terminerDigestion() {
        enDigestion = false;
        compteurDigestion = 0;
        String message = Couleurs.colorerTexte("Le serpent a terminé sa digestion et retrouve sa couleur d'origine !", Couleurs.ANSI_YELLOW);
        System.out.println(message);
    }



}


