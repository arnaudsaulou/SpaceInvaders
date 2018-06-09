package model;

import moteurJeu.Commande;
import moteurJeu.Jeu;
import utils.DebordementEspaceJeuException;
import utils.FinDuJeuException;
import utils.HorsEspaceJeuException;
import utils.MissileException;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders implements Jeu {

    private final int longueur;
    private final int hauteur;
    private final List<Missile> missileVaisseau;
    private final List<Missile> missileEnvahisseur;
    private final List<LigneEnvahisseur> ligneEnvahisseur;
    private final List<Envahisseur> envahisseur;
    private Vaisseau vaisseau;

    public SpaceInvaders(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.missileVaisseau = new ArrayList<Missile>();
        this.missileEnvahisseur = new ArrayList<Missile>();
        this.ligneEnvahisseur = new ArrayList<LigneEnvahisseur>();
        this.envahisseur = new ArrayList<Envahisseur>();
    }

    public void initialiserJeu() {
        Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
        Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
        positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

        positionerNouvelleLigneEnvahisseur(0,
                Constante.ESPACEJEU_LONGUEUR,
                Constante.ENVAHISSEUR_LONGUEUR,
                Constante.ENVAHISSEUR_HAUTEUR,
                Constante.ENVAHISSEUR_VITESSE);

        validationVitesseMissile();
    }

    public void positionerNouvelleLigneEnvahisseur(int numLigne, int longueurEspaceJeu, int envahisseurLongueur,
                                                   int envahisseurHauteur, int envahisseurVitesse) {

        this.ligneEnvahisseur.add(new LigneEnvahisseur(numLigne,
                longueurEspaceJeu, envahisseurLongueur, envahisseurHauteur, envahisseurVitesse));
    }

    private void validationVitesseMissile() {
        if (Constante.MISSILE_VITESSE > Constante.ENVAHISSEUR_HAUTEUR) {
            throw new MissileException("Vitesse des missile du vaisseau spérieur à hauteur de l'envahisseur");
        }
    }

    public void initialiserLigneEnvahisseur() {
        this.ligneEnvahisseur.add(new LigneEnvahisseur());
    }

    public List<LigneEnvahisseur> getLigneEnvahisseur() {
        return ligneEnvahisseur;
    }

    public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {
        positionnerUnNouvelElement(dimension, position);
        this.envahisseur.add(new Envahisseur(dimension, position, vitesse));
    }

    public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
        positionnerUnNouvelElement(dimension, position);
        this.vaisseau = new Vaisseau(dimension, position, vitesse);
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
        return (((x >= 0) && (x < this.longueur)) && ((y >= 0) && (y < this.hauteur)));
    }

    private char recupererMarqueDeLaPosition(int x, int y) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
            marque = Constante.MARQUE_VAISSEAU;
        else if (this.aUnMissileVaisseauQuiOccupeLaPosition(x, y) || aUnMissileEnvahisseurQuiOccupeLaPosition(x, y))
            marque = Constante.MARQUE_MISSILE;
        else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
            marque = Constante.MARQUE_ENVAHISSEUR;
        else marque = Constante.MARQUE_VIDE;
        return marque;
    }

    private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
        return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
    }

    private boolean aUnMissileVaisseauQuiOccupeLaPosition(int x, int y) {
        boolean positionOccupe = false;
        int numMissile = 0;

        while (!positionOccupe && numMissile < this.missileVaisseau.size()) {
            positionOccupe = this.aUnMissileVaisseau() && missileVaisseau.get(numMissile).occupeLaPosition(x, y);
            numMissile++;
        }

        return positionOccupe;
    }

    private boolean aUnMissileEnvahisseurQuiOccupeLaPosition(int x, int y) {
        boolean positionOccupe = false;
        int numMissile = 0;

        while (!positionOccupe && numMissile < this.missileEnvahisseur.size()) {
            positionOccupe = this.aUnMissileEnvahisseur() && missileEnvahisseur.get(numMissile).occupeLaPosition(x, y);
            numMissile++;
        }

        return positionOccupe;
    }

    private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
        boolean positionOccupe = false;
        int numEnvahisseur = 0;

        while (!positionOccupe && numEnvahisseur < this.envahisseur.size()) {
            positionOccupe = this.aUnEnvahisseur() && envahisseur.get(numEnvahisseur).occupeLaPosition(x, y);
            numEnvahisseur++;
        }
        return positionOccupe;
    }

    boolean aUnVaisseau() {
        return vaisseau != null;
    }

    boolean aUnMissileVaisseau() {
        return this.missileVaisseau.size() > 0;
    }

    boolean aUnMissileEnvahisseur() {
        return this.missileEnvahisseur.size() > 0;
    }

    boolean aUnEnvahisseur() {
        return this.envahisseur.size() > 0;
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


    private boolean testCollisionVaisseauMissileEnvahiseur() {

        Missile missileATest;
        boolean collision = false;
        int numMissile = 0;

        if (this.aUnMissileEnvahisseur()) {
            while (!collision && numMissile < this.missileEnvahisseur.size()) {
                missileATest = this.missileEnvahisseur.get(numMissile);

                if (Collision.detecterCollision(missileATest, this.vaisseau)) {
                    collision = true;
                    this.missileVaisseau.remove(missileATest);
                    this.vaisseau = null;
                }
                numMissile++;
            }
        }

        return collision;
    }

    public Vaisseau recupererVaisseau() {
        return this.vaisseau;
    }

    public List<Missile> recupererMissileVaisseau() {
        return this.missileVaisseau;
    }

    public List<Missile> recupererMissileEnvahisseur() {
        return this.missileEnvahisseur;
    }

    public List<Envahisseur> recupereEnvahisseur() {
        return this.envahisseur;
    }

    public void tirerUnMissileDepuisVaisseau(Dimension dimensionMissile, int vitesseMissile, int margin) {

        if ((this.vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur) {
            throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missileVaisseau");
        }

        Missile missileATirer = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);

        if (this.aUnMissileVaisseau()) {
            if (peutTirerMissileSuivant(this.missileVaisseau.get(this.missileVaisseau.size() - 1), missileATirer, margin)) {
                ajouterMissileVaisseauALaListe(missileATirer);
            }
        } else {
            ajouterMissileVaisseauALaListe(missileATirer);
        }
    }

    public void tirerUnMissileDepuisEnvahisseur(Dimension dimensionMissile, int vitesseMissile, int numEnvahisseur, int margin) {

        if ((this.ligneEnvahisseur.get(this.ligneEnvahisseur.size() - 1).envahisseurLePlusADroiteDeLaLigne().hauteur() + dimensionMissile.hauteur()) > Constante.ESPACEJEU_HAUTEUR) {
            throw new MissileException("Pas assez de hauteur libre entre l'envahisseur et le bas de l'espace jeu pour tirer le missileVaisseau");
        }

        Missile missileATirer = this.envahisseur.get(numEnvahisseur).tirerUnMissile(dimensionMissile, vitesseMissile);

        if (this.aUnMissileEnvahisseur()) {
            if (peutTirerMissileSuivant(this.missileEnvahisseur.get(this.missileEnvahisseur.size() - 1), missileATirer, margin)) {
                ajouterMissileEnvahisseurALaListe(missileATirer);
            }
        } else {
            ajouterMissileEnvahisseurALaListe(missileATirer);
        }
    }

    private boolean peutTirerMissileSuivant(Missile missileDAvant, Missile missileATest, int margin) {
        return !Collision.detecterCollisionAvecMargin(missileDAvant, missileATest, margin);
    }

    private void ajouterMissileVaisseauALaListe(Missile missileATirer) {
        this.missileVaisseau.add(missileATirer);
    }

    private void ajouterMissileEnvahisseurALaListe(Missile missileATirer) {
        this.missileEnvahisseur.add(missileATirer);
    }

    public void deplacerMissile() {
        if (this.aUnMissileVaisseau()) {
            for (int numMissile = 0; numMissile < this.missileVaisseau.size(); numMissile++) {
                if (this.estDansEspaceJeu(this.missileVaisseau.get(numMissile).abscisseLaPlusAGauche(), this.missileVaisseau.get(numMissile).ordonneeLaPlusBasse() + Direction.HAUT_ECRAN.valeur())) {
                    this.missileVaisseau.get(numMissile).deplacerVerticalementVers(Direction.HAUT_ECRAN);
                } else {
                    this.missileVaisseau.remove(numMissile);
                }
            }
        }

        if (this.aUnMissileEnvahisseur()) {
            for (int numMissile = 0; numMissile < this.missileEnvahisseur.size(); numMissile++) {
                if (this.estDansEspaceJeu(this.missileEnvahisseur.get(numMissile).abscisseLaPlusAGauche(), this.missileEnvahisseur.get(numMissile).ordonneeLaPlusHaute() + Direction.BAS_ECRAN.valeur())) {
                    this.missileEnvahisseur.get(numMissile).deplacerVerticalementVers(Direction.BAS_ECRAN);
                } else {
                    this.missileEnvahisseur.remove(numMissile);
                }
            }
        }
    }

    private void deplacerToutLesEnvahisseur(int typeDeplacement) {

        for (int numEnvahisseur = 0; numEnvahisseur < this.envahisseur.size(); numEnvahisseur++) {
            switch (typeDeplacement) {
                case 0:
                    demiTourEnvahisseurDroiteVersGauche(numEnvahisseur);
                    break;
                case 1:
                    deplacerEnvahisseurVersDroite(numEnvahisseur);
                    break;
                case 2:
                    demiTourEnvahisseurGaucheVersDroite(numEnvahisseur);
                    break;
                case 3:
                    deplacerEnvahisseurVersGauche(numEnvahisseur);
                    break;
                default:
                    break;
            }

            testCollisionEnvahisseurVaisseau(numEnvahisseur);
            testCollisionEnvahisseurMissileVaisseau(numEnvahisseur);

        }
    }

    private void testCollisionEnvahisseurMissileVaisseau(int numEnvahisseur) {

        Missile missileATest;
        Envahisseur envahisseurATest;
        boolean collision = false;
        int numMissile = 0;

        if (this.aUnMissileVaisseau()) {
            while (!collision && numMissile < this.missileVaisseau.size()) {
                missileATest = this.missileVaisseau.get(numMissile);
                envahisseurATest = this.envahisseur.get(numEnvahisseur);

                if (Collision.detecterCollision(missileATest, envahisseurATest)) {
                    collision = true;
                    this.missileVaisseau.remove(missileATest);
                    this.envahisseur.remove(envahisseurATest);
                }
                numMissile++;
            }
        }
    }

    private boolean testEnvahisseurEnBasDeEspaceDeJeu() {
        if (this.aUnEnvahisseur()) {
            return this.envahisseur.get(0).ordonneeLaPlusHaute() == Constante.ESPACEJEU_HAUTEUR;
        } else {
            return false;
        }
    }

    public void deplacerEnvahisseur() {
        if (this.aUnEnvahisseur()) {
            if (this.ligneEnvahisseur.get(0).envahisseurLePlusADroiteDeLaLigne().getDirectionDeplacement() == Direction.DROITE) {
                if (peutPasSeDeplacerADroite()) {
                    deplacerToutLesEnvahisseur(0);
                } else {
                    deplacerToutLesEnvahisseur(1);
                }
            } else {
                if (peutPasSeDeplacerAGauche()) {
                    deplacerToutLesEnvahisseur(2);
                } else {
                    deplacerToutLesEnvahisseur(3);
                }
            }
        }
    }

    private void testCollisionEnvahisseurVaisseau(int numEnvahisseur) {

        if (Collision.detecterCollision(this.envahisseur.get(numEnvahisseur), this.vaisseau)) {
            this.vaisseau = null;
        }

    }

    private void deplacerEnvahisseurVersGauche(int numEnvahisseur) {
        this.envahisseur.get(numEnvahisseur).deplacerHorizontalementVers(Direction.GAUCHE);
    }

    private void deplacerEnvahisseurVersDroite(int numEnvahisseur) {
        this.envahisseur.get(numEnvahisseur).deplacerHorizontalementVers(Direction.DROITE);
    }

    private void demiTourEnvahisseurGaucheVersDroite(int numEnvahisseur) {
        this.envahisseur.get(numEnvahisseur).inverserDirectionDeplacement();
        this.envahisseur.get(numEnvahisseur).faireDescendre();
        deplacerEnvahisseurVersDroite(numEnvahisseur);
    }

    private void demiTourEnvahisseurDroiteVersGauche(int numEnvahisseur) {
        this.envahisseur.get(numEnvahisseur).inverserDirectionDeplacement();
        this.envahisseur.get(numEnvahisseur).faireDescendre();
        deplacerEnvahisseurVersGauche(numEnvahisseur);
    }

    private boolean peutPasSeDeplacerAGauche() {
        return this.ligneEnvahisseur.get(0).envahisseurLePlusAGaucheDeLaLigne().abscisseLaPlusAGauche() < 0;
    }

    private boolean peutPasSeDeplacerADroite() {
        return this.ligneEnvahisseur.get(0).envahisseurLePlusADroiteDeLaLigne().abscisseLaPlusADroite() + Direction.DROITE.valeur() >= this.longueur;
    }

    private boolean genererUnMissileEnvahisseur() {
        return Math.random() * Math.random() > 0.85;
    }

    private boolean conditionPourGagner() {
        return this.envahisseur.size() == 0;
    }

    private boolean conditionPourPerdre() {
        return testCollisionVaisseauMissileEnvahiseur() || this.vaisseau == null || testEnvahisseurEnBasDeEspaceDeJeu();
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
            tirerUnMissileDepuisVaisseau(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR), Constante.MISSILE_VITESSE, Constante.MARGIN_MISSILE);
        }

        if (genererUnMissileEnvahisseur()) {
            int numEnvahisseur = (int) (Math.random() * (this.envahisseur.size() - 1));
            this.tirerUnMissileDepuisEnvahisseur(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR), Constante.MISSILE_VITESSE, numEnvahisseur, Constante.MARGIN_MISSILE);
        }

        this.deplacerMissile();
        this.deplacerEnvahisseur();
    }

    @Override
    public boolean etreFini() {
        if (conditionPourPerdre()) {
            throw new FinDuJeuException("Vous avez perdu :(");
        } else if (conditionPourGagner()) {
            throw new FinDuJeuException("Bravo vous avez gagné !");
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return recupererEspaceJeuDansChaineASCII();
    }

    class LigneEnvahisseur {

        int nbEnvahisseur;

        LigneEnvahisseur() {
        }

        LigneEnvahisseur(int numLigne, int longueurEspaceJeu, int envahisseurLongueur, int envahisseurHauteur, int envahisseurVitesse) {

            this.nbEnvahisseur = longueurEspaceJeu / ((envahisseurLongueur + (envahisseurLongueur / 2)));

            Dimension dimensionEnvahisseur = new Dimension(envahisseurLongueur, envahisseurHauteur);

            int x = envahisseurLongueur / 2;

            for (int nbEnvahisseurPosse = 0; nbEnvahisseurPosse < nbEnvahisseur; nbEnvahisseurPosse++) {
                Position positionEnvahisseur = new Position(x, (numLigne + 1) * (envahisseurHauteur + envahisseurHauteur));
                positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, envahisseurVitesse);
                x += envahisseurLongueur + (envahisseurLongueur / 2);
            }
        }

        Envahisseur envahisseurLePlusAGaucheDeLaLigne() {
            return envahisseur.get(0);
        }

        Envahisseur envahisseurLePlusADroiteDeLaLigne() {
            return envahisseur.get(envahisseur.size() - 1);
        }
    }


}
