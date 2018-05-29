import model.Dimension;
import model.Envahisseur;
import model.Position;
import org.junit.Test;
import utils.MissileException;

public class EnvahisseurTest {

    @Test(expected = MissileException.class)
    public void test_LongueurEnvahisseurSuperieureALongueurEspaceJeu_UneExceptionEstLevee() {
        new Envahisseur(new Dimension(5, 2), new Position(5, 9), 1);
    }
}
