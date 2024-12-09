package main;
import controller.Controller;
import view.Ihm;
public class Main {





        public static void main(String[] args) {
            Ihm ihm=new Ihm();
            Controller controller=new Controller(ihm);
            controller.demarrerJeu();
        }















//   public static void main(String[] args) {
//        // Création de la carte avec quelques cases et un animal
//        Carte carte = new Carte(5, 5);
//        carte.setCaseContenu(2, 2, 'G');  // Gland à la position (2, 2)
//        carte.setCaseContenu(3, 3, 'C');  // Champignon à la position (3, 3)
//        carte.setCaseContenu(4, 4, 'B');  // Banane à la position (4, 4)
//
//        // Création d'un écureuil affamé à la position (1, 1)
//        Ecureuil animal = new Ecureuil("Écureuil", 1, 1);
//
//        // Affichage initial
//        System.out.println("Position initiale de " + animal.getNom() + " : (" + animal.getX() + ", " + animal.getY() + ")");
//
//        // Acte 1 : L'animal agit dans son état affamé
//        animal.agir(carte); // Il devrait chercher de la nourriture
//
//        // Affichage après l'action
//        System.out.println("Position après l'action : (" + animal.getX() + ", " + animal.getY() + ")");
//
//        // Acte 2 : L'animal se nourrit et devient rassasié
//        animal.seNourrir(); // L'animal se nourrit pour passer à l'état rassasié
//
//        // Affichage après la nourriture
//        System.out.println(animal.getNom() + " a maintenant l'état : Rassasié");
//        System.out.println("Position après la nourriture : (" + animal.getX() + ", " + animal.getY() + ")");
//    }
}
