package LargestSumOfSunArray.circular;

import LargestSumOfSunArray.regular.bestSolution;

public class doubleArray {
    public static int[] largestSumOfArr(int[] arr) {
        int[] temp = new int[3];
        int[] ans = new int[3];
        int [] help = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j=0; j<arr.length; j++){
                help[j]=arr[(i+j)%arr.length];
            }
            temp= bestSolution.LargestSumOfArrayTirgul(help);
            if (temp[0]>ans[0]) {
                ans[0]= temp[0];
                ans[1]=(temp[1]+i)% arr.length;
                ans[2]=(temp[2]+i)% arr.length;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] arr = {7,-2,4,5};
        int ans []=largestSumOfArr(arr);
    }
}
