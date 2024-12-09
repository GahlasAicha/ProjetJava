package main;

import model.Ecureuil;

public class Main {
    public static void main(String[] args) {
        Ecureuil ecureuil = new Ecureuil(5, 5);
        System.out.println(ecureuil.afficherEcureuil());

        // Nourrir l'écureuil et voir s'il devient rassasié
        ecureuil.seNourrir();
        System.out.println(ecureuil.afficherEcureuil());

        // Le comportement de l'écureuil après avoir mangé
        ecureuil.agir();
    }
}
