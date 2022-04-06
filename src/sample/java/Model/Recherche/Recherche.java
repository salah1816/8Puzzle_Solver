package Model.Recherche;

import Model.Actions.*;
import Model.Recherche.OuverDS.Ouvert;
import Model.Runnable;
import Model.Taquin.Taquin;

import java.util.*;

public abstract class Recherche implements Runnable {
    protected static List<Action> actions = Arrays.asList(new Up(), new Down(), new Right(), new Left());
    protected Noeud root;
    protected Taquin but;
    protected Ouvert ouvert;
    protected Map<Taquin, Noeud> fermee = new HashMap<>();
    protected int profondeur = 0;
    protected List<Action> solution = Collections.emptyList();

    public Recherche(Taquin etatInitial, Taquin but) {
        this.root = new Noeud(etatInitial);
        this.but = but;
        System.out.println("Le but =");
        System.out.println(but);
    }


    public abstract List<Action> run();

    public boolean isGoal(Noeud noeud) {
        Taquin taquin = noeud.getTaquin();
        return Arrays.deepEquals(taquin.getTaquin(), but.getTaquin());
    }

    public List<Action> trackSolution(Noeud goal) {
        List<Action> actions = new ArrayList<>(goal.getProfondeur());

        while (goal.getPere() != null) {
            actions.add(0, goal.getAction());
            goal = goal.getPere();
        }
        solution = actions;
        return actions;
    }

    public List<Action> getSolution() {
        return solution;
    }
    public static List<Action> getContreDirectionSolution(List<Action> actions){
        List<Action> reverse = new ArrayList<>();
        for (Action action:
             actions) {
            if (action instanceof Left) reverse.add(new Right());
            else if (action instanceof Right) reverse.add(new Left());
            else if (action instanceof Down) reverse.add(new Up());
            else reverse.add(new Down());
        }
        return reverse;
    }

    public List<Action> getValidActions(Noeud noeud) {

        List<Action> validActions = new ArrayList<>();
        for (Action action : actions) {
            if (noeud.validAction(action) && !action.eq(noeud.getAction()))
                validActions.add(action);
        }

        return validActions;
    }

    public Noeud createNoeud(Noeud pere, Action action) {
        Taquin taquin = null;
        try {
            taquin = (Taquin) pere.getTaquin().clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        action.action(taquin);

        Noeud noeud = new Noeud(taquin, action, pere);

        return noeud;
    }


    public void reset() {
        ouvert.clear();
        fermee.clear();
        profondeur = 0;
    }


    public void updateProfondeur(int p) {

        profondeur = p;

        // System.out.println("Profondeur: "+profondeur+" Ouvert: "+ouvert.size()+" Fermer: "+fermer.size());

    }

    public int getOuvertSize() {
        return ouvert.size();
    }

    public int getFermerSize() {
        return fermee.size();
    }

    public Noeud getRoot() {
        return root;
    }
}
