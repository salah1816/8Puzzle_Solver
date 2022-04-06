package Model.Recherche;

import Model.Actions.Action;
import Model.Recherche.OuverDS.QueueO;
import Model.Taquin.Taquin;

import java.util.Collections;
import java.util.List;

public class Largeur extends Recherche {

    public Largeur(Taquin etatInitial, Taquin but) {
        super(etatInitial, but);
        ouvert = new QueueO();
    }

    public List<Action> run() {


        ouvert.add(root);
        while (!ouvert.isEmpty()) {

            Noeud noeud = ouvert.remove();
            updateProfondeur(noeud.getProfondeur());

            if (fermee.containsKey(noeud.getTaquin()))
                continue;

            if (isGoal(noeud)) {
                System.out.println("the number of actions to achieve the goal in BFS is = "+noeud.getProfondeur());
                return trackSolution(noeud);
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
        return "BFS";
    }
}
