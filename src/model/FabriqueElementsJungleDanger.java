package model;

public class FabriqueElementsJungleDanger extends FabriqueElementsJungle{

    public Animal creerAnimal(char symbole, int x, int y) {
        return super.creerAnimal(symbole,x,y);

    }

    @Override
    public Predateur creerPredateur(char symbole, int x, int y, Carte carte) {
        if (symbole== 'V'){
            return new Serpent("serpent vipere",x,y,carte);
        }else return new Scorpion("Deon scorpion",x,y,carte);
    }

    public Objet creerObjet(char symbole, int x, int y) {
        if (symbole == 'L') {
            return new Objet("Champignon hallucinogÃ¨ne", 'L', true, x, y);
        }else
            return super.creerObjet(symbole, x, y);
    }
    public char[] getSymbolesAutorises() {
        char[] c = super.getSymbolesAutorises();
        char[] s = new char[c.length +3];
        System.arraycopy(c,0,s,0,c.length);
        s[c.length]= 'V';
        s[c.length+1]='D';
        s[c.length+2]='L';
        return s;
    }
}
