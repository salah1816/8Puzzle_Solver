package Model.Taquin;

import Model.Main;

public class Index {
    private final int i;
    private final int j;

    public Index(int i, int j) {
        this.i = i;
        this.j = j;
    }
    public int cellNumber(){
        return i* Main.getN()+ j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}

