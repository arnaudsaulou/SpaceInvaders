package model;

import moteurJeu.DessinJeu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class DessinSpaceInvaders implements DessinJeu {

    private final SpaceInvaders jeu;

    public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
        this.jeu = spaceInvaders;
    }

    @Override
    public void dessiner(BufferedImage im) {
        if (this.jeu.aUnVaisseau()) {
            Vaisseau vaisseau = this.jeu.recupererVaisseau();
            this.dessinerUnVaisseau(vaisseau, im);
        }
        if (this.jeu.aUnMissileVaisseau()) {
            List<Missile> missileVaisseau = this.jeu.recupererMissileVaisseau();
            for (Missile aMissileVaisseau : missileVaisseau) {
                this.dessinerUnMissile(aMissileVaisseau, im, Color.BLUE);
            }
        }
        if (this.jeu.aUnMissileEnvahisseur()) {
            List<Missile> missileEnvahisseur = this.jeu.recupererMissileEnvahisseur();
            for (Missile aMissileEnvahisseur : missileEnvahisseur) {
                this.dessinerUnMissile(aMissileEnvahisseur, im, Color.MAGENTA);
            }
        }
        if (this.jeu.aUnEnvahisseur()) {
            List<Envahisseur> envahisseur = this.jeu.recupereEnvahisseur();
            for (Envahisseur anEnvahisseur : envahisseur) {
                this.dessinerUnEnvahisseur(anEnvahisseur, im);
            }
        }
    }

    private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        crayon.setColor(Color.gray);
        crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());

    }

    private void dessinerUnMissile(Missile missile, BufferedImage im, Color color) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        crayon.setColor(color);
        crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(), missile.hauteur());
    }

    private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        crayon.setColor(Color.red);
        crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.longueur(), envahisseur.hauteur());
    }

}
