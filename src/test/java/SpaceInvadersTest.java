import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpaceInvadersTest {

    @Test
    public void test_AuDebut_JeuSpaceInvaderEstVide() {
        SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n", spaceinvaders.toString());
    }

}