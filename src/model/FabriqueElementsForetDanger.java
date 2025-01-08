package model;

public class FabriqueElementsForetDanger extends FabriqueElementsForet{
    @Override
    public Animal creerAnimal(char symbole, int x, int y) {
        return super.creerAnimal(symbole,x,y);

    }

    @Override
    public Predateur creerPredateur(char symbole, int x, int y, Carte carte) {
        if (symbole=='R'){
            return new Renard(x, y, carte);
        }else return new Hibou(x, y, carte);
    }

    @Override
    public Objet creerObjet(char symbole, int x, int y) {
        if (symbole == 'M') {
            return new Objet("Champignon vénéneux", 'M', true, x, y);
        } else
            return super.creerObjet(symbole, x, y);
    }

    @Override
    public char[] getSymbolesAutorises() {
        char[] c = super.getSymbolesAutorises();
        char[] s = new char[c.length +3];
        System.arraycopy(c,0,s,0,c.length);
        s[c.length]= 'R';
        s[c.length+1]='H';
        s[c.length+2]='M';
        return s;

    }
}
