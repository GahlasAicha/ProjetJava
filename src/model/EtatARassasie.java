package model;

public class EtatARassasie extends EtatAnimal {
    private static EtatARassasie instance ;
    private  EtatARassasie() {}

    public static EtatARassasie getInstance() {
        if (instance == null) {
            instance = new EtatARassasie();
        }
        return instance;
    }


    @Override
    public void seNourrir() {

    }

    @Override
    public void apprivoiser() {

    }

    @Override
    public void recevoirCoup() {

    }

    @Override
    public void agir(Carte carte) {

    }

}
