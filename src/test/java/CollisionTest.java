import model.Collision;
import model.Dimension;
import model.Position;
import model.SpaceInvaders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollisionTest {

    private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
        spaceinvaders = new SpaceInvaders(15, 10);
    }

    @Test
    public void TestTirMissilePasDeCollisionAvecEnvahisseur() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(5, 1), new Position(6, 0), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 1), 1, 0);
        spaceinvaders.deplacerMissile();

        Assert.assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissileVaisseau().get(0), spaceinvaders.recupereEnvahisseur().get(0)));
    }

    @Test
    public void TestTirCollisionMissileDeTailleInferieurATailleEnvahisseur() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(4, 2), new Position(6, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(4, 3), new Position(7, 5), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 1), 2, 0);
        spaceinvaders.deplacerMissile();

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissileVaisseau().get(0), spaceinvaders.recupereEnvahisseur().get(0)));
    }

    @Test
    public void TestTirCollisionMissileDeTailleSuperieurATailleEnvahisseur() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(8, 2), new Position(4, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2, 1), new Position(8, 5), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(4, 2), 1, 0);
        spaceinvaders.deplacerMissile();

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissileVaisseau().get(0), spaceinvaders.recupereEnvahisseur().get(0)));
    }


    @Test
    public void Test_ordonneePlusBasseDeSprite1DansIntervalOrdoneeSprite2() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 1), new Position(6, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(5, 2), new Position(5, 6), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1, 0);
        spaceinvaders.deplacerMissile();

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissileVaisseau().get(0), spaceinvaders.recupereEnvahisseur().get(0)));
    }

    @Test
    public void Test_ordonneePlusHauteDeSprite1DansIntervalOrdoneeSprite2() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 1), new Position(6, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(5, 2), new Position(5, 8), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1, 0);
        spaceinvaders.deplacerMissile();

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissileVaisseau().get(0), spaceinvaders.recupereEnvahisseur().get(0)));
    }

    @Test
    public void Test_abscissePlusADroiteDeSprite1DansIntervalAbscisseSprite2() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 1), new Position(8, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(5, 2), new Position(5, 7), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1, 0);
        spaceinvaders.deplacerMissile();
        spaceinvaders.deplacerMissile();

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissileVaisseau().get(0), spaceinvaders.recupereEnvahisseur().get(0)));
    }

    @Test
    public void Test_abscissePlusAGaucheDeSprite1DansIntervalAbscisseSprite2() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 1), new Position(4, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(5, 2), new Position(5, 7), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1, 0);
        spaceinvaders.deplacerMissile();

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissileVaisseau().get(0), spaceinvaders.recupereEnvahisseur().get(0)));
    }

    @Test
    public void test_EnvahisseurToucheAvecTirePlusieurMissile() {
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(0, 0), 1);
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(0, 9), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 1), 1, 1);
        spaceinvaders.deplacerMissile();
        spaceinvaders.deplacerMissile();
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 1), 1, 1);

        for (int i = 0; i < 6; i++) {
            spaceinvaders.deplacerMissile();
        }

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissileVaisseau().get(0), spaceinvaders.recupereEnvahisseur().get(0)));
    }

}