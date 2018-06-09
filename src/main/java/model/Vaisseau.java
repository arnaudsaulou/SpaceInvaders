package model;

import utils.MissileException;

public class Vaisseau extends Sprite {

    public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
        super(dimension, positionOrigine, vitesse);
    }

    public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

        if (this.dimension.longueur() < dimensionMissile.longueur()) {
            throw new MissileException("La longueur du missileVaisseau est supérieure à celle du vaisseau.");
        }

        Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);
        return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
    }

    private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
        int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
        int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

        int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
        return new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
    }

}