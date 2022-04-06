package Model.Actions;

import Model.Taquin.Index;
import Model.Taquin.Taquin;

import java.util.Objects;

public abstract class Action {
    protected int v;

    public abstract void action(Taquin taquin);


    public abstract boolean isActionValide(Index videIndex, int n);


    public int getV() {
        return v;
    }

    public boolean eq(Action action) {
        return v == -action.getV();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;// to achieve this point we get (this != o && o!= null && getClass() = o.getClass())
        return getV() == action.getV();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getV());
    }
}
