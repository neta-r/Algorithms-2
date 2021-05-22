import java.util.Arrays;
import java.util.Random;

public class MatrixOfOnes {
    public static int[][] matrix(int[][] mat) {
        if (sumMat(mat) == mat.length * mat[0].length) return mat;
        if (sumMat(mat) == mat.length * mat[0].length * -1) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    mat[i][j] = mat[i][j] * -1;
                }
            }
            return mat;
        }
        int[][] help1 = copyOf(mat);
        int[][] help2 = copyOf(mat);
        matrixRow(help1);
        matrixCol(help2);
        if (sumMat(help1) > sumMat(help2)) return help1;
        else return help2;
    }

    public static int[][] copyOf(int[][] mat) {
        int[][] cp = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                cp[i][j] = mat[i][j];
            }
        }
        return cp;
    }

    public static int[][] matrixRow(int[][] mat) {
        int sumCol, sumRow;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == -1) {
                    sumRow = sumRow(mat, i);
                    sumCol = sumCol(mat, j);
                    if (sumRow < sumCol && sumRow < 0) flipRow(mat, i);
                    else if (sumCol <= sumRow && sumCol < 0) flipCol(mat, j);
                }
            }
        }
        return mat;
    }

    public static int[][] matrixCol(int[][] mat) {
        int sumCol, sumRow;
        for (int j = 0; j < mat[0].length; j++) {
            for (int i = 0; i < mat.length; i++) {
                if (mat[i][j] == -1) {
                    sumRow = sumRow(mat, i);
                    sumCol = sumCol(mat, j);
                    if (sumRow < sumCol && sumRow < 0) flipRow(mat, i);
                    else if (sumCol <= sumRow && sumCol < 0) flipCol(mat, j);
                }
            }
        }
        return mat;
    }


    public static int[][] flipRow(int[][] matrix, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = matrix[row][i] * -1;
        }
        return matrix;
    }

    public static int[][] flipCol(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = matrix[i][col] * -1;
        }
        return matrix;
    }

    public static int sumRow(int[][] matrix, int row) {
        int sum = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            sum += matrix[row][i];
        }
        return sum;
    }

    public static int sumCol(int[][] matrix, int col) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][col];
        }
        return sum;
    }

    public static int sumMat(int mat[][]) {
        int sum = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                sum += mat[i][j];
            }
        }
        return sum;
    }

    public static int[][] generateMat(int i, int j) {
        int[][] mat = new int[i][j];
        Random rd = new Random();
        for (int i1 = 0; i1 < mat.length; i1++) {
            for (int j1 = 0; j1 < mat[0].length; j1++) {
                if (rd.nextInt() % 2 == 0) mat[i1][j1] = 1;
                else mat[i1][j1] = -1;
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] mat = generateMat(5, 5);
        matrix(mat);
    }
}
