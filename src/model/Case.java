package model;

public class Case {
    private int x,y;
    private char contenu; // si la case est vide c le char .  si c  c un symbole nah le symbole
    private boolean estOccupee;
    private Animal animal;
    private static Objet objet ;
    private boolean contientArbre;
    /**
     * Constructeur pour créer une nouvelle case.
     * @param x La coordonnée X de la case.
     * @param y La coordonnée Y de la case.
     * @param contenu Le contenu initial de la case ('.' si vide, autre symbole sinon).
     */
    public Case(int x, int y,char contenu){
        this.x=x;
        this.y=y;
        this.contenu=contenu;
        this.estOccupee= contenu != '.'; // Si un objet est présent, la case est occupée
    }

    // les getters et setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getContenu() {
        return contenu;
    }

   /*public static void validerCoordonnees(int x, int y) {
      //  if (x < 0 || y < 0) {
          //  throw new IllegalArgumentException("Les coordonnées doivent être positives.");
       // }
    }*/

    public void setContenu(char contenu) {

        this.contenu = contenu;
        this.estOccupee= contenu !='.'; // mettre a jour l'etat occupée de la case
    }

    public boolean isEstOccupee() {
        return estOccupee;
    }

    public void setEstOccupee(boolean estOccupee) {
        this.estOccupee = estOccupee;
    }

    /**
     * Réinitialise la case à son état vide.
     */
    public void viderCase() {
        this.contenu = ' ';
        this.estOccupee = false;
    }

    @Override
    public String toString() {
        return "Case{" +
                "x=" + x +
                ", y=" + y +
                ", contenu=" + contenu +
                ", estOccupee=" + estOccupee +
                '}';
    }



    public Animal getAnimal() {
        return animal ;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
        this.estOccupee = (animal != null); // Si un animal est associé à cette case, elle est occupée
    }

    public Objet getObjet() {
        return objet ;
    }
    public void setObjet(Objet objet) {
        this.objet = objet;
    }
    // Méthode pour vérifier si la case contient un arbre
    public boolean contientArbre() {
        return objet != null && objet.getSymbole() == 'A';  // Vérifier si l'objet dans la case est un arbre
    }

    public boolean contientBuisson() {
        return objet != null && objet.getSymbole()=='B';
    }
    public boolean contientCocotier() {
        return objet != null && objet.getSymbole()=='C';
    }
    public static boolean estSousRocher(Case caseSinge) {
        // Vérifie si la case du singe contient un rocher
        return caseSinge != null && caseSinge.contientRocher();
    }

    public boolean contientRocher() {
        // Vérifie si la case contient un objet de type rocher (symbole 'P')
        return objet != null && objet.getSymbole() == 'P';
    }


}
