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
    public Objet creerObjet(char symbole) {
        switch (symbole) {
            case 'B': return new Objet("Banane", 'B', true);
            case 'C': return new Objet("Champignon", 'C', true);
            case 'P': return new Objet("Petit rocher", 'P', false);
            case 'T': return new Objet("Cocotier", 'T', false);
            default: return null;
        }
    }

    @Override
    public char[] getSymbolesAutorises() {
        return new char[]{' ', 'B', 'C', 'P', 'T', 'S'};
    }
}
