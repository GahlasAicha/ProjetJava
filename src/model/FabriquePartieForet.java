package model;

import java.io.IOException;

public class FabriquePartieForet extends FabriqueAbstraitePartie {
    private FabriqueElementsForet elements;
    private final Partie partie;
    public FabriquePartieForet(Partie partie, int choix, int choix2) {// 01/01 on cree la partie dans le constructeur!!
        this.partie=partie;
        if (choix2== 2) {
            this.elements = new FabriqueElementsForet();
        }else if (choix2==1){
            this.elements = new FabriqueElementsForetDanger();
        }
        initialiserPartie(choix,choix2);
    }



    @Override
    public void initialiserCarte(int hauteur, int largeur) {
        if (elements == null) {
            throw new IllegalStateException("L'attribut 'elements' n'a pas été initialisé !");
        }
        partie.setCarte(new Carte(hauteur, largeur));
        partie.getCarte().afficherCarte(elements.getSymbolesAutorises()); // Utilisation sécurisée
    }


    @Override
    public void initialiserElements() {
        for (int i =0 ; i< partie.getCarte().getHauteur(); i++){
            for (int j = 0 ; j< partie.getCarte().getLargeur();j++){

                char caseContenu = partie.getCarte().getCase(i,j).getContenu();
                switch (caseContenu) {
                    case '@':// cree un nouveu personnage dans l'emplacement initialise
                        partie.ajouterPersonnage("perso",i,j);
                    case 'E':
                        Ecureuil ecureuil = (Ecureuil) elements.creerAnimal(caseContenu,i,j);
                        partie.ajouterAnimal(ecureuil);// ajouter les animeux dans la list
                        break;
                    case 'R','H':
                        Predateur predateur=elements.creerPredateur(caseContenu,i,j,partie.getCarte());
                        partie.ajouterPredateur(predateur);
                        break;
                    case ' ':
                        break;
                    default:
                        Objet objet=elements.creerObjet(caseContenu,i,j);
                        partie.ajouterObjet(objet);
                        break;
                }
            }
        }
    }
    public void initialiserPartie(int choix,int choix2) {
        if (choix==1){
            getCarteExistente(35, 100,choix2);
        }else if (choix==2){
            initialiserCarte(35, 100);
        }
        initialiserElements();
    }
    protected void getCarteExistente(int hauteur, int largeur, int choix) {
        partie.setCarte(new Carte(hauteur, largeur));
        try {
            if ( choix==1){
                partie.getCarte().chargerDepuisFichier("carte_danger.txt");
            }else
                partie.getCarte().chargerDepuisFichier("carte.txt");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte depuis le fichier : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}












