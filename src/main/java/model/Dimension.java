package model;

public class Dimension {
    private final int longueur;
    private final int hauteur;

    public Dimension(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

    int longueur() {
        return this.longueur;
    }

    int hauteur() {
        return this.hauteur;
    }

}
