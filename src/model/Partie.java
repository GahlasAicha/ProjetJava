package model;
// on va utiliser le design pattern factory pour la classe factory ( creer des instances specifiques comme partie jungle et partie foret )
import java.util.ArrayList;

public abstract class Partie {
    protected Carte carte;
    private ArrayList<Animal> animaux;
    private ArrayList<Objet> objets;
    private boolean estEnCours;
    private String statut;// statut de la partie
    private Personnage personnage;
    private int tours; // compter tours


    public Partie() {
        this.personnage=new Personnage("surviver",2,2);
        this.animaux = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.estEnCours = true;
        this.statut = "En cours ";
        initialiserPartie();
    }
    public void ajouterPersonnage(String nom, int x, int y) {
        if (carte.getCase(x, y).getContenu() == '.') { // Vérifiez si la case est vide
            Personnage personnage = new Personnage(nom, x, y);
            this.personnage=personnage;
            carte.setCaseContenu(x, y, '@'); // Placez le personnage sur la carte
        } else {
            System.out.println("Impossible de placer le personnage : case occupée.");
        }
    }

    public abstract void initialiserPartie();

    public abstract void rammaserObjet(int x, int y);

    protected abstract void initialiserCarte(int hauteur, int largeur);

    protected abstract void initialiserElements();

    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    public int getTours() {
        return tours;
    }

    public void setTours(int tours) {
        this.tours = tours;
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

    public ArrayList<Objet> getObjets() {
        return objets;
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
            if (caseContenu == '.') {
                carte.setCaseContenu(personnage.getX(), personnage.getY(), '.'); // Supprimer l'ancien emplacement
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
        animaux.add(animal);
    }

    public void ajouterObjet(Objet objet) {
        objets.add(objet);
    }

    // Méthode pour afficher l'état actuel de la partie (par exemple, carte, objets, animaux)
    public void afficherEtat() {
        //carte.afficherCarte();
        System.out.println("Animaux : " + animaux.size());
        System.out.println("Objets : " + objets.size());
        System.out.println("Statut : " + statut);
    }

    // Méthode pour terminer la partie
    public void terminerPartie() {
        this.estEnCours = false;
        this.statut = "Terminée";
    }

}