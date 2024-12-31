package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Carte {
    private Case[][] grille;
    //private String type;
    private static int largeur;
    private static int hauteur;
    private Personnage personnage;
    private int personnageX;  // Coordonnée x du personnage
    private int personnageY;  // Coordonnée y du personnage

    public Carte(int hauteur,int largeur){
        if (largeur <= 0 || hauteur <= 0) {
            throw new IllegalArgumentException("Largeur et hauteur doivent être positives !");
        }
        Carte.largeur = largeur;
        Carte.hauteur = hauteur;
        this.grille = new Case[hauteur][largeur];
        initialiserCarteVide(largeur,hauteur);
    }

    // Initialisation de la carte avec une grille vide
    private void initialiserCarteVide(int largeur, int hauteur) {
        // Vérification des dimensions
        if (largeur <= 0 || hauteur <= 0) {
            throw new IllegalArgumentException("La largeur et la hauteur doivent être positives et supérieures à 0.");
        }

        // Initialisation de la grille si elle n'est pas encore créée
        if (grille == null) {
            grille = new Case[largeur][hauteur];  // Création de la grille 2D avec les dimensions spécifiées
        }

        // Remplissage de la grille avec des cases vides
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                grille[i][j] = new Case(i, j, ' '); // crée case sans contenu
            }
        }
    }


    private void genererCarteAleatoire(char[] contenu) {
        Random random = new Random();

        // Crée un tableau pondéré avec 85% d'espaces vides
        int proportionEspaces = 90; // Pourcentage d'espaces vides
        int totalPonderation = 100; // Total de la pondération (pour simplification)
        int repetitionsAutres = (totalPonderation - proportionEspaces) / (contenu.length - 1);

        char[] contenuPondere = new char[totalPonderation];
        int index = 0;

        for (char symbole : contenu) {
            int repetition = (symbole == ' ') ? proportionEspaces : repetitionsAutres;
            for (int i = 0; i < repetition; i++) {
                contenuPondere[index++] = symbole;
            }
        }

        // Génère la grille avec les symboles pondérés
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                char symbole = contenuPondere[random.nextInt(contenuPondere.length)];
                grille[y][x] = new Case(x, y, symbole); // Assigne une case avec un symbole
            }
        }

        // Exemple de placement fixe : positionner le personnage '@' au centre
        int centreX = largeur / 2;
        int centreY = hauteur / 2;
        grille[centreY][centreX] = new Case(centreX, centreY, '@');
    }

    public void chargerDepuisFichier(String cheminFichier) throws IOException {
            // Assurez-vous que la largeur et la hauteur sont bien définies avant de charger la carte
            if (largeur <= 0 || hauteur <= 0) {
                throw new IllegalArgumentException("Les dimensions de la carte doivent être valides.");
            }

            // Vérifier si le fichier contient suffisamment de lignes
            try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
                long lineCount = br.lines().count();  // Compter le nombre de lignes dans le fichier

                if (lineCount < hauteur) {
                    throw new IOException("Le fichier ne contient pas assez de lignes pour remplir la carte.");
                }
            }

          try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
                String ligne;
                int ligneIndex = 0;

                while ((ligne = br.readLine()) != null && ligneIndex < hauteur) {
                    char[] caracteres = ligne.toCharArray();

                    for (int colIndex = 0; colIndex < Math.min(caracteres.length, largeur); colIndex++) {

                        char symbole = caracteres[colIndex];
                        if (symbole == ' ') {
                            grille[ligneIndex][colIndex] = new Case(ligneIndex, colIndex, ' ');
                        } else {
                            grille[ligneIndex][colIndex] = new Case(ligneIndex, colIndex, symbole);
                        }
                    }
                    ligneIndex++;
                }
            }
        }



    // Assurez-vous que cette méthode existe et fonctionne correctement
    public void setCaseContenu(int x, int y, char contenu) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            grille[x][y].setContenu(contenu);
        } else {
            throw new ArrayIndexOutOfBoundsException("Coordonnées hors de la carte : (" + x + ", " + y + ")");
        }
    }



    //getter et setter
    public Case getCase(int x, int y) {
        if (y < 0 || y >= hauteur || x < 0 || x >= largeur) {
            throw new IndexOutOfBoundsException("Coordonnées hors de la carte !");
        }
        return grille[y][x];
    }




//    public void setCaseContenu(int x, int y, char contenu) {
//        Case c = getCase(x, y);
//        c.setContenu(contenu);
//    }

    //afficher la carte
    public void afficherCarte(char[] contenu) {
        genererCarteAleatoire(contenu);

    }

    public char getContenuCase(int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            return grille[x][y].getContenu();
        }
        throw new IndexOutOfBoundsException("Coordonnées hors de la carte !");
    }

    public static List<int[]> obtenirCasesAdjacentes(int x, int y) {
        List<int[]>  casesAdjacentes = new ArrayList<>();


        // on va verifier lescases adjacentes dans les 4 directions

        if (x > 0) casesAdjacentes.add(new int[]{x - 1, y}); // Case à gauche
        if (x < largeur-1) casesAdjacentes.add(new int[]{x + 1, y}); // Case à droite
        if (y > 0) casesAdjacentes.add(new int[]{x, y - 1}); // Case en haut
        if (y < hauteur-1) casesAdjacentes.add(new int[]{x, y + 1}); // Case en bas

        return casesAdjacentes; // Retourne la liste des cases adjacentes valides
    }

    // Méthode pour obtenir la position du personnage
    public int[] getPositionPersonnage() {
        // Retourne un tableau contenant les coordonnées (x, y) du personnage
        return new int[] {personnageX, personnageY};
    }



    public void ajouterObjet(Objet objet, int x, int y) {
        if (objet == null) {
            System.out.println("Erreur : Objet est null. Impossible de l'ajouter à la carte.");
            return;
        }

        if (grille == null || grille.length == 0 || grille[0].length == 0) {
            System.out.println("Erreur : La grille n'est pas initialisée correctement.");
            return;
        }

        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            System.out.printf("Erreur : Les coordonnées (%d, %d) sont en dehors des limites de la carte (largeur : %d, hauteur : %d).%n", x, y, largeur, hauteur);
            return;
        }

        try {
            grille[y][x].setContenu(objet.getSymbole());
        } catch (Exception e) {
            System.out.printf("Erreur : Impossible d'ajouter l'objet '%s' à la position (%d, %d) en raison de : %s%n",
                    objet.getSymbole(), x, y, e.getMessage());
            e.printStackTrace(); // Affiche la trace pour faciliter le débogage
        }
    }



    public void ajouterAnimal(Animal animal, int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            grille[x][y].setContenu('E'); // 'E' pour représenter un écureuil
        } else {
            throw new IllegalArgumentException("Coordonnées hors des limites de la carte.");
        }
    }

    public void ajouterPersonnage(String perso, int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            grille[x][y].setContenu('@'); // '@' pour représenter le personnage
            setPositionPersonnage(x, y); // Met à jour les coordonnées du personnage
        } else {
            throw new IllegalArgumentException("Coordonnées hors des limites de la carte.");
        }
    }
    private void setPositionPersonnage(int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            // Nettoyer l'ancienne position du personnage
            if (grille[personnageX][personnageY].getContenu() == '@') {
                grille[personnageX][personnageY].setContenu(' '); // Remplace '@' par un espace vide
            }

            // Mettre à jour la nouvelle position
            personnageX = x;
            personnageY = y;
            grille[personnageX][personnageY].setContenu('@'); // Place '@' à la nouvelle position
        } else {
            throw new IllegalArgumentException("Coordonnées hors des limites de la carte.");
        }
    }




    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }


    public Case[][] getGrille() {
        return grille;
    }

    public Personnage getPersonnage() {
        return personnage;
    }
}






