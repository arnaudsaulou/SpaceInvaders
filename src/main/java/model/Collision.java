package model;

public class Collision {

    public static boolean detecterCollision(Sprite sprite1, Sprite sprite2) {
        if (sprite1 == null || sprite2 == null)
            return false;

        return collisionSpriteAbscisse(sprite1, sprite2) && collisionSpriteOrdonnee(sprite1, sprite2);
    }

    public static boolean collisionSpriteOrdonnee(Sprite sprite1, Sprite sprite2) {
        return ordonneePlusBasseDeSprite1DansIntervalOrdoneeSprite2(sprite2, sprite1) ||
                ordonneePlusHauteDeSprite1DansIntervalOrdoneeSprite2(sprite2, sprite1);
    }

    public static boolean collisionSpriteAbscisse(Sprite sprite1, Sprite sprite2) {
        return abscissePlusAGaucheDeSprite1DansIntervalAbscisseSprite2(sprite1, sprite2) ||
                abscissePlusADroiteDeSprite1DansIntervalAbscisseSprite2(sprite1, sprite2);
    }

    public static boolean ordonneePlusBasseDeSprite1DansIntervalOrdoneeSprite2(Sprite sprite1, Sprite sprite2) {
        return sprite1.ordonneeLaPlusBasse() >= sprite2.ordonneeLaPlusBasse() &&
                sprite1.ordonneeLaPlusBasse() <= sprite2.ordonneeLaPlusHaute();
    }

    public static boolean ordonneePlusHauteDeSprite1DansIntervalOrdoneeSprite2(Sprite sprite1, Sprite sprite2) {
        return sprite1.ordonneeLaPlusHaute() >= sprite2.ordonneeLaPlusBasse() &&
                sprite1.ordonneeLaPlusHaute() <= sprite2.ordonneeLaPlusHaute();
    }

    public static boolean abscissePlusADroiteDeSprite1DansIntervalAbscisseSprite2(Sprite sprite1, Sprite sprite2) {
        return sprite1.abscisseLaPlusADroite() >= sprite2.abscisseLaPlusAGauche() &&
                sprite1.abscisseLaPlusADroite() <= sprite2.abscisseLaPlusADroite();
    }

    public static boolean abscissePlusAGaucheDeSprite1DansIntervalAbscisseSprite2(Sprite sprite1, Sprite sprite2) {
        return sprite1.abscisseLaPlusAGauche() >= sprite2.abscisseLaPlusAGauche() &&
                sprite1.abscisseLaPlusAGauche() <= sprite2.abscisseLaPlusADroite();
    }
}