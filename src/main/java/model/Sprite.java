package model;

abstract class Sprite {
    final Position origine;
    final Dimension dimension;
    final int vitesse;

    Sprite(Dimension dimension, Position origine, int vitesse) {
        super();
        this.dimension = dimension;
        this.origine = origine;
        this.vitesse = vitesse;
    }

    boolean occupeLaPosition(int x, int y) {
        return estAbscisseCouverte(x) && estOrdonneeCouverte(y);
    }

    private boolean estOrdonneeCouverte(int y) {
        return (ordonneeLaPlusBasse() <= y) && (y <= ordonneeLaPlusHaute());
    }

    private boolean estAbscisseCouverte(int x) {
        return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
    }

    int ordonneeLaPlusBasse() {
        return this.origine.ordonnee() - this.dimension.hauteur() + 1;
    }

    int ordonneeLaPlusHaute() {
        return this.origine.ordonnee();
    }

    int abscisseLaPlusADroite() {
        return this.origine.abscisse() + this.dimension.longueur() - 1;
    }

    int abscisseLaPlusAGauche() {
        return this.origine.abscisse();
    }

    void positionner(int x, int y) {
        this.origine.changerAbscisse(x);
        this.origine.changerOrdonnee(y);
    }

    int hauteur() {
        return this.dimension.hauteur();
    }

    int longueur() {
        return this.dimension.longueur();
    }

    void deplacerHorizontalementVers(Direction direction) {
        this.origine.changerAbscisse(this.origine.abscisse() + direction.valeur() * this.vitesse);
    }

    void deplacerVerticalementVers(Direction direction) {
        this.origine.changerOrdonnee(this.origine.ordonnee() + direction.valeur() * this.vitesse);
    }

}
