package model;

import moteurJeu.DessinJeu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class DessinSpaceInvaders implements DessinJeu {

    private SpaceInvaders jeu;

    public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
        this.jeu = spaceInvaders;
    }

    @Override
    public void dessiner(BufferedImage im) {
        if (this.jeu.aUnVaisseau()) {
            Vaisseau vaisseau = this.jeu.recupererVaisseau();
            this.dessinerUnVaisseau(vaisseau, im);
        }
        if (this.jeu.aUnMissile()) {
            List<Missile> missile = this.jeu.recupererMissile();
            for (int numMissile = 0; numMissile < missile.size(); numMissile++) {
                this.dessinerUnMissile(missile.get(numMissile), im);
            }
        }
        if (this.jeu.aUnEnvahisseur()) {
            Envahisseur envahisseur = this.jeu.recupereEnvahisseur();
            this.dessinerUnEnvahisseur(envahisseur, im);
        }
    }

    private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        crayon.setColor(Color.gray);
        crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());

    }

    private void dessinerUnMissile(Missile missile, BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        crayon.setColor(Color.blue);
        crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(), missile.hauteur());
    }

    private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        crayon.setColor(Color.red);
        crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.longueur(), envahisseur.hauteur());
    }

}
