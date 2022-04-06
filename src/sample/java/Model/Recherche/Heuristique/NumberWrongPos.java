package Model.Recherche.Heuristique;

import Model.Recherche.Noeud;
import Model.Taquin.Index;
import Model.Taquin.Taquin;

public class NumberWrongPos extends Heuristique {
    @Override
    public int evaluate(Noeud noeud, Taquin etatBut) {
        int h=0;
        Taquin etat = noeud.getTaquin();
        int size = etat.getSize();

        int [][] mat=etat.getTaquin();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(etatBut.getTaquin()[i][j]!=mat[i][j]) h++;
            }
        }
        return h;
    }
}
