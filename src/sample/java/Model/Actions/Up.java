package Model.Actions;

import Model.Taquin.Index;
import Model.Taquin.Taquin;

public class Up extends Action {
    public Up() {
        v = 1;
    }

    public void action(Taquin taquin) {

        Index videIndex = taquin.getCaseVide();
        Index Up = new Index(videIndex.getI() - 1, videIndex.getJ());
        taquin.change(videIndex, Up);
        taquin.setCaseVideIndex(Up);


    }

    @Override
    public boolean isActionValide(Index videIndex, int n) {

        return videIndex.getI() != 0;
    }

    @Override
    public String toString() {
        return "Up";
    }
}
