package moteurJeu;

/**
 * permet de representer une commande de l'utilisateur
 *
 * @author vthomas
 */
public class Commande {

    /**
     * boolean representant la commande de l'utilisateur
     */
    public boolean gauche;
    public boolean droite;
    boolean haut;
    boolean bas;
    public boolean tir;

    Commande() {

    }

    /**
     * constructeur par copie
     * copie la commande pour en creer une nouvelle
     *
     * @param commandeACopier
     */
    Commande(Commande commandeACopier) {
        this.bas = commandeACopier.bas;
        this.haut = commandeACopier.haut;
        this.gauche = commandeACopier.gauche;
        this.droite = commandeACopier.droite;
        this.tir = commandeACopier.tir;

    }

}
