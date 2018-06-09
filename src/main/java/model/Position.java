package model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int abscisse() {
        return this.x;
    }

    int ordonnee() {
        return this.y;
    }

    void changerAbscisse(int nouvelleAbscisse) {
        this.x = nouvelleAbscisse;
    }

    void changerOrdonnee(int nouvelleOrdonnee) {
        this.y = nouvelleOrdonnee;
    }

}