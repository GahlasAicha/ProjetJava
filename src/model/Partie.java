package model;
// on va utiliser le design pattern factory pour la classe factory ( creer des instances specifiques comme partie jungle et partie foret )
import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
    protected Carte carte;
    private ArrayList<Animal> animaux;
    private ArrayList<Objet> objets;
    private boolean estEnCours;
    private String statut;// statut de la partie
    private Personnage personnage;
    private int tours; // compter tours
    private ArrayList<Predateur> predateurs;



    public Partie() {
        this.personnage = new Personnage("surviver", 2, 2);
        this.animaux = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.estEnCours = true;
        this.statut = "En cours ";
        this.predateurs= new ArrayList<>();

        //initialiserPartie();

    }

    public void ajouterPersonnage(String nom, int x, int y) {
        if (carte.getCase(x, y).getContenu() == '@') {
            this.personnage = new Personnage(nom, x, y);
            carte.setCaseContenu(x, y, '@'); // Placez le personnage sur la carte
        } else {
            System.out.println("Impossible de placer le personnage : case occupée.");
        }
    }


    public void ramasserObjet(int x, int y) {// on peut le metre dans class partie
        if (personnage.estEnface(x, y)) {
            Objet objet = getObjets(x, y);
            if (objet != null) {
                if (objet.isEstRamassable()) {
                    personnage.ramasserObjet(objet);
                    enleverObjet(objet);
                    carte.setCaseContenu(x, y, ' ');

                } else {
                    System.out.println("Cet objet n'est pas ramassable.");
                }
            } else {
                System.out.println("Il n'y a rien à ramasser ici.");
            }
        } else {
            System.out.println("Il n'y a rien à ramasser ici.");
        }
    }

    public void lancerObjet(int index, String direction) {
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


    public Personnage getPersonnage() {
        return personnage;
    }


    public int getTours() {
        return tours;
    }

    public void setTours(int tours) {
        this.tours = tours;
        // Nettoyage de la liste pour retirer les objets null
        animaux.removeIf(animal -> animal == null);

        for (Animal animal : animaux) {
            try {
                animal.getEtat().setAnimal(animal);
                animal.agir(carte);
                attaquePredateur(animal);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'action de " + (animal != null ? animal.getNom() : "animal null") + ": " + e.getMessage());
            }
        }

        for (Predateur predateur : predateurs) {
            if (predateur!=null) {
                System.out.println("predareeur bouge");
                predateur.deplacer();
            }
        }

    }

    private void attaquePredateur(Animal animal){

        for (Predateur predateur:predateurs){
            if (predateur!=null) {
                predateur.attaquer(animal);
            }
        }
    }


    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public ArrayList<Animal> getAnimaux() {
        return animaux;
    }

    public void setAnimaux(ArrayList<Animal> animaux) {
        this.animaux = animaux;
    }

    public Objet getObjets(int x, int y) {
        if (!objets.isEmpty()) {
            for (Objet objet : objets) {
                if (objet != null) {
                    if (objet.getX() == x && objet.getY() == y) {
                        return objet;
                    }
                }
            }
        } else {
            System.out.println("vous n'avez pas b'objets a disposition");
        }
        return null;

    }

    public void setObjets(ArrayList<Objet> objets) {
        this.objets = objets;
    }

    public boolean isEstEnCours() {
        return estEnCours;
    }

    public void setEstEnCours(boolean estEnCours) {
        this.estEnCours = estEnCours;
    }
    // Getter et Setter pour l'état de la partie (en cours ou terminée)

    //les methodes
    public void deplacerPersonage(int dX, int dY) {
        int nouvelleX = personnage.getX() + dX;
        int nouvelleY = personnage.getY() + dY;

        // Vérifier si le déplacement est dans les limites de la carte
        if (nouvelleX >= 0 && nouvelleX < carte.getHauteur() && nouvelleY >= 0 && nouvelleY < carte.getLargeur()) {
            // Vérifier si la case n'est pas déjà occupée
            char caseContenu = carte.getCase(nouvelleX, nouvelleY).getContenu();
            if (caseContenu == ' ') {
                carte.setCaseContenu(personnage.getX(), personnage.getY(), ' '); // Supprimer l'ancien emplacement
                personnage.seDeplacer(dX, dY);
                carte.setCaseContenu(nouvelleX, nouvelleY, '@'); // Ajouter le personnage à la nouvelle position
            } else {
                System.out.println("La case est occupée par un autre objet ou animal.");
            }
        } else {
            System.out.println("Déplacement hors limites !");
        }
    }


    // Méthode pour initialiser les objets et animaux de la partie
    public void ajouterAnimal(Animal animal) {
        if (!animaux.contains(animal)) {
            animaux.add(animal);
        }
    }
    public void ajouterPredateur(Predateur predateur){predateurs.add(predateur);}

    public void ajouterObjet(Objet objet) {
        objets.add(objet);
    }

    public void enleverObjet(Objet objet) {
        objets.remove(objet);
    }

    // Méthode pour que le personnage frappe un animal
    public void frapperAnimal(Personnage personnage, Animal animal) {
        // Vérifier si l'animal est dans la liste des animaux de la partie
        if (!animaux.contains(animal)) {
            System.out.println("L'animal n'est pas dans la partie.");
            return;
        }

        // Vérifier si l'animal est proche du personnage
        if (estProche(personnage, animal)) {
            System.out.println(personnage.getNom() + " frappe " + animal.getNom() + "!");

            // Appel à la méthode recevoirCoup de l'animal
            animal.recevoirCoup();

            // Réagir si l'animal perd son amitié
            if (!animal.isAmi()) {
                System.out.println(animal.getNom() + " n'est plus ami avec " + personnage.getNom() + ".");
            }
        } else {
            System.out.println(animal.getNom() + " est trop loin pour être frappé.");
        }
    }

    // Méthode pour vérifier si l'animal est proche du personnage
    public boolean estProche(Personnage personnage, Animal animal) {
        // Comparaison des positions (x, y) du personnage et de l'animal
        return (Math.abs(personnage.getX() - animal.getX()) <= 1 && Math.abs(personnage.getY() - animal.getY()) <= 1);
    }

    public void checkAmitiePersonnage(Carte carte) {
        for (Animal animal : animaux) {
            animal.checkAmitie(carte);
        }
    }
    public Animal getAnimalProche(Personnage personnage) {
        for (Animal animal : animaux) {
            // Vérifier si l'animal est proche du personnage
            if (estProche(personnage, animal)) {
                return animal; // Retourne l'animal qui est proche
            }
        }
        return null; // Aucun animal n'est proche
    }
}