package model;

import java.io.IOException;

public class PartieForet extends Partie{
    FabriqueElementsForet elements;
    public PartieForet(int choix) {
        super();
        this.elements=new FabriqueElementsForet();
        initialiserPartie(choix);
    }

    @Override
    public void initialiserPartie(int choix) {
        if (choix==1){
            getCarteExistente(35,100);
        }else {
            initialiserCarte(35, 100);
        }
        initialiserElements();
    }



    @Override
    protected void initialiserCarte(int hauteur, int largeur) {
        if (elements == null) {
            throw new IllegalStateException("L'attribut 'elements' n'a pas été initialisé !");
        }
        this.carte = new Carte(hauteur, largeur);
        this.carte.afficherCarte(elements.getSymbolesAutorises()); // Utilisation sécurisée


    }

    @Override
    protected void getCarteExistente(int hauteur, int largeur) {
        this.carte = new Carte(hauteur, largeur);
        try {
            this.carte.chargerDepuisFichier("carte.txt");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte depuis le fichier : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void initialiserElements() {
        for (int i =0 ; i< carte.getHauteur(); i++){
            for (int j = 0 ; j< carte.getLargeur();j++){

                char caseContenu = carte.getCase(i,j).getContenu();
                switch (caseContenu) {
                    case '@':// cree un nouveu personnage dans l'emplacement initialise
                        ajouterPersonnage("perso",i,j);
                    case 'E':
                        Ecureuil ecureuil = new Ecureuil(i, j);
                        ajouterAnimal(ecureuil);// ajouter les animeux dans la list
                        break;
                        /*case 'G':  // Gland
                            Objet gland= new Objet("Gland", 'G', true);
                            ajouterObjet(gland);//ajouter les objet
                            break;
                        case 'A':  // Arbre
                            Objet arbre= new Objet("Arbre", 'A', false);
                            ajouterObjet(arbre);
                            break;
                        case 'B':  // Buisson
                            // Initialiser un buisson
                            Objet buisson= new Objet("Buisson", 'B', false);
                            ajouterObjet(buisson);
                            break;
                        case 'C':  // Champignon
                            Objet champignon = new Objet("Champignon", 'C', true);
                            ajouterObjet(champignon);
                            break;
                        default: // Zone vide
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


    @Override
    public void ramasserObjet(int x, int y) {// on peut le metre dans class partie
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
            }else {
                System.out.println("Il n'y a rien à ramasser ici.");
            }
        } else {
            System.out.println("Il n'y a rien à ramasser ici.");
        }
    }
}









