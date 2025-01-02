package model;

import java.io.IOException;

public class FabriquePartieForet extends FabriqueAbstraitePartie {
    private FabriqueElementsForet elements;
    private Partie partie;
    public FabriquePartieForet(Partie partie, int choix) {// 01/01 on cree la partie dans le constructeur!!
        this.partie=partie;
        this.elements= new FabriqueElementsForet();
        initialiserPartie(choix);
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
                        Ecureuil ecureuil = new Ecureuil(i, j);
                        partie.ajouterAnimal(ecureuil);// ajouter les animeux dans la list
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
    public void initialiserPartie(int choix) {
        if (choix==1){
            getCarteExistente(35, 100);
        }else {
            initialiserCarte(35, 100);
        }
        initialiserElements();
    }
    protected void getCarteExistente(int hauteur, int largeur) {
        partie.setCarte(new Carte(hauteur, largeur));
        try {
            partie.getCarte().chargerDepuisFichier("carte.txt");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte depuis le fichier : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}











