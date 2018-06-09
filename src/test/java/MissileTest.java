import model.Dimension;
import model.Position;
import model.SpaceInvaders;
import org.junit.Before;
import org.junit.Test;
import utils.MissileException;

public class MissileTest {
    private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
        spaceinvaders = new SpaceInvaders(15, 10);
    }

    @Test(expected = MissileException.class)
    public void test_VitesseMissileSuperieurHauteurEnvahisseur() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(7, 2), new Position(5, 2), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(7, 9), 5, 0);
    }
}
