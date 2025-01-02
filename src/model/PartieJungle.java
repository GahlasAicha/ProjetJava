package model;

import java.io.IOException;

public class PartieJungle extends Partie{
    private Singe[][] singes ;
    private Objet[][] objets;
    private FabriqueElementsJungle elements;

    public PartieJungle(int choix) {
        super();
        elements= new FabriqueElementsJungle();
        initialiserPartie(choix);
    }



    public void initialiserPartie(int choix) {
        if (choix==1){
            getCarteExistente(10,32);
        }else if(choix==2) {
            initialiserCarte(35, 100);
        }
        initialiserElements();
    }




    protected void initialiserCarte(int hauteur, int largeur) {
        this.carte = new Carte(hauteur,largeur);
        objets = new Objet[hauteur][largeur];
        singes = new Singe[hauteur][largeur];
        this.carte.afficherCarte(elements.getSymbolesAutorises());

    }


    protected void getCarteExistente(int hauteur, int largeur) {
        this.carte = new Carte(hauteur,largeur);
        objets = new Objet[hauteur][largeur];
        singes = new Singe[hauteur][largeur];
        try {
            this.carte.chargerDepuisFichier("carte_jungle.txt"); // Le fichier de carte pour la jungle
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte depuis le fichier : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    protected void initialiserElements() {
        for (int i = 0; i < carte.getHauteur(); i++) {
            for (int j = 0; j < carte.getLargeur(); j++) {
                char caseContenu = carte.getCase(i, j).getContenu();
                switch (caseContenu) {
                    case '@':
                        ajouterPersonnage("perso",i,j);
                    case 'S': // Singe
                        Singe singe = (Singe) elements.creerAnimal(caseContenu,i,j);
                        ajouterAnimal(singe);
                        break;
                    /*case 'B': // Banane
                        ajouterObjet(new Objet("Banane", 'B', true)); // Ajouter une banane à cet endroit
                        break;
                    case 'C': // Champignon
                        ajouterObjet(new Objet("Champignon", 'C', true)); // Ajouter un champignon
                        break;
                    case 'P': // Petit rocher
                        ajouterObjet(new Objet("Petit rocher", 'P', false)); // Ajouter un rocher
                        break;
                    case 'T': // Cocotier
                        ajouterObjet(new Objet("Cocotier", 'T', false)); // Ajouter un cocotier
                        break;*/
                    case ' ':
                        break;
                    default:
                        Objet objet=elements.creerObjet(caseContenu,i,j);
                        ajouterObjet(objet);
                        break;
                }
            }
        }
    }




    public void ramasserObjet(int x, int y) {
        if (getPersonnage().estEnface(x,y)) {
            Objet objet = getObjets(x,y);
            if (objet!=null) {
                if (objet.isEstRamassable()) {
                    getPersonnage().ramasserObjet(objet);
                    enleverObjet(objet);

                    getCarte().setCaseContenu(x, y, ' ');

                } else {
                    System.out.println("Cet objet n'est pas ramassable.");
                }
            }
        } else {
            System.out.println("Il n'y a rien à ramasser ici.");
        }
    }
}

