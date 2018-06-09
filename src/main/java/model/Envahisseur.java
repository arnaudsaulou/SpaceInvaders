package model;

import utils.MissileException;

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

    public void faireDescendre() {
        this.origine.changerOrdonnee(this.origine.ordonnee() + Direction.BAS_ECRAN.valeur() * this.vitesse * this.dimension.hauteur());
    }

    public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

        if (this.dimension.longueur() < dimensionMissile.longueur()) {
            throw new MissileException("La longueur du missileVaisseau est supérieure à celle du vaisseau.");
        }

        Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);
        return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
    }

    public Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
        int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
        int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

        int ordonneeeOrigineMissile = this.ordonneeLaPlusHaute() + 1;
        return new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
    }

}
