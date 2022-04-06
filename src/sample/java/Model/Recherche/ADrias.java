package Model.Recherche;

import Model.Actions.Action;
import Model.Recherche.Heuristique.Heuristique;
import Model.Recherche.OuverDS.Priority;
import Model.Taquin.Taquin;

import java.util.Collections;
import java.util.List;

public class ADrias extends Recherche {

    Heuristique heuristique;

    public ADrias(Taquin etatInitial, Taquin but, Heuristique heuristique) {
        super(etatInitial, but);
        this.heuristique = heuristique;
        ouvert = new Priority();
    }

    public List<Action> run() {


        ouvert.add(root);
        while (!ouvert.isEmpty()) {

            Noeud noeud = ouvert.remove();
            updateProfondeur(noeud.getProfondeur());


            fermee.put(noeud.getTaquin(), noeud);

            if (isGoal(noeud)) {
                System.out.println(noeud.getProfondeur());
                return trackSolution(noeud);
            }


            for (Action action : getValidActions(noeud)) {
                Noeud newNoeud = createNoeud(noeud, action);
                ouvert.remove(newNoeud);
                ouvert.add(newNoeud);
                Noeud ex = fermee.get(newNoeud.getTaquin());
                if (ex != null && newNoeud.getScore() < ex.getScore()) {
                    ex.setScore(newNoeud.getScore());
                    ex.setPere(noeud);
                    ex.setProfondeur(noeud.getProfondeur() + 1);
                }
            }


        }

        return Collections.emptyList();
    }


    @Override
    public Noeud createNoeud(Noeud pere, Action action) {

        Noeud noeud = super.createNoeud(pere, action);
        evaluate(noeud);
        return noeud;

    }

    public void evaluate(Noeud noeud) {
        int score = noeud.getProfondeur() + heuristique.evaluate(noeud, but);
        noeud.setScore(score);
    }

    @Override
    public String toString() {
        return "A* Drias";
    }
}
