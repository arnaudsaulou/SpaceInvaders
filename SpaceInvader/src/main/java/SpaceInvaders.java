
public class SpaceInvaders {

	  int longueur;
	  int hauteur;
	  Vaisseau vaisseau;

	    public SpaceInvaders(int longueur, int hauteur) {
		   this.longueur = longueur;
		   this.hauteur = hauteur;
	   }
	    
	    public void positionnerUnNouveauVaisseau(int x, int y) {
	        Vaisseau vaisseau = new Vaisseau (x,y);
		}
	    
	    @Override
		public String toString() {
			StringBuilder espaceDeJeu = new StringBuilder();
			for (int y = 0; y < hauteur; y++) {
				for (int x = 0; x < longueur; x++) {
					if (vaisseau!=null && vaisseau.occupeLaPosition(x, y))
						espaceDeJeu.append('V');
					else
						espaceDeJeu.append('.');
				}
				espaceDeJeu.append('\n');
			}
			return espaceDeJeu.toString();
		}
}
