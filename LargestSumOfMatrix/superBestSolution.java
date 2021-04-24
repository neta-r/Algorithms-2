package LargestSumOfMatrix;

public class superBestSolution {
    public static int LargestSumOfMatrix(int[][] mat) {
        if (mat.length<=mat[0].length) return LargestSumOfMatrixCols(mat);
        else return LargestSumOfMatrixRows(mat);
    }

    public static int LargestSumOfMatrixCols(int[][] mat) {
        int max = mat[0][0], tempSum;
        int[][] helpMat = dynamicSolution.getHelpMatrix(mat);
        for (int i = 0; i < mat.length; i++) {
            for (int p = i; p < mat.length; p++) {
                int j=0;
                for (int q = 0; q <mat[0].length ; q++) {
                    tempSum = dynamicSolution.clacSum(helpMat, i, j, p, q); // O(n^2)
                    if (tempSum<0) j=q+1;
                    if (tempSum > max) max = tempSum;
                }
            }
        }
        return max;
    }

    public static int LargestSumOfMatrixRows(int[][] mat) {
        int max = mat[0][0], tempSum;
        int[][] helpMat = dynamicSolution.getHelpMatrix(mat);
        for (int j = 0; j < mat.length; j++) {
            for (int q = j; q < mat.length; q++) {
                int i=0;
                for (int p = 0; p <mat[0].length ; p++) {
                    tempSum = dynamicSolution.clacSum(helpMat, i, j, p, q); // O(n^2)
                    if (tempSum<0) i=p+1;
                    if (tempSum > max) max = tempSum;
                }
            }
        }
        return max;

    }
    public static void main(String[] args) {
        int[][] mat = {{1,2,3},
                {-3,4,5},
                {-1,-2,-4}};
        LargestSumOfMatrixRows(mat);
        //System.out.println(LargestSumOfMatrix(mat));
    }
}
