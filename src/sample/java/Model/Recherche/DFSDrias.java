package Model.Recherche;

import Model.Actions.Action;
import Model.Recherche.OuverDS.StackOv;
import Model.Taquin.Taquin;

import java.util.Collections;
import java.util.List;

public class DFSDrias extends Recherche {
    private final int maxProfondeur;

    public DFSDrias(Taquin etatInitial, Taquin but, int maxProfondeur) {
        super(etatInitial, but);
        this.maxProfondeur = maxProfondeur;
        ouvert = new StackOv();
    }

    public List<Action> run() {

        System.out.println();
        ouvert.add(root);

        while (!ouvert.isEmpty()) {

            Noeud noeud = ouvert.remove();
            updateProfondeur(noeud.getProfondeur());

            fermee.put(noeud.getTaquin(), noeud);


            if (profondeur == maxProfondeur){

                //System.out.println(profondeur+" Sorry, the solution doesn't exist within this deep in the tree "+ouvert.isEmpty());
                //System.out.println("the stack is Empty : "+ouvert.isEmpty());
                continue;
            }



            for (Action action : getValidActions(noeud)) {
                Noeud newNoeud = createNoeud(noeud, action);

                if (fermee.containsKey(newNoeud.getTaquin()))
                    continue;

                if (ouvert.contain(newNoeud))
                    continue;

                if (isGoal(newNoeud)){
                    System.out.println("the number of actions to achieve the goal in DFSDrias is ="+newNoeud.getProfondeur());
                    return trackSolution(newNoeud);
                }


                ouvert.add(newNoeud);
            }
        }

        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "DFSDrias";
    }
}
