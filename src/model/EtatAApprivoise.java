package model;

public class EtatAApprivoise extends EtatAnimal {
    public EtatAApprivoise(Animal animal) {
        super(animal);
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
    public void agir() {

    }

    @Override
    public void Senourrir(Animal animal) {

    }


    public void nourrir(Animal animal) {
        System.out.println(animal.getNom() + " apprécie votre attention, mais il est déjà votre ami.");
    }

    @Override
    public void apprivoiserAnimal(Animal animal) {
        System.out.println(animal.getNom() + " est déjà votre ami.");

    }

    @Override
    public void recevoirCoup(Animal animal) {
        System.out.println(animal.getNom() + " est blessé et ne vous fait plus confiance.");
        animal.setEtat(new EtatAffame(animal));
    }

    @Override
    public void agir(Animal animal) {
        System.out.println(animal.getNom() + " vous suit et vous avertit des dangers.");
    }
}
