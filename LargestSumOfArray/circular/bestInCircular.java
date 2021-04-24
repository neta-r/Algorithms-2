package LargestSumOfArray.circular;

import LargestSumOfArray.regular.bestSolution;

public class bestInCircular {
    public static int[] largestSumOfArr(int[] arr) {
        int[] ans = new int[3];
        int[] flipArr = new int[arr.length];
        int arrSum=0;
        for (int i=0; i< arr.length; i++){
            arrSum+=arr[i];
            flipArr[i] = arr[i]*-1;
        }
        int[] s1= bestSolution.LargestSumOfArrayTirgul(arr);
        int[] s2=bestSolution.LargestSumOfArrayTirgul(flipArr);
        int sum2 = arrSum-(-s2[0]);
        if (s1[0]>sum2) return s1;
        else {
            s2[0] = sum2;
            s2[2]= (s2[2]-1)%(arr.length);
            s2[1]= (s2[1]+1)%(arr.length);
        }
        return s2;
    }

    public static void main(String[] args) {
        int[] arr = {7,-9,2,1};
        largestSumOfArr(arr);
    }
}
