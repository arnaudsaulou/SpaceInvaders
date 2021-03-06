import model.Dimension;
import model.Position;
import model.Vaisseau;
import org.junit.Test;
import utils.MissileException;

public class VaisseauTest {

    @Test(expected = MissileException.class)
    public void test_LongueurMissileSuperieureALongueurVaisseau_UneExceptionEstLevee() {
        Vaisseau vaisseau = new Vaisseau(new Dimension(5, 2), new Position(5, 9), 1);
        vaisseau.tirerUnMissile(new Dimension(7, 2), 1);
    }

}
