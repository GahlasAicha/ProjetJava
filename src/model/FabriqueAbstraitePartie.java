package model;

public abstract class FabriqueAbstraitePartie { // 01/01/2025


    /**
     * Méthode abstraite pour initialiser la carte de la partie.
     *
     * @param hauteur La hauteur de la carte.
     * @param largeur La largeur de la carte.
     */
    public abstract void initialiserCarte(int hauteur, int largeur);

    /**
     * Méthode abstraite pour initialiser les éléments de la partie (objets, ennemis, etc.).
     */
    public abstract void initialiserElements();



}

