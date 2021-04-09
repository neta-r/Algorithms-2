package FloydWarshall;

public class bottleProblem {
    public static boolean[][] fillMatrix(int n, int m) { //m >= n, n->a, m->b
        if (n>m){
            int temp=m;
            m=n;
            n=temp;
        }
        int size = (m + 1) * (n + 1);
        boolean[][] mat = new boolean[size][size];
        for (int a = 0; a < n + 1; a++) {
            for (int b = 0; b < m + 1; b++) {
                int i = index(a, b, m+1);
                int j= index(0, b, m+1); //empty left side
                mat[i][j] = true;
                j= index(a, 0, m+1); //empty right side
                mat[i][j] = true;
                j= index(n, b, m+1); //filling left side
                mat[i][j] = true;
                j= index(a, m, m+1); //filling right side
                mat[i][j] = true;
                int min = Math.min(a + b, n);
                j =index(min, a + b - min, m+1); //from right to left
                mat[i][j] = true;
                min = Math.min(a + b, m);
                j =index(a + b - min, min, m+1); //from left to right
                mat[i][j] = true;
            }
        }
        return mat;
    }

    public static int index(int i, int j, int col) {
        return i * col + j;
    }

    public static int numOfTrues(boolean[][] mat, int n, int m) {
        int counter = 0;
        for (int a = 0; a < m + 1; a++) {
            for (int b = 0; b < n + 1; b++) {
                for (int x = 0; x < m + 1; x++) {
                    for (int y = 0; y < n + 1; y++) {
                        int ind = index(a, b, m);
                        int ind2 = index(x, y, m);
                        if (mat[ind][ind2]) counter++;
                    }
                }
            }
        }
        return counter;
    }

    public static boolean numOfTruesAboveNunder(boolean[][] mat, int n, int m) {
        int counterAbove = 0, counterUnder = 0;
        for (int a = 0; a < m + 1; a++) {
            for (int b = 0; b < n + 1; b++) {
                for (int x = 0; x < m + 1; x++) {
                    for (int y = 0; y < n + 1; y++) {
                        int ind = index(a, b, m);
                        int ind2 = index(x, y, m);
                        if (mat[ind][ind2]&& ind>ind2) counterUnder++;
                        if (mat[ind][ind2]&& ind<ind2) counterAbove++;
                    }
                }
            }
        }
        return counterUnder==counterAbove;
    }

    public static void main(String[] args) {
        boolean[][] res = fillMatrix(1, 2);
        System.out.println(numOfTrues(res, 1, 2));
        System.out.println(numOfTruesAboveNunder(res,1,2));
    }
}

