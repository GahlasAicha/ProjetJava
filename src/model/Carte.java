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

    public Carte(int largeur,int hauteur){
        Carte.largeur = largeur;
        Carte.hauteur = hauteur;
        this.grille = new Case[hauteur][largeur];
        initialiserCarteVide();
    }

    private void initialiserCarteVide(){
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                grille[i][j] = new Case(i, j, '.'); // crée case sans contenu
            }
        }
    }
    private void genererCarteAleatoire(char[] contenue) {
        Random random= new Random();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                char symbole= contenue[random.nextInt(contenue.length)];
                grille[y][x]=new Case(x,y,symbole);
            }
        }

    }
    public void chargerDepuisFichier(String cheminFichier) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int ligneIndex = 0;

            while ((ligne = br.readLine()) != null && ligneIndex < hauteur) {
                char[] caracteres = ligne.toCharArray();

                for (int colIndex = 0; colIndex < Math.min(caracteres.length, largeur); colIndex++) {
                    char symbole = caracteres[colIndex];
                    if (symbole == '.') {
                        grille[ligneIndex][colIndex] = new Case(ligneIndex, colIndex, '.');
                    } else {
                        grille[ligneIndex][colIndex] = new Case(ligneIndex, colIndex, symbole);
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




    public char getContenuCase(int i, int i1) {
          if ( i >= 0 && i < largeur && i1 >=0 && i1 < hauteur ){
              return grille[i][i1].getContenu();
          }
          return ' ';
    }


    public static List<int[]> obtenirCasesAdjacentes(int x, int y) {
        List<int[]>  casesAdjacentes = new ArrayList<>();


        // on va verifier lescases adjacentes dans les 4 directions

        if (x > 0) casesAdjacentes.add(new int[]{x - 1, y}); // Case à gauche
        if (x < largeur - 1) casesAdjacentes.add(new int[]{x + 1, y}); // Case à droite
        if (y > 0) casesAdjacentes.add(new int[]{x, y - 1}); // Case en haut
        if (y < hauteur - 1) casesAdjacentes.add(new int[]{x, y + 1}); // Case en bas

        return casesAdjacentes; // Retourne la liste des cases adjacentes valides
    }
    }



