package model;

public class PartieFactory {
    // Méthode de création
    public static Partie creerPartie(String type) {
        switch (type.toLowerCase()) {
            case "foret" :
                return new PartieForet();
            case "jungle" :
                return new PartieJungle();
            default :
                throw new IllegalArgumentException("Type de partie inconnu : " + type);
        }
    }
}