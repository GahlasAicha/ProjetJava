package model;

import java.io.IOException;

public class FabriquePartieForet extends FabriqueAbstraitePartie {
    private FabriqueElementsForet elements;
    private Carte carte ;

    public FabriquePartieForet(int choix) {
        super();
        this.elements = new FabriqueElementsForet();
        initialiserPartie(choix);
    }

    public void initialiserPartie(int choix) {
        if (choix == 1) {
            getCarteExistente(35, 100);
        } else {
            initialiserCarte(35, 100);
        }
        initialiserElements();
    }


    @Override
    public Partie creerPartie(int choix, Personnage personnage) {
        // Crée une nouvelle partie Forêt, avec un personnage
        Partie partie = new Partie("Forêt", personnage);
        return partie;
    }
    @Override
    public Carte creerCarte(int largeur, int hauteur) {
        // Retourne une nouvelle carte (vous pouvez personnaliser davantage si nécessaire)
        return new Carte(largeur, hauteur);
    }

    protected void initialiserCarte(int hauteur, int largeur) {
        if (elements == null) {
            throw new IllegalStateException("L'attribut 'elements' n'a pas été initialisé !");
        }
        this.carte = new Carte(hauteur, largeur);
        this.carte.afficherCarte(elements.getSymbolesAutorises()); // Utilisation sécurisée


    }

    protected void getCarteExistente(int hauteur, int largeur) {
        this.carte = new Carte(hauteur, largeur);
        try {
            this.carte.chargerDepuisFichier("carte.txt");
            if (this.carte.getLargeur() != largeur || this.carte.getHauteur() != hauteur) {
                throw new IOException("La taille de la carte dans le fichier ne correspond pas aux dimensions attendues.");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte depuis le fichier : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialiserElements() {
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {

                char caseContenu = carte.getCase(x, y).getContenu();
                switch (caseContenu) {
                    case '@':// cree un nouveu personnage dans l'emplacement initialise
                        ajouterPersonnage("perso", x, y);
                    case 'E':
                        Ecureuil ecureuil = new Ecureuil(x, y);
                        ajouterAnimal(ecureuil,x,y);// ajouter les animeux dans la list
                        break;
                    case ' ':
                        break;
                    default:
                        Objet objet = elements.creerObjet(caseContenu, x, y);
                        ajouterObjet(objet,x,y);
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

                }
            }
        }
    }

    private void ajouterObjet(Objet objet, int x, int y) {
        carte.ajouterObjet(objet, x, y);
    }

    private void ajouterAnimal(Ecureuil ecureuil, int x, int y) {
        carte.ajouterAnimal(ecureuil, x, y);
    }

    private void ajouterPersonnage(String perso, int i, int j) {
        carte.ajouterPersonnage(perso, i, j);
    }

    public Partie getPartie() {
        return new Partie();
    }
    /*public void ramasserObjet(int x, int y) {// on peut le metre dans class partie
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
    }*/
}













