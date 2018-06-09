package model;

public enum Direction {

    GAUCHE(-1),
    DROITE(1),

    HAUT_ECRAN(-1),
    BAS_ECRAN(1);

    private final int valeur;

    Direction(int valeur) {
        this.valeur = valeur;
    }

    public int valeur() {
        return this.valeur;
    }

}