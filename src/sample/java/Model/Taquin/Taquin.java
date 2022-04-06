package Model.Taquin;

import java.util.concurrent.LinkedBlockingDeque;

public class Taquin implements Cloneable {
    private int[][] taquin;
    private Index caseVideIndex;
    private int size;

    public Taquin(int size) {
        this.taquin = new int[size][size];
        this.size = size;
    }

    public Taquin(String taquin, int size) {
        int[][] mat = new int[size][size];
        int deb = 0;
        int fin = size;
        for (int i = 0; i < size; i++) {
            String str = taquin.substring(deb, fin);
            for (int j = 0; j < size; j++)
                mat[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            deb += size;
            fin += size;
        }
        this.taquin = mat;
        this.size = size;
        this.caseVideIndex = searchCase(0);
    }

    public Taquin(int[][] taquin, int size) {
        this.taquin = taquin;
        this.size = size;
        this.caseVideIndex = searchCase(0);
    }

    public Index getCaseVide() {
        return caseVideIndex;
    }

    public void setCaseVideIndex(Index caseVideIndex) {
        this.caseVideIndex = caseVideIndex;
    }

    public Index searchCase(int v) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                if (taquin[i][j] == v)
                    return new Index(i, j);
        }
        return null;
    }

    public int[][] getTaquin() {
        return taquin;
    }

//    //public void setCaseVideIndex(Index caseVideIndex) {
//        this.taquin = taquin;
//    }

    public void setTaquin(int[][] taquin) {
        this.taquin = taquin;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void change(Index from, Index To) {

        int t = taquin[To.getI()][To.getJ()];
        taquin[To.getI()][To.getJ()] = 0;
        taquin[from.getI()][from.getJ()] = t;
    }

    public void afficherTaquin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(taquin[i][j] + "\t");
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taquin taquin1 = (Taquin) o;
        return equalTaquin(taquin1.getTaquin());
    }

    @Override
    public int hashCode() {

        int result = Integer.parseInt(getTaquinStr()) % 362897;//<--why 362897, known that 9!=362880 && why not integer.parseInt
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Taquin taquin = (Taquin) super.clone();
        taquin.setTaquin(copyTaquin());
        return taquin;
    }

    public int[][] copyTaquin() {
        int[][] mat = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mat[i][j] = taquin[i][j];
            }
        }
        return mat;
    }

    public boolean equalTaquin(int[][] mat) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mat[i][j] != taquin[i][j])
                    return false;
            }
        }
        return true;
    }

    public String getTaquinStr() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stringBuilder.append(taquin[i][j]);
            }
        }
        return stringBuilder.toString();
    }
    public static  int[][] getMatrixFromStr(String taquin,int size){
        int[][] mat = new int[size][size];
        int deb = 0;
        int fin = size;
        for (int i = 0; i < size; i++) {
            String str = taquin.substring(deb, fin);
            for (int j = 0; j < size; j++)
                mat[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            deb += size;
            fin += size;
        }
        return mat;
    }
    @Override
    public String toString() {
        this.afficherTaquin();
        return "caseVideIndex=" + caseVideIndex +
                ", size=" + size +
                '}';
    }
}
