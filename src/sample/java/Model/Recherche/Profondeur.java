package Model.Recherche;

import Model.Actions.Action;
import Model.Recherche.OuverDS.StackOv;
import Model.Taquin.Taquin;

import java.util.Collections;
import java.util.List;

public class Profondeur extends Recherche {
    private int maxProfondeur;

    public Profondeur(Taquin etatInitial, Taquin but) {
        super(etatInitial, but);
        ouvert = new StackOv();
    }

    public Profondeur(Taquin etatInitial, Taquin but, int maxProfondeur) {
        super(etatInitial, but);
        this.maxProfondeur = maxProfondeur;
        ouvert = new StackOv();
    }

    public List<Action> run() {


        ouvert.add(root);
        while (!ouvert.isEmpty()) {

            Noeud noeud = ouvert.remove();
            updateProfondeur(noeud.getProfondeur());

            if (fermee.containsKey(noeud.getTaquin()))
                continue;

            if (isGoal(noeud)) {
                System.out.println("the number of actions to achieve the goal in DFS is = "+noeud.getProfondeur());
                return trackSolution(noeud);
            }

            if (profondeur == maxProfondeur) {
                fermee.put(noeud.getTaquin(), noeud);
                continue;
            }


            fermee.put(noeud.getTaquin(), noeud);

            for (Action action : getValidActions(noeud)) {
                Noeud newNoeud = createNoeud(noeud, action);
                ouvert.add(newNoeud);
            }
        }

        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "DFS";
    }
}
