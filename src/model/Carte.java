package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Carte {
    private Case[][] grille;
    //private String type;
    private int largeur,hauteur;

    public Carte(int largeur,int hauteur){
        this.largeur = largeur;
        this.hauteur = hauteur;
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
    public void afficherCarte() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(grille[i][j] + ".");
            }
            System.out.println();
        }
    }


    private char getContenuCase(int i, int i1) {
          if ( i >= 0 && i < largeur && i1 >=0 && i1 < hauteur ){
              return grille[i][i1].getContenu();
          }
          return ' ';
    }


    private List<int[]> obtenirCasesAdjacentes(int x, int y) {
        List<int[]>  casesAdjaventes = new ArrayList<>();


        // on va verifier lescases adjacentes dans les 4 directions
    }


}
