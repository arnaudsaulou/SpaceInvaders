package model;

public class Envahisseur extends Sprite {

    boolean vaVersLaDroite;

    public Envahisseur(Dimension dimension, Position origine, int vitesse) {
        super(dimension, origine, vitesse);
        this.vaVersLaDroite = true;
    }

    public void inverserDirectionDeplacement() {
        this.vaVersLaDroite = !this.vaVersLaDroite;
    }

    public Direction getDirectionDeplacement() {
        if (this.vaVersLaDroite) {
            return Direction.DROITE;
        } else {
            return Direction.GAUCHE;
        }
    }

}
