package LargestSumOfSunArray.regular;

import java.time.LocalTime;
import java.util.Random;

public class dynamicSolution {
    public static int LargestSumOfArrayRows(int[] arr) {
        int[][] mat = new int[arr.length][arr.length];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            mat[i][i] = arr[i];
            if (mat[i][i] > max) max = mat[i][i];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                mat[i][j] = mat[i][j - 1] + mat[j][j];
                if (mat[i][j] > max) max = mat[i][j];
            }
        }

        return max;
    }

    public static int LargestSumOfArrayCols(int[] arr) {
        int[][] mat = new int[arr.length][arr.length];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            mat[i][i] = arr[i];
            if (mat[i][i] > max) max = mat[i][i];
        }
        for (int k = 0; k < arr.length; k++) {
            for (int i = 0, j = i + 1 + k; j < arr.length; j++, i++) {
                mat[i][j] = mat[i + 1][j] + mat[i][i];
                if (mat[i][j] > max) max = mat[i][j];
            }
        }

        return max;
    }

    public static int LargestSumOfArrayUsingArr(int[] arr) {
        int[][] mat = new int[arr.length][arr.length];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            mat[i][i] = arr[i];
            if (mat[i][i] > max) max = mat[i][i];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                mat[i][j] = mat[i][j - 1] + arr[j];
                if (mat[i][j] > max) max = mat[i][j];
            }
        }

        return max;
    }
    public static int[] randArr() {
        Random ran = new Random();
        int len = 10000;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = ran.nextInt(50);
        }
        return arr;
    }

    public static void main(String[] args) {
        long start, end, totalrow = 0, totalcol = 0, totalarr=0;
        for (int i = 0; i < 2; i++) {
            int[] arr = randArr();
            start = System.currentTimeMillis();
            LargestSumOfArrayRows(arr);
            end = System.currentTimeMillis();
            totalrow =totalrow +end-start;
            start = System.currentTimeMillis();
            LargestSumOfArrayCols(arr);
            end = System.currentTimeMillis();
            totalcol =totalcol +end-start;
            start = System.currentTimeMillis();
            LargestSumOfArrayUsingArr(arr);
            end = System.currentTimeMillis();
            totalarr =totalarr +end-start;
        }
        System.out.println("rows: " + totalrow + " cols: " + totalcol+ " totalarr: " + totalarr);
    }
}
