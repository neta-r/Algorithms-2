package LargestSumOfSunArray.regular;

public class exhaustiveSearch {
    public static int LargestSumOfArray(int[] arr) {
        int max = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > max) max = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {2, -1, 4, 7, 8, -15};
        System.out.println(LargestSumOfArray(arr));
    }
}
