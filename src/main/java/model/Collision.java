package model;

public class Collision {

    public static boolean detecterCollisionAvecMargin(Sprite sprite1, Sprite sprite2, int margin) {
        if (sprite1 == null || sprite2 == null) {
            return false;
        } else {
            return collisionSpriteOrdonnee(sprite1, sprite2, margin) && collisionSpriteAbscisse(sprite1, sprite2, margin);
        }
    }

    public static boolean detecterCollision(Sprite sprite1, Sprite sprite2) {
        return detecterCollisionAvecMargin(sprite1, sprite2, 0);
    }

    private static boolean collisionSpriteOrdonnee(Sprite sprite1, Sprite sprite2, int margin) {
        return ordonneePlusBasseDeSprite1DansIntervalOrdoneeSprite2(sprite1, sprite2, margin) || ordonneePlusHauteDeSprite1DansIntervalOrdoneeSprite2(sprite1, sprite2, margin);
    }

    private static boolean collisionSpriteAbscisse(Sprite sprite1, Sprite sprite2, int margin) {
        return abscissePlusAGaucheDeSprite1DansIntervalAbscisseSprite2(sprite1, sprite2, margin) || abscissePlusADroiteDeSprite1DansIntervalAbscisseSprite2(sprite1, sprite2, margin);
    }

    private static boolean ordonneePlusBasseDeSprite1DansIntervalOrdoneeSprite2(Sprite sprite1, Sprite sprite2, int margin) {
        return sprite1.ordonneeLaPlusBasse() >= sprite2.ordonneeLaPlusBasse() && sprite1.ordonneeLaPlusBasse() <= sprite2.ordonneeLaPlusHaute() + margin;
    }

    private static boolean ordonneePlusHauteDeSprite1DansIntervalOrdoneeSprite2(Sprite sprite1, Sprite sprite2, int margin) {
        return sprite1.ordonneeLaPlusHaute() >= sprite2.ordonneeLaPlusBasse() - margin && sprite1.ordonneeLaPlusHaute() <= sprite2.ordonneeLaPlusHaute();
    }

    private static boolean abscissePlusADroiteDeSprite1DansIntervalAbscisseSprite2(Sprite sprite1, Sprite sprite2, int margin) {
        return sprite1.abscisseLaPlusADroite() + margin >= sprite2.abscisseLaPlusAGauche() && sprite1.abscisseLaPlusADroite() <= sprite2.abscisseLaPlusADroite();
    }

    private static boolean abscissePlusAGaucheDeSprite1DansIntervalAbscisseSprite2(Sprite sprite1, Sprite sprite2, int margin) {
        return sprite1.abscisseLaPlusAGauche() >= sprite2.abscisseLaPlusAGauche() && sprite1.abscisseLaPlusAGauche() <= sprite2.abscisseLaPlusADroite() + margin;
    }
}