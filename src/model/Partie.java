package model;
// on va utiliser le design pattern abstraite factory pour la classe factory ( creer des instances specifiques comme partie jungle et partie foret )
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe abstraite représentant une partie du jeu.
 */
public class Partie {
    protected Carte carte;
    private ArrayList<Animal> animaux;
    private Personnage personnage;
    private ArrayList<Objet> objets;
    private boolean estEnCours;
   private String theme ;
    private String statut;
    private int tours;
    private int[] grille;

    // Constructeur
    public Partie() {
        this.animaux = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.estEnCours = true;
        this.statut="En cours ";

    }

    public Partie(String theme, Personnage personnage) {
        this.theme = theme;
        this.personnage = personnage;
    }



    // Méthode pour ajouter un personnage sur la carte
  /*  public void ajouterPersonnage(String nom, int x, int y) {
        if (carte.getCase(x, y).getContenu() == ' ') {
            this.personnage = new Personnage(nom, x, y);
            carte.setCaseContenu(x, y, '@');  // Place le personnage sur la carte
        } else {
            System.out.println("Impossible de placer le personnage : case occupée.");
        }
    }*/
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



    public void lancerObjet (int index, String direction){
        int nouvelleX = personnage.getX();
        int nouvelleY = personnage.getY();
        // Déterminer la nouvelle position en fonction de la direction
        switch (direction) {
            case "haut":
                nouvelleX -= 1;
                break;
            case "bas":
                nouvelleX += 1;
                break;
            case "gauche":
                nouvelleY -= 1;
                break;
            case "droite":
                nouvelleY += 1;
                break;
            default:
                System.out.println("Direction non valide.");
                return;
        }
        // Vérifier si la case dans la direction indiquée est vide
        if (carte.getCase(nouvelleX, nouvelleY).getContenu() == ' ') {
            Objet objet = personnage.reposerObjet(index); // Prendre l'objet du personnage
            objets.add(objet); // Ajouter l'objet à la liste des objets
            objet.setPosition(nouvelleX, nouvelleY); // Mettre à jour la position de l'objet
            carte.setCaseContenu(nouvelleX, nouvelleY, objet.getSymbole()); // Placer l'objet sur la carte
            System.out.println("L'objet a été lancé à la position (" + nouvelleX + ", " + nouvelleY + ")");
        } else {
            System.out.println("La case est occupée. Impossible de lancer l'objet.");
        }
    }

    public void jeterObjet(int index, String direction) {
            Scanner scanner = new Scanner(System.in);


            // Calcul des nouvelles coordonnées en fonction de la direction
            int nouvelleX = personnage.getX();
            int nouvelleY = personnage.getY();

            switch (direction) {
                case "haut":
                    nouvelleX -= 1;  // Déplacement vers le haut
                    break;
                case "bas":
                    nouvelleX += 1;  // Déplacement vers le bas
                    break;
                case "gauche":
                    nouvelleY -= 1;  // Déplacement vers la gauche
                    break;
                case "droite":
                    nouvelleY += 1;  // Déplacement vers la droite
                    break;
                default:
                    System.out.println("Direction invalide, réessayez.");
                    return;  // Quitter si la direction n'est pas valide
            }

            // Vérification si la case est vide avant de jeter l'objet
            if (carte.getCase(nouvelleX, nouvelleY).getContenu() == ' ') {
                // Le personnage jette l'objet
                Objet objet = personnage.jeterObjet(index);
                objets.add(objet);  // Ajouter l'objet dans la liste des objets

                // Mettre à jour la carte avec la nouvelle position de l'objet
                carte.setCaseContenu(nouvelleX, nouvelleY, objet.getSymbole());

                System.out.println("L'objet a été jeté dans la direction " + direction + " à la position (" + nouvelleX + ", " + nouvelleY + ").");
            } else {
                System.out.println("La case " + direction + " est occupée, vous ne pouvez pas jeter l'objet ici.");
            }
        }
    // Méthode pour déplacer le personnage avec gestion des exceptions

    // Méthode pour déplacer le personnage avec gestion des exceptions
    public void deplacerPersonnage(int dX, int dY) {
        int nouvelleX = personnage.getX() + dX;
        int nouvelleY = personnage.getY() + dY;

        try {
            // Vérifier si la position est valide (dans les limites de la carte)
            if (!isPositionValide(nouvelleX, nouvelleY)) {
                throw new DeplacementHorsLimitesException("La nouvelle position est hors des limites de la carte !");
            }

            // Vérifier si la case est occupée
            char caseContenu = carte.getCase(nouvelleX, nouvelleY).getContenu();
            if (caseContenu != ' ') {
                System.out.println("La case est occupée par un autre objet ou animal.");
                return;
            }

            // Supprimer l'ancien emplacement du personnage
            carte.setCaseContenu(personnage.getX(), personnage.getY(), ' ');

            // Déplacer le personnage à la nouvelle position
            personnage.seDeplacer(dX, dY);

            // Ajouter le personnage à la nouvelle position
            carte.setCaseContenu(nouvelleX, nouvelleY, '@');
        } catch (DeplacementHorsLimitesException e) {
            System.out.println(e.getMessage());  // Affiche l'erreur si la position est hors limites
        }
    }

    // Méthode pour vérifier si la position est dans les limites de la carte
    private boolean isPositionValide(int x, int y) {
        return x >= 0 && x < carte.getHauteur() && y >= 0 && y < carte.getLargeur();
    }
    // Méthode pour initialiser la carte avec animaux et objets
    public void ajouterAnimal(Animal animal) {
        animaux.add(animal);
    }

    public void ajouterObjet(Objet objet) {
        objets.add(objet);
    }

    public void enleverObjet(Objet objet) {
        objets.remove(objet);
    }

    // Méthode pour obtenir un objet à une position donnée
    public Objet getObjets(int x, int y) {
        for (Objet objet : objets) {
            if (objet.getX() == x && objet.getY() == y) {
                return objet;
            }
        }
        return null;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public Carte getCarte() {
        return carte;
    }

    public boolean isEstEnCours() {
        return estEnCours;
    }

    public void setEstEnCours(boolean estEnCours) {
        this.estEnCours = estEnCours;
    }
    // Méthode pour vérifier si la position est dans les limites de la carte

    public void terminerPartie() {
        this.estEnCours = false;
        this.statut = "Terminée";
    }

    public String getTheme() {
        return theme;
    }
    public int getTours() {
        return tours;
    }

    public void setTours(int tours) {
        this.tours = tours;
    }


    public Partie getPartie() {
        return this;
    }


}
