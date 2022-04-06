package Model.Recherche;

import Model.Actions.Action;
import Model.Actions.ActionVide;
import Model.Taquin.Taquin;

import java.util.Objects;

public class Noeud {
    private Taquin taquin;
    private Action action;
    private Noeud pere;
    private int profondeur;
    private int score;

    public Noeud(Taquin taquin) {
        this.taquin = taquin;
        this.action = new ActionVide();
        this.profondeur = 0;
    }

    public Noeud(Taquin taquin, Action action, Noeud pere) {
        this.taquin = taquin;
        this.action = action;
        this.pere = pere;
        this.profondeur = pere.getProfondeur() + 1;
    }

    public void appliquerAction(Action action) {
        action.action(taquin);
    }

    public boolean validAction(Action action) {
        return action.isActionValide(taquin.getCaseVide(), taquin.getSize());
    }

    public void afficherNoeud() {
        System.out.println(profondeur);
        taquin.afficherTaquin();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noeud noeud = (Noeud) o;
        return getTaquin().equals(noeud.getTaquin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaquin());
    }

    public Taquin getTaquin() {
        return taquin;
    }

    public void setTaquin(Taquin taquin) {
        this.taquin = taquin;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Noeud getPere() {
        return pere;
    }

    public void setPere(Noeud pere) {
        this.pere = pere;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Noeud{" +
                "taquin=" + taquin +
                ", action=" + action +
                ", pere=" + pere +
                ", profondeur=" + profondeur +
                ", score=" + score +
                '}';
    }
}
