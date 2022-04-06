package Model.Actions;

import Model.Taquin.Index;
import Model.Taquin.Taquin;

public class Down extends Action {
    public Down() {
        v = -1;
    }

    @Override
    public void action(Taquin taquin) {

        Index videIndex = taquin.getCaseVide();
        Index down = new Index(videIndex.getI() + 1, videIndex.getJ());
        taquin.change(videIndex, down);
        taquin.setCaseVideIndex(down);
    }

    @Override
    public boolean isActionValide(Index videIndex, int n) {

        return videIndex.getI() != n - 1;
    }

    @Override
    public String toString() {
        return "Down";
    }
}
