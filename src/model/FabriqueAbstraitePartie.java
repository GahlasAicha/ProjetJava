package model;

/**
 * Classe abstraite représentant une fabrique pour créer des parties du jeu.
 */
public abstract class FabriqueAbstraitePartie {

    /**
     * Méthode abstraite pour initialiser une partie.
     * @param choix Le choix de l'utilisateur (nouvelle ou existante).
     * @param personnage Le personnage principal de la partie.
     * @return Une instance de la classe Partie correspondant à la fabrique spécifique.
     */
    public abstract Partie creerPartie(int choix, Personnage personnage);

    /**
     * Méthode abstraite pour initialiser la carte de la partie.
     * @param hauteur La hauteur de la carte.
     * @param largeur La largeur de la carte.
     * @return Une instance de la carte.
     */
    public abstract Carte creerCarte(int hauteur, int largeur);

    /**
     * Méthode abstraite pour initialiser les éléments de la partie (objets, ennemis, etc.).
     */
    public abstract void initialiserElements();



}

