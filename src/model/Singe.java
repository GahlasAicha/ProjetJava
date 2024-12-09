package model;

public class Singe extends Animal{
    public Singe(int positionX, int positionY) {
        super("Singe", 'S', positionX, positionY);
        setEtat(new EtatAffame(this)); // Par défaut, un singe commence affamé
    }


    public String afficherSinge() {
        return null;
    }

    @Override
    public void seNourrir() {
        etat.seNourrir();
    }

    @Override
    public void apprivoiser() {
     etat.apprivoiser();
    }

    @Override
    public void recevoirCoup() {
   etat.recevoirCoup();
    }

    @Override
    public void agir() {
      etat.agir();
    }
}
