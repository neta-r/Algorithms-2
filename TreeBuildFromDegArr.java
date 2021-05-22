import java.util.Arrays;

public class TreeBuildFromDegArr {
    public static int[] TreeBuild (int[] deg){
        int[] Tree = new int[deg.length];
        int sum = 0, j=-1;
        for (int i = 0; i < deg.length; i++) sum+=deg[i];
        if (sum%2!=0 || (sum/2)+1!=deg.length){ //No such tree
            Arrays.fill(Tree,-1);
            return Tree;
        }
        Arrays.sort(deg);
        for (int i = 0; i < deg.length; i++) {
            if (deg[i] > 1){
                j = i;
                break;
            }
        }
        for (int i = 0; i < deg.length-2; i++) {
            Tree[i] = j;
            deg[j] -- ;
            if (deg[j] == 1) j++;
        }
        Tree[deg.length-2] = deg.length-1;
        return Tree;
    }

    public static void main(String[] args) {
        int[] deg1 = {1,1,1,1,2,2,3,4};
        int[] deg2 = {1,1,1,2,2,3,4};
        int[] deg3 = {1,1,1,1,2,2,3,3};
        TreeBuild(deg1);
        TreeBuild(deg2);
        TreeBuild(deg3);
    }
}
