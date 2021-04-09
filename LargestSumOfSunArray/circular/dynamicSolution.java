package LargestSumOfSunArray.circular;

public class dynamicSolution {
    public static int[] largestSumOfArr (int[] arr){
        int[] ans = new int[3];
        int[][] mat = new int[arr.length][arr.length];
        int max = 0,sum=0, ii, jj, from=0, to=0;
        for (int i = 0; i < arr.length; i++) {
            mat[i][i] = arr[i];
            sum+=arr[i];
            if (mat[i][i] > max) max = mat[i][i];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                mat[i][j] = mat[i][j - 1] + mat[j][j];
                if (mat[i][j] > max){
                    max = mat[i][j];
                    from=i;
                    to=j;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ii = (j-1)%(arr.length);
                jj= (i+1)%(arr.length);
                if (ii==i && jj==j) mat[j][i]=sum;
                else mat[j][i]=sum-mat[jj][ii];
                if (mat[i][j] > max){
                    max = mat[i][j];
                    from=j;
                    to=i;
                }
            }
        }
        ans[0] = max;
        ans[1]= from;
        ans[2]= to;
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {7,-9,2,1};
        int[] ans = largestSumOfArr(arr); }
}
