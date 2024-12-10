package controller;

import model.*;
import util.Couleurs;
import view.Ihm;

import java.util.ArrayList;

public class Controller {
    private Partie partie;
    private Ihm ihm;

    public Controller( Ihm ihm){
        int choi=ihm.lireEntreeEntier("choisissez un theme: \n1. Foret \n2. Jungle \n");
        int choi2=ihm.lireEntreeEntier("avez vous une carte existante? \n1. oui \n2. non \n");
        if (choi==1){
            partie= new PartieForet(choi2);
        }else {
            partie= new PartieJungle(choi2);
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
            partie.deplacerPersonage(dy, dx);
            ihm.afficherMessageSucces("Le personnage s'est déplacé.");
        } catch (IndexOutOfBoundsException e) {
            ihm.afficherMessageErreur("Déplacement hors limites !");
        }
    }

    private void interagir(){// 8/12
        ihm.afficherMessage("\nInteraction :");
        int choix=ihm.lireEntreeEntier("\n1. ramasse un objet\n");
        if (choix==1){
            partie.ramasserObjet(partie.getPersonnage().getX()+1,partie.getPersonnage().getY());
        }

    }

    private void menuObjetAmi(){// 10/12
        ihm.afficherMessage("\nmenu:");
        ArrayList<Objet> objets=partie.getPersonnage().getInventaire();
        ArrayList<Animal> animals=partie.getPersonnage().getAmis();
        for (int i=0;i<objets.size();i++){
            ihm.afficherMessage(i+"."+objets.get(i).getNom());
        }
        for (int j=0;j<animals.size();j++){
            ihm.afficherMessage(j+objets.size()+"."+animals.get(j).getNom());
        }
        if (objets.isEmpty() && animals.isEmpty()){
            ihm.afficherMessage("votre sac est vide!");
        }
        int choix= ihm.lireEntreeEntier("\n1. poser un objet!\n2. lencer un objet\n3.retour\n");
        switch (choix){
            case 1:

                int choi= ihm.lireEntreeEntier("choisissez l'objet\n");
                String direction = ihm.lireEntreeTexte("choisissez la direction haut , bas , gauche  et droite ");

                if (choi>=0 && choi<objets.size()) {
                    partie.lancerObjet(choi,direction);
                } else {
                    ihm.afficherMessage("chois invalid");
                }
                break;
            case 2:
                choi = ihm.lireEntreeEntier("choisissez l'objet\n");
                direction = ihm.lireEntreeTexte("choisissez la direction haut , bas , gauche  et droite ");
                if (choi>=0 && choi<objets.size()) {
                    partie.jeterObjet(choi,direction);
                } else {
                    ihm.afficherMessage("chois invalid");
                }
                break;
            case 3:
                break;

        }

    }

    private void passerTour(){
        ihm.afficherMessage("\nVous passez votre tour.");
        partie.isEstEnCours();

    }

}