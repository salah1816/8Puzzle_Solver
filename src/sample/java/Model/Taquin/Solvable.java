package Model.Taquin;

public class Solvable {
    private final int n;

    public Solvable(int n) {
        this.n = n;
    }

    protected int getInvCount(int[][] arr) {
        System.out.println();
        int inv_count = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++) {
                System.out.println(arr[i][j]);
                System.out.println(arr[j][i]);
                if (arr[j][i] > 0 && arr[j][i] > arr[i][j])
                    inv_count++;
            }
        System.out.println();
        System.out.println(inv_count);
        return inv_count;
    }


    public boolean isSolvable(Taquin taquin) {
        taquin.afficherTaquin();
        System.out.println(n);
        int invCount = getInvCount(taquin.getTaquin());
        System.out.println(invCount);
        // if n is odd check inv if is even
        if (n % 2 != 0)
            return invCount % 2 == 0;

        // else n is even check for casevide index
        Index caseVide = taquin.getCaseVide();
        int i = n - caseVide.getI();
        if (i % 2 == 0)
            return invCount % 2 == 1;
        else
            return invCount % 2 == 0;


    }

}
