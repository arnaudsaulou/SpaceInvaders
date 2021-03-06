import model.Dimension;
import model.Position;
import model.SpaceInvaders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.DebordementEspaceJeuException;
import utils.FinDuJeuException;
import utils.HorsEspaceJeuException;
import utils.MissileException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SpaceInvadersTest {

    private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
        spaceinvaders = new SpaceInvaders(15, 10);
    }

    @Test
    public void test_AuDebut_JeuSpaceInvaderEstVide() {
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
                "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(7, 9), 1);
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
                ".......V.......\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".......VVV.....\n" +
                ".......VVV.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }


    @Test
    public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {

        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(15, 9), 1);
            fail("model.Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }

        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(-1, 9), 1);
            fail("model.Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }

        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, 10), 1);
            fail("model.Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }

        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, -1), 1);
            fail("model.Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }

    }

    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, 9), 1);

        spaceinvaders.deplacerVaisseauVersLaDroite();

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
                "..............V\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 3);
        spaceinvaders.deplacerVaisseauVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "....VVV........\n" +
                "....VVV........\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(0, 9), 3);
        spaceinvaders.deplacerVaisseauVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "VVV............\n" +
                "VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_UnNouvelElementPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

        try {
            spaceinvaders.positionnerUnNouvelElement(new Dimension(9, 2), new Position(7, 9));
            fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
        } catch (final DebordementEspaceJeuException e) {
        }


        try {
            spaceinvaders.positionnerUnNouvelElement(new Dimension(3, 4), new Position(7, 1));
            fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
        } catch (final DebordementEspaceJeuException e) {
        }

    }

    @Test
    public void test_VaisseauAvecDimensionImmobile_DeplacerVaisseauVersLaDroite() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(12, 9), 3);
        spaceinvaders.deplacerVaisseauVersLaDroite();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "............VVV\n" +
                "............VVV\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(10, 9), 3);
        spaceinvaders.deplacerVaisseauVersLaDroite();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "............VVV\n" +
                "............VVV\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(1, 9), 3);
        spaceinvaders.deplacerVaisseauVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "VVV............\n" +
                "VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(3, 2), 2, 0);

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".......MMM.....\n" +
                ".......MMM.....\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test(expected = MissileException.class)
    public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(7, 9), 1, 0);
    }

    @Test
    public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(3, 2), 2, 0);

        spaceinvaders.deplacerMissile();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".......MMM.....\n" +
                ".......MMM.....\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }


    @Test
    public void test_TirerPlusieurMissile() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(0, 9), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 1), 1, 1);
        spaceinvaders.deplacerMissile();
        spaceinvaders.deplacerMissile();
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 1), 1, 1);


        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "M..............\n" +
                "...............\n" +
                "M..............\n" +
                "V..............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(3, 2), 1, 0);
        for (int i = 1; i <= 6; i++) {
            spaceinvaders.deplacerMissile();
        }

        spaceinvaders.deplacerMissile();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_UnNouvelEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {

        try {
            spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(15, 9), 1);
            fail("model.Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }


        try {
            spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(-1, 9), 1);
            fail("model.Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }


        try {
            spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(14, 10), 1);
            fail("model.Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }


        try {
            spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(14, -1), 1);
            fail("model.Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }

    }

    @Test
    public void test_PlacementEnvahisseurOk() {

        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(4, 2), new Position(6, 1), 1);

        assertEquals("" +
                "......EEEE.....\n" +
                "......EEEE.....\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {

        spaceinvaders.initialiserLigneEnvahisseur();
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(0, 0), 1);

        spaceinvaders.deplacerEnvahisseur();

        assertEquals("" +
                ".E.............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroiteMaisPeutPasDoncDemiTourEtDescent() {

        spaceinvaders.initialiserLigneEnvahisseur();
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(14, 0), 1);

        spaceinvaders.deplacerEnvahisseur();

        assertEquals("" +
                "...............\n" +
                ".............E.\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_PlacementPlusieurEnvahisseur() {

        spaceinvaders.positionerNouvelleLigneEnvahisseur(0, 15, 2, 1, 1, 0);

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                ".EE.EE.EE.EE.EE\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_faireDescendreEnvahisseur() {

        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(0, 0), 1);
        spaceinvaders.recupereEnvahisseur().get(0).faireDescendre();

        assertEquals("" +
                "...............\n" +
                "E..............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }


    @Test(expected = FinDuJeuException.class)
    public void test_FinDePariePerdu_EnvahisseurEnBasEspaceDeJeu() {

        spaceinvaders.initialiserLigneEnvahisseur();
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(14, 9), 1);

        spaceinvaders.deplacerEnvahisseur();

        spaceinvaders.etreFini();
    }

    @Test(expected = FinDuJeuException.class)
    public void test_FinDePariePerdu_EnvahisseurToucheVaisseau() {
        spaceinvaders.initialiserLigneEnvahisseur();
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(13, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(14, 8), 1);

        spaceinvaders.deplacerEnvahisseur();
        spaceinvaders.etreFini();
    }

    @Test(expected = FinDuJeuException.class)
    public void test_FinDeParieGagne_PlusDEnvahisseur() {
        spaceinvaders.initialiserLigneEnvahisseur();
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(1, 9), 1);
        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(0, 8), 1);
        spaceinvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 1), 1, 0);
        spaceinvaders.deplacerEnvahisseur();

        spaceinvaders.etreFini();
    }

    @Test
    public void test_initialisetionNouvelleLigneEnvahisseur() {
        spaceinvaders.initialiserLigneEnvahisseur();
        Assert.assertNotNull(spaceinvaders.getLigneEnvahisseur());
    }

}