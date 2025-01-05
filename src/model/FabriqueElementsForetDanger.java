package model;

public class FabriqueElementsForetDanger extends FabriqueElementsForet{
    @Override
    public Animal creerAnimal(char symbole, int x, int y) {
        switch (symbole){
            case 'R':
            case 'H':
            default: return super.creerAnimal(symbole,x,y);
        }
    }

    @Override
    public Objet creerObjet(char symbole, int x, int y) {
        if (symbole == 'M') {
            return new Objet("Champignon vénéneux", 'M', true, x, y);
        }
        return super.creerObjet(symbole, x, y);
    }

    @Override
    public char[] getSymbolesAutorises() {
        char[] c = super.getSymbolesAutorises();
        char[] s = new char[c.length +3];
        System.arraycopy(c,0,s,0,c.length);
        s[c.length -1]= 'R';
        s[c.length]='H';
        s[c.length+1]='M';
        return s;

    }
}
