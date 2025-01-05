package model;

import util.Couleurs;

import java.util.List;
import java.util.Random;

public class Hibou extends Predateur{
    private boolean enRepos =false ;
     private boolean estAuSol = false ;// verifier si il s'est reposer ou pas

    public Hibou(int x, int y,Carte carte ) {
        super("Hibou",x, y,carte);

    }

    @Override
    public void deplacer() {
        if (enRepos) {
            System.out.println(Couleurs.colorerTexte("Le hibou est en repos et ne peut pas se déplacer ce tour.", Couleurs.ANSI_YELLOW));


            // on doit verifier la possibilité de contre attaque
            if (peutEtreContreAttaque()) {
                System.out.println(Couleurs.colorerTexte("Le personnage contre-attaque le hibou pendant son repos !", Couleurs.ANSI_CYAN));
            }
            enRepos = false; // Le hibou sort de l'état de repos au prochain tour
            estAuSol = false; // Il ne reste pas vulnérable après ce tour
            return;
        }
        // Deplacement aleatoire  dans des cases adjacentes
        Random random = new Random();
        int direction = random.nextInt(4); // Choix aléatoire parmi 4 directions

        // on  sauvegrade les coordonnéees avant le deplcanement
        int ancienneX= this.getX();
        int ancienneY = this.getY();
        switch (direction) {
            case 0: // Déplacement vers le haut
                this.setY(this.getY() - 2);
                break;
            case 1: // Déplacement vers le bas
                this.setY(this.getY() + 2);
                break;
            case 2: // Déplacement vers la gauche
                this.setX(this.getX() - 2);
                break;
            case 3: // Déplacement vers la droite
                this.setX(this.getX() + 2);
                break;
        }
        // Vérification si la nouvelle position est valide sur la carte
        if (!Predateur.estPositionValide(this.getX(), this.getY())) {
            // Si la position est invalide, revenir à la position précédente
            this.setX(ancienneX);
            this.setY(ancienneY);
            System.out.println("Le hibou a tenté de sortir de la carte, il revient à sa position initiale.");
        } else {
            System.out.println("Le hibou se déplace de 2 cases dans la direction " + direction);
        }
    }

    private boolean peutEtreContreAttaque() {
        // Récupérer les coordonnées du personnage via getPositionPersonnage()
        int[] positionPersonnage = carte.getPositionPersonnage();
        int personnageX = positionPersonnage[0];
        int personnageY = positionPersonnage[1];


        // Calcul de la distance entre le hibou et le personnage
        int distanceX = Math.abs(this.getX() - personnageX);
        int distanceY = Math.abs(this.getY() - personnageY);

        return distanceX <= 1 && distanceY <= 1; // Vérifie si le personnage est adjacent au hibou

    }


    @Override
    public void attaquer(Animal animal) {
        if (animal instanceof Ecureuil) {
            Ecureuil ecureuil = (Ecureuil) animal;
            // on doit verifier si l'ecureuil  est dans u  rayon de 3 cases
            if (ecureuil.estEffraye()) {
                System.out.println(nom + " ne peut pas attaquer, " + ecureuil.getNom() + " est déjà effrayé.");
                return; // L'écureuil est effrayé, l'attaque échoue
            }
            if (estDansRayonDe3Cases(ecureuil)) {
                // Vérifier si la case de l'écureuil est dégagée
                if (estCaseDegagee(ecureuil)) {

                    // Vérifier s'il y a un buisson dans une case adjacente à l'écureuil
                    if (aBuissonAdjacente(ecureuil)) {
                        System.out.println("Le hibou fait fuir l'écureuil dans un buisson !");
                        faireFuirDansBuisson(ecureuil);
                    } else {
                        System.out.println("Le hibou tue l'écureuil !");
                        carte.supprimerAnimal(ecureuil);
                    }
                    // apres une attaque le hibou il doit se reposer 
                    seReposer();

                } else {
                    System.out.println("La case de l'écureuil n'est pas dégagée, le hibou ne peut pas attaquer.");
                    ecureuil.devenirEffraye();
                }

            } else {
                System.out.println("L'écureuil est hors de portée du hibou.");
            }
        }
    }

    private void seReposer() {
        enRepos = true; // Active le mode repos pour un tour
        estAuSol = true; // Met le hibou en état de repos
        String message = Couleurs.colorerTexte("Le hibou se repose au sol et devient vulnérable pour un tour.", Couleurs.ANSI_YELLOW);
        System.out.println(message);
    }



    private void faireFuirDansBuisson(Ecureuil ecureuil) {
        // Obtenir les coordonnées de l'écureuil
        int x = ecureuil.getX();
        int y = ecureuil.getY();


        // Obtenir la grille depuis l'objet carte
        Case[][] grille = carte.getGrille();

        // Obtenir les cases adjacentes
        List<int[]> casesAdjacentes = Carte.obtenirCasesAdjacentes(x, y, carte.getHauteur(), carte.getLargeur());
        // Parcourir les cases adjacentes pour trouver un buisson
        for (int[] coordonnees : casesAdjacentes) {
            int adjX = coordonnees[0];
            int adjY = coordonnees[1];

            // Vérifier si la case adjacente contient un buisson ('B')
            if (grille[adjX][adjY].getContenu() == 'B') {
                // Déplacer l'écureuil vers cette case
                ecureuil.setX(adjX);
                ecureuil.setY(adjY);

                System.out.println("L'écureuil a fui vers un buisson en (" + adjX + ", " + adjY + ").");
                return; // Arrêter la recherche une fois le buisson trouvé
            }
        }

        // Aucun buisson trouvé
        System.out.println("Aucun buisson adjacent n'est disponible pour l'écureuil !");
    }

    private boolean aBuissonAdjacente(Ecureuil ecureuil) {
        int x = ecureuil.getX();
        int y = ecureuil.getY();
        // Obtenir la grille depuis l'objet carte
        Case[][] grille = carte.getGrille();

        List<int[]> casesAdjacentes = Carte.obtenirCasesAdjacentes(x,y, carte.getHauteur(), carte.getLargeur());
        // Vérifier chaque case adjacente
        for (int[] coordonnees : casesAdjacentes) {
            int adjX = coordonnees[0];
            int adjY = coordonnees[1];

            // Vérifier si la case adjacente contient un buisson ('B')
            if (grille[adjX][adjY].getContenu() == 'B') {
                return true; // Un buisson est adjacent
            }
        }

        return false; // Aucun buisson adjacent trouvé
    }

    private boolean estDansRayonDe3Cases(Animal animal) {
        int distanceX = Math.abs(this.getX() - animal.getX());
        int distanceY = Math.abs(this.getY() - animal.getY());
        return distanceX <= 3 && distanceY <= 3;
    }
    private boolean estCaseDegagee(Ecureuil ecureuil) {
        Case caseEcureuil = carte.getCase(ecureuil.getX(), ecureuil.getY());
        return !caseEcureuil.contientArbre() && !caseEcureuil.contientBuisson();
    }
    public String getNom(){
        return "Hibou";
    }

}



