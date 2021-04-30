package LargestSumOfMatrix;

public class otherSuperBestSolution {
    public static int LargestSumOfMatrix(int[][] mat) {
        if (mat.length<=mat[0].length) return LargestSumOfMatrixCols(mat);
        else return LargestSumOfMatrixRows(mat);
    }

    public static int LargestSumOfMatrixCols(int[][] mat) {
        int max = mat[0][0], tempSum;
        int[][] helpArr = new int[mat.length][mat[0].length + 1];
        for (int i=0 ;i<mat.length; i++) {
            for (int j = 1; j < helpArr[0].length; j++) {
                helpArr[i][j] = helpArr[i][j - 1] + mat[i][j - 1];
            }
        }
        for (int jbeg=0 ; jbeg<mat.length-1; jbeg++){
            for (int jend=jbeg+1 ; jend<mat.length; jend++){
                tempSum=0;
                for (int i=0; i<mat[0].length; i++){
                    tempSum+=helpArr[i][jend]-helpArr[i][jbeg];
                    if (tempSum>max) max=tempSum;
                    else if (tempSum<0) tempSum=0;
                }
            }
        }
        return max;
    }

    public static int LargestSumOfMatrixRows(int[][] mat) {
        int max = mat[0][0], tempSum;
        int[][] helpArr = new int[mat.length +1][mat[0].length];
        for (int i=0 ;i<mat[0].length; i++) {
            for (int j = 1; j < helpArr.length; j++) {
                helpArr[j][i] = helpArr[j-1][i] + mat[j-1][i];
            }
        }
        for (int ibeg=0 ; ibeg<=mat[0].length-1; ibeg++){
            for (int iend=ibeg+1 ; iend<=mat[0].length; iend++){
                tempSum=0;
                for (int j=0; j<mat.length; j++){
                    tempSum+=helpArr[iend][j]-helpArr[ibeg][j];
                    if (tempSum>max) max=tempSum;
                    else if (tempSum<0) tempSum=0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,2,3},
                {-3,4,5},
                {-1,-2,-4}};
        LargestSumOfMatrix(mat);
    }
}