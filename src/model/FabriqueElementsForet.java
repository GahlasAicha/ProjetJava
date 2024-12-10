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
    public Objet creerObjet(char symbole,int x,int y) {
        switch (symbole) {
            case 'G': return new Objet("Gland", 'G', true,x,y);
            case 'A': return new Objet("Arbre", 'A', false,x,y);
            case 'B': return new Objet("Buisson", 'B', false,x,y);
            case 'C': return new Objet("Champignon", 'C', true,x,y);
            default: return null;
        }
    }

    @Override
    public char[] getSymbolesAutorises() {
        return new char[]{' ', 'A', 'B', 'G', 'C', 'E'};
    }
}
