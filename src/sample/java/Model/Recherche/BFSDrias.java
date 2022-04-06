package Model.Recherche;

import Model.Actions.Action;
import Model.Recherche.OuverDS.QueueO;
import Model.Taquin.Taquin;

import java.util.Collections;
import java.util.List;

public class BFSDrias extends Recherche {
    public BFSDrias(Taquin etatInitial, Taquin but) {
        super(etatInitial, but);
        ouvert = new QueueO();
    }

    @Override
    public List<Action> run() {


        ouvert.add(root);
        while (!ouvert.isEmpty()) {

            Noeud noeud = ouvert.remove();
            updateProfondeur(noeud.getProfondeur());

            fermee.put(noeud.getTaquin(), noeud);

            for (Action action : getValidActions(noeud)) {
                Noeud newNoeud = createNoeud(noeud, action);

                if (fermee.containsKey(newNoeud.getTaquin()))
                    continue;
                if (ouvert.contain(newNoeud))
                    continue;

                if (isGoal(newNoeud))
                    return trackSolution(newNoeud);
                ouvert.add(newNoeud);
            }
        }

        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "BFSDrias";
    }
}
