package view;

import model.Carte;
import model.Case;
import model.Partie;
import util.Couleurs;

import java.util.Scanner;

public class Ihm {
    private Scanner scanner;

    public Ihm(){
        this.scanner= new Scanner(System.in);
    }

    public void afficherMessage(String message) {
        System.out.println(Couleurs.ANSI_WHITE + message + Couleurs.ANSI_RESET);
    }

    public void afficherMessageSucces(String message) {
        System.out.println(Couleurs.ANSI_GREEN + message + Couleurs.ANSI_RESET);
    }

    public void afficherMessageErreur(String message) {
        System.out.println(Couleurs.ANSI_RED + message + Couleurs.ANSI_RESET);
    }

    public void afficherCarte(Partie partie) {
        Carte carte = partie.getCarte();
        Case[][] grille = carte.getGrille();
        System.out.println(Couleurs.ANSI_YELLOW + "\nAffichage de la carte :" + Couleurs.ANSI_RESET);
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                Case currentCase = grille[i][j];
                if (currentCase.getContenu() == '.') {
                    System.out.print(Couleurs.ANSI_GREEN + " " + Couleurs.ANSI_RESET + " ");
                } else {
                    char symbole = currentCase.getContenu();
                    String couleur;
                    String backGroundColor;
                    switch (symbole) {
                        case '@': // Personnage
                            couleur = Couleurs.ANSI_PURPLE;
                            backGroundColor=Couleurs.ANSI_WHITE_BACKGROUND;

                            break;
                        case 'E': // Écureuil
                        case 'S': // Singe
                            couleur = Couleurs.ANSI_RED;
                            backGroundColor= Couleurs.ANSI_YELLOW_BACKGROUND;
                            break;
                        case 'A':
                        case 'B':   // Arbre
                            couleur = Couleurs.ANSI_GREEN;
                            backGroundColor=Couleurs.ANSI_BLACK_BACKGROUND;
                            break;
                        case 'G': // Gland ou Banane
                            couleur = Couleurs.ANSI_YELLOW;
                            backGroundColor=Couleurs.ANSI_RED_BACKGROUND;
                            break;
                        case 'C': // Champignon
                            couleur = Couleurs.ANSI_RED;
                            backGroundColor=Couleurs.ANSI_WHITE_BACKGROUND;
                            break;
                        default:
                            couleur = Couleurs.ANSI_BLACK;
                            backGroundColor=Couleurs.ANSI_GREEN_BACKGROUND;

                            break;
                    }
                    System.out.print(couleur + backGroundColor + symbole + Couleurs.ANSI_GREEN_BACKGROUND + Couleurs.ANSI_RESET);

                }
            }
            System.out.println();
        }
    }

    public int lireEntreeEntier(String invite){
        System.out.print(Couleurs.ANSI_CYAN + invite + Couleurs.ANSI_RESET);
        return scanner.nextInt();
    }

    public String lireEntreeTexte(String invite) {
        System.out.print(Couleurs.ANSI_CYAN + invite + Couleurs.ANSI_RESET);
        return scanner.next();
    }

}
