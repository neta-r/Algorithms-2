package LargestSumOfMatrix;

public class exhaustiveSearch {
    public static int LargestSumOfMatrix(int[][] mat) {  // O(n^6)
        int max=0, tempSum=0;
        for (int i=0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                for (int p = i; p < mat.length; p++) {
                    for (int q = j; q < mat[0].length; q++) {
                        tempSum = clacSum(mat, i, j, p, q); // O(n^2)
                        if (tempSum > max) max = tempSum;
                    }
                }
            }
        }
        return max;
    }

    public static int clacSum(int[][] mat, int ii, int jj, int pp, int qq){
        int sum =0;
        for (int i=ii; i <= pp; i++) {
            for (int j = jj; j <= qq; j++) {
                sum+=mat[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] mat = {{0,-2,-7,0},
                {9,2,-6,2},
                {-4,1,-4,1},
                {-1,8,0,-2}};
        System.out.println(LargestSumOfMatrix(mat));
    }
}
