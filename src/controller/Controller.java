package controller;

import model.*;
import util.Couleurs;
import view.Ihm;
public class Controller {
    private Partie partie;
    private Ihm ihm;

    public Controller( Ihm ihm){
        int choi=ihm.lireEntreeEntier("choisissez un theme: \n1. Foret \n2. Jungle \n");
        if (choi==1){
            partie= new PartieForet();
        }else {
            partie= new PartieJungle();
        }
        this.ihm=ihm;
    }

    public void demarrerJeu(){
        ihm.afficherMessage(Couleurs.ANSI_BLUE + "Bienvenue dans le jeu !" + Couleurs.ANSI_RESET);
        boolean enCours = true;

        while (enCours) {
            ihm.afficherCarte(partie);
            afficherMenu();
            int choix = ihm.lireEntreeEntier("Votre choix : ");

            switch (choix) {
                case 1:
                    deplacerPersonnage();
                    partie.setTours(partie.getTours()+1);
                    break;
                case 2:
                    interagir();
                    partie.setTours(partie.getTours()+1);
                    break;
                case 3:
                    menuObjetAmi();
                    break;
                case 4:
                    passerTour();
                    partie.setTours(partie.getTours()+1);
                    break;
                case 5:
                    ihm.afficherMessage("Merci d'avoir joué !");
                    enCours = false;
                    break;
                default:
                    ihm.afficherMessageErreur("Choix invalide. Réessayez.");
                    break;

            }
        }
    }

    private void afficherMenu(){
        ihm.afficherMessage("\nActions disponibles :");
        ihm.afficherMessage("1. Déplacer le personnage");
        ihm.afficherMessage("2. Interagir avec un animal ou un objet");
        ihm.afficherMessage("3. voir le menu des objet et amis");
        ihm.afficherMessage("4. Passer un tour");
        ihm.afficherMessage("5. Quitter le jeu");
    }

    private void deplacerPersonnage(){
        ihm.afficherMessage("\nDéplacement :");
        int dx = ihm.lireEntreeEntier("Entrez le déplacement en X (-1, 0, 1) : ");
        int dy = ihm.lireEntreeEntier("Entrez le déplacement en Y (-1, 0, 1) : ");

        try {
            partie.deplacerPersonage(dx, dy);
            ihm.afficherMessageSucces("Le personnage s'est déplacé.");
        } catch (IndexOutOfBoundsException e) {
            ihm.afficherMessageErreur("Déplacement hors limites !");
        }
    }

    private void interagir(){// 8/12
        ihm.afficherMessage("\nInteraction :");


    }

    private void menuObjetAmi(){// 8/12
        ihm.afficherMessage("\nmenu:");

        ihm.afficherMessage("\n lancer un objet!");

    }

    private void passerTour(){
        ihm.afficherMessage("\nVous passez votre tour.");
        partie.isEstEnCours();

    }

}
