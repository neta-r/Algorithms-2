package LargestSumOfMatrix;

import java.util.Arrays;

public class best {

    public static int LargestSumOfMatrix(int[][] mat) {
        int max = 0, tempSum;
        int[] helpArr = new int[mat[0].length];
        for (int i=0; i<mat.length; i++){
            for (int j=i+1; j<mat.length+1; j++){
                Arrays.fill(helpArr, 0);
                for (int k=i; k<j; k++){
                    for (int l=0; l<helpArr.length; l++){
                        helpArr[l] += mat[k][l];
                    }
                }
                tempSum=LargestSumOfArray.regular.bestSolution.LargestSumOfArray(helpArr);
                if (tempSum>max) max=tempSum;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[][] mat = {{2,1,-3,-4,5},
                {0,6,3,4,1},
                {2,-2,-1,4,5},
                {-3,3,1,0,3}};
        LargestSumOfMatrix(mat);
        //System.out.println(LargestSumOfMatrix(mat));
    }
}