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
        spaceinvaders.tirerUnMissile(new Dimension(1, 1), 1);
        spaceinvaders.deplacerMissile();

        Assert.assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupereEnvahisseur()));
    }


    @Test
    public void TestTirCollisionMissileDeTailleInferieurATailleEnvahisseur() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(4, 2), new Position(6, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(4, 1), new Position(7, 5), 1);
        spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
        spaceinvaders.deplacerMissile();

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupereEnvahisseur()));
    }

    @Test
    public void TestTirCollisionMissileDeTailleSuperieurATailleEnvahisseur() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(8, 2), new Position(4, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2, 1), new Position(8, 5), 1);
        spaceinvaders.tirerUnMissile(new Dimension(4, 2), 1);
        spaceinvaders.deplacerMissile();

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupereEnvahisseur()));
    }

    @Test
    public void TestTirCollisionMissileTransperseEnvahisseur() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(5, 2), new Position(6, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(5, 1), new Position(6, 6), 1);
        spaceinvaders.tirerUnMissile(new Dimension(1, 3), 1);

        Assert.assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupereEnvahisseur()));
    }
}