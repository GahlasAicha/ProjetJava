package model;

public class FabriqueElementsJungleDanger extends FabriqueElementsJungle{

    public Animal creerAnimal(char symbole, int x, int y) {
        switch (symbole){
            case 'V':
            case 'D':
            default: return super.creerAnimal(symbole,x,y);

        }

    }
    public Objet creerObjet(char symbole, int x, int y) {
        if (symbole == 'L') {
            return new Objet("Champignon hallucinogène", 'L', true, x, y);
        }
        return super.creerObjet(symbole, x, y);
    }
    public char[] getSymbolesAutorises() {
        char[] c = super.getSymbolesAutorises();
        char[] s = new char[c.length +3];
        System.arraycopy(c,0,s,0,c.length);
        s[c.length -1]= 'V';
        s[c.length]='D';
        s[c.length+1]='L';
        return s;
    }
}
