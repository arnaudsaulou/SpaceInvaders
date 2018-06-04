package model;

import moteurJeu.Commande;
import moteurJeu.Jeu;
import utils.DebordementEspaceJeuException;
import utils.HorsEspaceJeuException;
import utils.MissileException;

import static java.lang.System.exit;

public class SpaceInvaders implements Jeu {

    int longueur;
    int hauteur;
    Vaisseau vaisseau;
    Missile missile;
    Envahisseur envahisseur;

    public SpaceInvaders(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

    public void initialiserJeu() {
        Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
        Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
        positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

        Position positionEnvahisseur = new Position(this.longueur / 2, Constante.ENVAHISSEUR_HAUTEUR + Constante.ENVAHISSEUR_HAUTEUR);
        Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
        positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);

        validationVitesseMissile();
    }

    private void validationVitesseMissile() {
        if (Constante.MISSILE_VITESSE > Constante.ENVAHISSEUR_HAUTEUR) {
            throw new MissileException("Vitesse du missile spérieur à hauteur de l'envahisseur");
        }
    }

    public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
        positionnerUnNouvelElement(dimension, position);
        this.vaisseau = new Vaisseau(dimension, position, vitesse);
    }

    public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {
        positionnerUnNouvelElement(dimension, position);
        this.envahisseur = new Envahisseur(dimension, position, vitesse);
    }

    public void positionnerUnNouvelElement(Dimension dimension, Position position) {

        int x = position.abscisse();
        int y = position.ordonnee();

        if (!estDansEspaceJeu(x, y))
            throw new HorsEspaceJeuException("La position de l'élément est en dehors de l'espace jeu");

        int longueurElement = dimension.longueur();
        int hauteurElement = dimension.hauteur();

        if (!estDansEspaceJeu(x + longueurElement - 1, y))
            throw new DebordementEspaceJeuException("L'élément déborde de l'espace jeu vers la droite à cause de sa longueur");
        if (!estDansEspaceJeu(x, y - hauteurElement + 1))
            throw new DebordementEspaceJeuException("L'élément déborde de l'espace jeu vers le bas à cause de sa hauteur");
    }

    private boolean estDansEspaceJeu(int x, int y) {
        return (((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur)));
    }

    public char recupererMarqueDeLaPosition(int x, int y) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
            marque = Constante.MARQUE_VAISSEAU;
        else if (this.aUnMissileQuiOccupeLaPosition(x, y))
            marque = Constante.MARQUE_MISSILE;
        else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
            marque = Constante.MARQUE_ENVAHISSEUR;
        else marque = Constante.MARQUE_VIDE;
        return marque;
    }

    private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
        return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
    }

    private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
        return this.aUnMissile() && missile.occupeLaPosition(x, y);
    }

    private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
        return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
    }

    public boolean aUnVaisseau() {
        return vaisseau != null;
    }

    public boolean aUnMissile() {
        return missile != null;
    }

    public boolean aUnEnvahisseur() {
        return envahisseur != null;
    }

    public String recupererEspaceJeuDansChaineASCII() {
        StringBuilder espaceDeJeu = new StringBuilder();

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < longueur; x++) {
                espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
            }
            espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
        }
        return espaceDeJeu.toString();
    }

    public void deplacerVaisseauVersLaDroite() {
        if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
            vaisseau.deplacerHorizontalementVers(Direction.DROITE);
            if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
                vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
            }
        }
    }

    public void deplacerVaisseauVersLaGauche() {
        if (0 < vaisseau.abscisseLaPlusAGauche())
            vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
        if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
            vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
        }
    }

    public Vaisseau recupererVaisseau() {
        return this.vaisseau;
    }

    public Missile recupererMissile() {
        return this.missile;
    }

    public Envahisseur recupereEnvahisseur() {
        return this.envahisseur;
    }

    public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

        if ((this.vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur) {
            throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
        }

        this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
    }

    public void deplacerMissile() {
        if (this.aUnMissile()) {
            if (this.estDansEspaceJeu(this.missile.abscisseLaPlusAGauche(), this.missile.ordonneeLaPlusBasse() + Direction.HAUT_ECRAN.valeur())) {
                this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
            } else {
                this.missile = null;
            }
        }
    }

    public void deplacerEnvahisseur() {
        if (this.aUnEnvahisseur()) {
            if (this.envahisseur.getDirectionDeplacement() == Direction.DROITE) {
                if (peutPasSeDeplacerADroite()) {
                    this.envahisseur.inverserDirectionDeplacement();
                    this.envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
                } else {
                    this.envahisseur.deplacerHorizontalementVers(Direction.DROITE);
                }
            } else {
                if (peutPasSeDeplacerAGauche()) {
                    this.envahisseur.inverserDirectionDeplacement();
                    this.envahisseur.deplacerHorizontalementVers(Direction.DROITE);
                } else {
                    this.envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
                }
            }
        }
    }

    private boolean peutPasSeDeplacerAGauche() {
        return this.envahisseur.abscisseLaPlusAGauche() < 0;
    }

    private boolean peutPasSeDeplacerADroite() {
        return this.envahisseur.abscisseLaPlusADroite() + Direction.DROITE.valeur() > hauteur;
    }


    @Override
    public void evoluer(Commande commandeUser) {

        if (commandeUser.gauche) {
            deplacerVaisseauVersLaGauche();
        }

        if (commandeUser.droite) {
            deplacerVaisseauVersLaDroite();
        }

        if (commandeUser.tir) {
            tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR), Constante.MISSILE_VITESSE);
        }

        this.deplacerMissile();
        this.deplacerEnvahisseur();
    }

    @Override
    public boolean etreFini() {
        if (Collision.detecterCollision(this.missile, this.envahisseur)) {
            this.missile = null;
            this.envahisseur = null;
            this.vaisseau = null;
            //TODO
            // Un peut Hard our le momemnt
            exit(0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return recupererEspaceJeuDansChaineASCII();
    }
}
