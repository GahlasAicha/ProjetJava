package model;

public class FabriqueElementsJungle extends FabriqueElements{

    @Override
    public Animal creerAnimal(char symbole, int x, int y) {
        if (symbole == 'S') {
            return new Singe(x, y);
        }
        return null;
    }

    @Override
    public Predateur creerPredateur(char symbole, int x, int y, Carte carte) {
        return null;
    }

    @Override
    public Objet creerObjet(char symbole, int x, int y) {
        switch (symbole) {
            case 'b': return new Objet("Banane", 'b', true,x,y);
            case 'C': return new Objet("Champignon", 'C', true,x,y);
            case 'P': return new Objet("Petit rocher", 'P', false,x,y);
            case 'T': return new Objet("Cocotier", 'T', false,x,y);
            default: return null;
        }
    }

    @Override
    public char[] getSymbolesAutorises() {
        return new char[]{' ', 'b', 'C', 'P', 'T', 'S'};
    }
}