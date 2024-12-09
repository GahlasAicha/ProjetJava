package model;

public class FabriqueElementsForet extends FabriqueElements {
    @Override
    public Animal creerAnimal(char symbole, int x, int y) {
        if (symbole == 'E') {
            return new Ecureuil(x, y);
        }
        return null;

    }

    @Override
    public Objet creerObjet(char symbole) {
        switch (symbole) {
            case 'G': return new Objet("Gland", 'G', true);
            case 'A': return new Objet("Arbre", 'A', false);
            case 'B': return new Objet("Buisson", 'B', false);
            case 'C': return new Objet("Champignon", 'C', true);
            default: return null;
        }
    }

    @Override
    public char[] getSymbolesAutorises() {
        return new char[]{' ', 'A', 'B', 'G', 'C', 'E'};
    }
}
