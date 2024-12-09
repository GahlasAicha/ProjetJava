package model;

import java.io.IOException;

public class PartieJungle extends Partie{
    private Singe[][] singes ;
    private Objet[][] objets;

    public PartieJungle() {
        super();
    }

    @Override
    public void initialiserPartie() {
        initialiserCarte(35,100);
        initialiserElements();
    }



    @Override
    protected void initialiserCarte(int hauteur, int largeur) {
        this.carte = new Carte(largeur,hauteur);
        objets = new Objet[hauteur][largeur];
        singes = new Singe[hauteur][largeur];
        try {
            this.carte.chargerDepuisFichier("carte_jungle.txt"); // Le fichier de carte pour la jungle
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte depuis le fichier : " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void initialiserElements() {
        for (int i = 0; i < carte.getHauteur(); i++) {
            for (int j = 0; j < carte.getLargeur(); j++) {
                char caseContenu = carte.getCase(i, j).getContenu();
                switch (caseContenu) {
                    case '@':
                        setPersonnage(new Personnage("fiter",i,j));
                    case 'S': // Singe
                        Singe singe = new Singe(i,j);
                        singes[i][j] = singe; // Ajouter le singe à la matrice des singes
                        break;
                    case 'B': // Banane
                        objets[i][j] = new Objet("Banane", 'B', true); // Ajouter une banane à cet endroit
                        break;
                    case 'C': // Champignon
                        objets[i][j] = new Objet("Champignon", 'C', true); // Ajouter un champignon
                        break;
                    case 'P': // Petit rocher
                        objets[i][j] = new Objet("Petit rocher", 'P', false); // Ajouter un rocher
                        break;
                    case 'T': // Cocotier
                        objets[i][j] = new Objet("Cocotier", 'T', false); // Ajouter un cocotier
                        break;
                    default: // Zone vide
                        break;
                }
            }
        }
    }

    public void setPersonnage(Personnage fiter) {
    }


    @Override
    public void rammaserObjet(int x, int y) {
        if (objets[x][y] != null) {
            Objet objet = objets[x][y];
            if (objet.isEstRamassable()) {
                System.out.println("Vous pouvez ramasser l'objet " + objet.getNom() + " !");
                objets[x][y] = null;
            } else {
                System.out.println("Cet objet n'est pas ramassable.");
            }
        } else {
            System.out.println("Il n'y a rien à ramasser ici.");
        }
    }
}



