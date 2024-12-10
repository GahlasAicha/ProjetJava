package main;

import controller.Controller;
import view.Ihm;

public class Main {
    public static void main(String[] args) {
        Ihm ihm=new Ihm();
        Controller controller=new Controller(ihm);
        controller.demarrerJeu();
    }
}

