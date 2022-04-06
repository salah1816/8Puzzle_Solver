package Model.Recherche.Heuristique;

import Model.Recherche.Noeud;
import Model.Taquin.Index;
import Model.Taquin.Taquin;

public class Manhatten extends Heuristique {
    @Override
    public int evaluate(Noeud noeud, Taquin etatBut) {
        int score = 0;
        Taquin etat = noeud.getTaquin();
        int n = etat.getSize();
        double distances = 0;
        int[][] mat = etat.getTaquin();//here getTaquin means get the matrix of the taquin etat
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    continue;
                Index index = etatBut.searchCase(mat[i][j]);
                double d = Math.abs(i - index.getI()) + Math.abs(j - index.getJ());
                distances += d;
            }
        }
        score=(int) distances;
        return score;
    }
}
