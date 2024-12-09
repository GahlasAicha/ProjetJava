package model;

import java.io.IOException;

public class PartieForet extends Partie{
    private Ecureuil[][] ecureuils ;// matrice pour pouvoir stocker les ecur
    private  Objet[][] objets;
    public PartieForet() {
        super();
    }

    @Override
    public void initialiserPartie() {
        initialiserCarte(100,35);
        initialiserElements();
    }



    @Override
    protected void initialiserCarte(int hauteur, int largeur) {
        this.carte= new Carte(hauteur,largeur);
        ecureuils = new Ecureuil[hauteur][largeur];
        objets= new Objet[hauteur][largeur];
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
                if (i >= 0 && i < ecureuils.length && j >= 0 && j < ecureuils[i].length) {
                    char caseContenu = carte.getCase(i,j).getContenu();
                    switch (caseContenu) {
                        case '@':// cree un nouveu personnage dans l'emplacement initialise
                            setPersonnage(new Personnage("fiter",i,j));
                        case 'E':
                            Ecureuil ecureuil = new Ecureuil(i, j);
                            ajouterAnimal(ecureuil);// ajouter les animeux dans la list
                            // ecureuils[i][j] = ecureuil; // la j'ai ajouter l'ecureuil a la liste des ecureuils
                            break;
                        case 'G':  // Gland
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
                            break;
                    }
                }
            }
        }
    }

    public void setPersonnage(Personnage fiter) {
    }

    @Override
    public void rammaserObjet(int x, int y) {
        if (objets[x][y] != null) {
            Objet objet = objets[x][y];
            if (objet.isEstRamassable()) {
                System.out.println("Vous pouvez ramasser l'objet " + objet.getNom() + " !");
                objets[x][y] = null;
            } else {
                System.out.println("Cet objet n'est pas ramassable.");
            }
        } else {
            System.out.println("Il n'y a rien à ramasser ici.");
        }
    }}






