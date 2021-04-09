package LargestSumOfSunArray.regular;

public class bestSolution {
    public static int LargestSumOfArray(int[] arr) {
        int max=Integer.MIN_VALUE, i=0, sum=0;
        while (arr[i]<0){
            i++;
            if (arr[i]>max) max=arr[i];
        }
        if (i+1==arr.length) return max;
        for (int j=i; j<arr.length;  j++){
            sum+=arr[j];
            if (sum<0) sum=0;
            if (sum>max) max=sum;
        }
        return max;
    }


    public static int[] LargestSumOfArrayTirgul(int[] arr) {
        int[] helpArr = new int[arr.length];
        int maxSum=0, from=0, to=0;
        int[] ans = new int[3];
        helpArr[0] = arr[0];
        for (int i=1; i< arr.length; i++){
            if (helpArr[i-1]<0) helpArr[i]=arr[i];
            else helpArr[i]=helpArr[i-1]+arr[i];
            if (helpArr[i]>maxSum) {
                maxSum=helpArr[i];
                to = i;
            }
        }
        for (int i=to; i>=0; i--){
            if (helpArr[i]>0) from=i;
            else break;
        }
        ans[0]= maxSum;
        ans[1]= from;
        ans[2]= to;
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,7,3,-13,2,1,10,-2,1,-20};
        int[] ans = LargestSumOfArrayTirgul(arr);
        //System.out.println(LargestSumOfArrayTirgul(arr));
    }
}
