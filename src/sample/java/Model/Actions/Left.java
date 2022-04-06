package Model.Actions;

import Model.Taquin.Index;
import Model.Taquin.Taquin;

public class Left extends Action {
    public Left() {
        v = -2;
    }

    @Override
    public void action(Taquin taquin) {


        Index videIndex = taquin.getCaseVide();
        Index left = new Index(videIndex.getI(), videIndex.getJ() - 1);
        taquin.change(videIndex, left);
        taquin.setCaseVideIndex(left);

    }

    @Override
    public boolean isActionValide(Index videIndex, int n) {

        return videIndex.getJ() != 0;
    }

    @Override
    public String toString() {
        return "Left";
    }
}
