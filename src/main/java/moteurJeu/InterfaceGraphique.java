package moteurJeu;

import javax.swing.*;


/**
 * cree une interface graphique avec son controleur et son afficheur
 *
 * @author Graou
 */
class InterfaceGraphique {

    /**
     * le Panel lie a la JFrame
     */
    private final PanelDessin panel;

    /**
     * le controleur lie a la JFrame
     */
    private final Controleur controleur;

    /**
     * la construction de l'interface grpahique
     * - construit la JFrame
     * - construit les Attributs
     *
     * @param afficheurUtil l'afficheur a utiliser dans le moteur
     */
    InterfaceGraphique(DessinJeu afficheurUtil, int x, int y) {
        //creation JFrame
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creation panel
        this.panel = new PanelDessin(x, y, afficheurUtil);
        f.setContentPane(this.panel);

        //ajout du controleur
        Controleur controlleurGraph = new Controleur();
        this.controleur = controlleurGraph;
        this.panel.addKeyListener(controlleurGraph);

        //recuperation du focus
        f.pack();
        f.setVisible(true);
        f.getContentPane().setFocusable(true);
        f.getContentPane().requestFocus();
    }


    /**
     * retourne le controleur de l'affichage construit
     *
     * @return
     */
    Controleur getControleur() {
        return controleur;
    }

    /**
     * demande la mise a jour du dessin
     */
    void dessiner() {
        this.panel.dessinerJeu();
    }

}
