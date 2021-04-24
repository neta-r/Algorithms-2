package LargestSumOfMatrix;

public class dynamicSolution {
    public static int LargestSumOfMatrix(int[][] mat) {
        int max = 0, tempSum;
        int[][] helpMat = getHelpMatrix(mat);
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    for (int p = i; p < mat.length; p++) {
                        for (int q = j; q < mat[0].length; q++) {
                            tempSum = clacSum(helpMat, i, j, p, q); // O(n^2)
                            if (tempSum > max) max = tempSum;
                        }
                    }
                }
            }
        return 0;
    }

    public static int clacSum(int[][] help, int i, int j, int p, int q) {
        if (i == 0 && j == 0) return help[p][q];
        else if (i == 0 && j > 0) return help[p][q] - help[p][j - 1];
        else if (i > 0 && j == 0) return help[p][q] - help[i - 1][q];
        return help[p][q] - help[i-1][q] - help[p][j-1] + help[i-1][j-1];
    }


    public static int[][] getHelpMatrix(int[][] mat) {
        int[][] help = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                help[i][j] = exhaustiveSearch.clacSum(mat, 0, 0, i, j);
            }
        }
        return help;
    }


    public static void main(String[] args) {
        int[][] mat = {{1,2,3},
                {-3,4,5},
                {-1,-2,-4}};
        System.out.println(LargestSumOfMatrix(mat));
    }
}