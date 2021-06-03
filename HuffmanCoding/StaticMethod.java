package HuffmanCoding;

import java.util.ArrayList;
import java.util.Stack;

class container {
    int num;
    Stack<Integer> s;

    public container(int num) {
        this.num = num;
        this.s = new Stack<>();
    }
}

public class StaticMethod {
    static String getPath(int index, int[][] mat) {
        String ans = "";
        boolean flag = true;
        int currIndex = index;
        while (flag) {
            int lastIndex = currIndex;
            currIndex= mat[currIndex][1];
            if (mat[currIndex][2] == lastIndex) ans = "0"+ans; //right son to the parent
            if (mat[currIndex][3] == lastIndex) ans = "1"+ans; //left son to the parent
            if (mat[currIndex][1] == 0) flag = false;
        }
        return ans;
    }

    public static String[][] huffman(char[] chars, int[] percentage) {
        int[][] mat = getMat(chars, percentage);
        String[][] ans = new String[2][chars.length];
        for (int i = 0; i < chars.length; i++) {
            ans[0][i] = String.valueOf(chars[i]);
            ans[1][i] = getPath(i, mat);
        }
        return ans;
    }

    public static int[][] getMat(char[] chars, int[] percentage) {
        int[][] mat = new int[2 * chars.length - 1][4];
        if (chars.length == percentage.length) {
            // visited , freq , father , left , right
            ArrayList<Integer> p = new ArrayList<>();
            for (int i = 0; i < percentage.length; i++) {
                p.add(percentage[i]);
                mat[i][0] = percentage[i];
            }
            int index = percentage.length;
            for (int i = 0; i < chars.length - 1; i++) {
                int[] mins = find2min(p, mat);
                mat[mins[2]][1] = index;
                mat[mins[3]][1] = index;
                mat[index][0] = mins[0] + mins[1];
                mat[index][2] = mins[3];
                mat[index][3] = mins[2];
                p.add(mins[0] + mins[1]);
                index++;
            }
        }
        return mat;
    }

    private static int[] find2min(ArrayList<Integer> array, int[][] mat) { //assuming the array has at least 2 arguments
        int[] arr = new int[array.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = array.get(i);
        int size = arr.length, index = 0;
        boolean even = true;
        if (arr.length % 2 == 1) {
            size = arr.length - 1;
            even = false;
        }
        container[] a = new container[size + 1];
        for (int i = 0; i < size; i += 2) {
            if (arr[i] < arr[i + 1]) {
                container temp = new container(arr[i]);
                temp.s.add(arr[i + 1]);
                a[index] = temp;
                index++;
            } else {
                container temp = new container(arr[i + 1]);
                temp.s.add(arr[i]);
                a[index] = temp;
                index++;
            }
        }
        size /= 2;
        while (size > 1) {
            boolean f = false;
            int s = size;
            if (size % 2 != 0) {
                f = true;
                s--;
            }
            index = 0;
            for (int i = 0; i < s; i += 2) {
                if (a[i].num < a[i + 1].num) {
                    a[i].s.add(a[i + 1].num);
                    a[index] = a[i];
                    index++;
                } else {
                    a[i + 1].s.add(a[i].num);
                    a[index] = a[i + 1];
                    index++;
                }
            }
            if (f) {
                if (a[0].num < a[s].num) {
                    a[0].s.add(a[s].num);
                    a[index] = a[0];
                } else {
                    a[s].s.add(a[0].num);
                    a[index] = a[s];
                }
            }
            size /= 2;
        }
        if (!even) {
            if (arr[arr.length - 1] < a[0].num) {
                container temp = new container(arr[arr.length - 1]);
                temp.s.add(a[0].num);
                temp.s.addAll(a[0].s);
                a[0] = temp;
            } else {
                a[0].s.add(arr[arr.length - 1]);
            }
        }
        int[] min = new int[4];
        min[0] = a[0].num;
        int minn = a[0].s.get(0);
        for (int i = 1; i < a[0].s.size(); i++) {
            if (a[0].s.get(i) < minn) minn = a[0].s.get(i);
        }
        min[1] = minn;
        array.removeIf(element -> element == min[0] || element == min[1]);
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][0] == min[0]) {
                min[2] = i;
            }
            if (mat[i][0] == min[1]) {
                min[3] = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] percentage = {10, 18, 24, 48};
        char[] chars = {'a', 'b', 'c', 'd'};
        huffman(chars, percentage);
    }
}