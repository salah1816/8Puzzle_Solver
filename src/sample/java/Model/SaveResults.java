package Model;

import Model.Actions.Action;
import Model.Recherche.Recherche;
import Model.Taquin.Taquin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveResults {
    private final Taquin taquinInitial;
    private final List<Action> solution;
    private final long time;
    private final int etatDeveloper;
    private final int etatExplorer;
    private final String algo;

    public SaveResults(Taquin taquinInitial, List<Action> solution, long time, int etatDeveloper, int etatExplorer, String algo) {
        this.taquinInitial = taquinInitial;
        this.solution = solution;
        this.time = time;
        this.etatDeveloper = etatDeveloper;
        this.etatExplorer = etatExplorer;
        this.algo = algo;
    }

    public SaveResults(Recherche recherche, long time) {
        this.taquinInitial = recherche.getRoot().getTaquin();
        this.solution = recherche.getSolution();
        this.time = time;
        this.etatDeveloper = recherche.getOuvertSize();
        this.etatExplorer = recherche.getFermerSize();
        this.algo = recherche.toString();
    }

    public void save() {

        try (FileWriter out = new FileWriter("result.txt", true)) {

            out.write(taquinInitial.getTaquinStr() + "\t");

            out.write(solution.size() + "\t");

            out.write(time + "\t");
            out.write(etatDeveloper + "\t");
            out.write(etatExplorer + "\t");
            out.write(algo + "\t");
            for (Action action : solution)
                out.write(action.toString() + "\t");
            out.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
