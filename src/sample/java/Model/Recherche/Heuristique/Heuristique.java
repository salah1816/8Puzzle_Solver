package Model.Recherche.Heuristique;

import Model.Recherche.Noeud;
import Model.Taquin.Taquin;

public abstract class Heuristique {

    public Heuristique() {
    }

    public abstract int evaluate(Noeud noeud, Taquin etatBut);
}
