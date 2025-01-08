package model;

import java.io.IOException;

public class FabriquePartieJungle extends FabriqueAbstraitePartie{
    private final Partie partie;
    private FabriqueElementsJungle elements;
    public FabriquePartieJungle(Partie partie, int choix, int choix2) {
        this.partie=partie;
        if (choix2==2) {
            this.elements = new FabriqueElementsJungle();
        }else if (choix2==1){
            this.elements = new FabriqueElementsJungleDanger();
        }
        initialiserPartie(choix,choix2);

    }

    @Override
    public void initialiserCarte(int hauteur, int largeur) { // 01/01 j'ai change creeCarte par initialiserCarte
        if (elements == null) {
            throw new IllegalStateException("L'attribut 'elements' n'a pas été initialisé !");
        }
        partie.setCarte(new Carte(hauteur, largeur));
        partie.getCarte().afficherCarte(elements.getSymbolesAutorises()); // Utilisation sécurisée
    }

    @Override
    public void initialiserElements() {
        for (int i = 0; i < partie.getCarte().getHauteur(); i++) {
            for (int j = 0; j < partie.getCarte().getLargeur(); j++) {
                char caseContenu = partie.getCarte().getCase(i, j).getContenu();
                switch (caseContenu) {
                    case '@':
                        partie.ajouterPersonnage("perso", i, j);
                    case 'S': // Singe
                        Singe singe = (Singe) elements.creerAnimal(caseContenu, i, j);
                        partie.ajouterAnimal(singe);
                        break;
                    case 'V','D':
                        Predateur predateur =elements.creerPredateur(caseContenu,i,j,partie.getCarte());
                        partie.ajouterPredateur(predateur);
                        break;
                    case ' ':
                        break;
                    default:
                        Objet objet = elements.creerObjet(caseContenu, i, j);
                        partie.ajouterObjet(objet);
                        break;
                }
            }
        }
    }
    public void initialiserPartie(int choix, int choix2) {
        if (choix==1){
            getCarteExistente(35,100,choix2);
        }else {
            initialiserCarte(35, 100);
        }
        initialiserElements();
    }
    protected void getCarteExistente(int hauteur, int largeur, int choix) {
        partie.setCarte(new Carte(hauteur, largeur));
        try {
            if (choix ==1){
                partie.getCarte().chargerDepuisFichier("carte_jungle_danger.txt");
            } else
                partie.getCarte().chargerDepuisFichier("carte_jungle.txt");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte depuis le fichier : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}




