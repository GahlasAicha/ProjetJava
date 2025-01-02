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
    public static int largeur;
    public static int hauteur;
    private int personnageX;  // Coordonnée x du personnage
    private int personnageY;  // Coordonnée y du personnage

    public Carte(int hauteur,int largeur){
        if (largeur <= 0 || hauteur <= 0) {
            throw new IllegalArgumentException("Largeur et hauteur doivent être positives !");
        }
        Carte.largeur = largeur;
        Carte.hauteur = hauteur;
        this.grille = new Case[hauteur][largeur];
        initialiserCarteVide();
    }

    private void initialiserCarteVide(){
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
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int ligneIndex = 0;

            while ((ligne = br.readLine()) != null && ligneIndex < hauteur) {
                char[] caracteres = ligne.toCharArray();

                for (int colIndex = 0; colIndex < Math.min(caracteres.length, largeur); colIndex++) {
                    char symbole = caracteres[colIndex];
                    if (symbole == ' ') {
                        setCaseContenu(ligneIndex, colIndex, ' ');
                    } else {
                        setCaseContenu(ligneIndex, colIndex, symbole);
                    }
                }
                ligneIndex++;
            }
        }
    }


    //getter et setter
    public Case getCase(int x, int y) {
        if (x < 0 || x >= hauteur || y < 0 || y >= largeur) {

            throw new IndexOutOfBoundsException("Coordonnées hors de la carte !");
        }
        return grille[x][y];
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public Case[][] getGrille() {
        return grille;
    }

    public void setCaseContenu(int x, int y, char contenu) {
        Case c = getCase(x, y);
        c.setContenu(contenu);
    }

    //afficher la carte
    public void afficherCarte(char[] contenu) {
        genererCarteAleatoire(contenu);

    }




    public char getContenuCase(int x, int y) {
        if ( x >= 0 && x < largeur-1 && y >=0 && y < hauteur-1 ){
            return grille[x][y].getContenu();
        }
        return ' ';
    }


    public static List<int[]> obtenirCasesAdjacentes(int x, int y, int hauteur, int largeur) {
        List<int[]> cases = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (estCaseValide(nx, ny, hauteur, largeur)) { // Passez les dimensions de la carte
                cases.add(new int[] {nx, ny});
            }
        }

        return cases;
    }



    // Méthode pour obtenir la position du personnage
    public int[] getPositionPersonnage() {
        // Retourne un tableau contenant les coordonnées (x, y) du personnage
        return new int[] {personnageX, personnageY};
    }

    public static boolean estCaseValide(int x, int y, int hauteur, int largeur) {
        return x >= 0 && x < hauteur && y >= 0 && y < largeur;
    }

    // Méthodes pour déplacer le personnage, mettre à jour sa position, etc.
    public void setPositionPersonnage(int x, int y) {
        this.personnageX = x;
        this.personnageY = y;
    }

    public boolean estPositionValide(int x, int y) {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur; // largeur et hauteur définissent la taille de la carte
    }
    public int compterAnimaux() {
        int nombreAnimaux = 0;

        // Parcours de la carte pour compter les animaux
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                Case caseActuelle = grille[i][j]; // Récupère la case actuelle
                char contenu = caseActuelle.getContenu(); // Récupère le contenu de la case

                // Vérifier si la case contient un animal (par exemple 'E' pour l'écureuil et 'S' pour le singe)
                if (contenu == 'E' || contenu == 'S') {
                    nombreAnimaux++;
                }
            }
        }
        return nombreAnimaux;
    }
}









