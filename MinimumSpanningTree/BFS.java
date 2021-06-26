package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    enum color {White, Gray, Black}

    public static int[][] BFS(ArrayList<Integer>[] graph, int startV) {
        color[] colors = new color[graph.length];
        int[] distances = new int[graph.length];
        int[] fathers = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            colors[i] = color.White;
            distances[i] = Integer.MAX_VALUE;
            fathers[i] = -1;
        }
        colors[startV] = color.Gray;
        distances[startV] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startV);
        while (!queue.isEmpty()){
            int u = queue.remove();
            for (int v : graph[u]){
                if (colors[v]==color.White){
                    colors[v] = color.Gray;
                    distances[v] = distances[u]+1;
                    fathers[v] = u;
                    queue.add(v);
                }
            }
            colors[u] = color.Black;
        }
        int [][] res = new int[2][graph.length];
        for (int i=0; i< graph.length; i++){
            res[0][i] = distances[i];
            res[1][i] = fathers[i];
        }
        return res;
    }

    public static boolean isConnected (ArrayList<Integer>[] graph){
        int[][] res= BFS(graph,0);
        for (int i = 0; i < graph.length; i++) {
            if (res[0][i] == Integer.MAX_VALUE) return false;
        }
        return true;
    }

    public static int numOfCCS (ArrayList<Integer>[] graph) {
        int[] CCS = CCS(graph);
        int max=-1;
        for (int i = 0; i < CCS.length; i++) {
            if (CCS[i] > max) max=CCS[i];
        }
        return max;
    }

    public static int[] CCS(ArrayList<Integer>[] graph){
        int counter= 1;
        int currVertex = 0;
        int [] CCS = new int[graph.length];
        boolean keepGoing = true;
        while (keepGoing) {
            keepGoing = false;
            int[][] res = BFS(graph, currVertex);
            for (int i = 0; i < graph.length; i++) {
                if (res[0][i] != Integer.MAX_VALUE) {
                    CCS[i] = counter;
                }
                else{
                    if (CCS[i] == 0) {
                        keepGoing=true;
                        currVertex=i;
                    }
                }
            }
            counter++;
        }
        return CCS;
    }

    public static int distOfTwoVertices(ArrayList<Integer>[] graph, int one, int two){
        int [][] res = BFS(graph,one);
        return res[0][two];
    }

    public static String path(ArrayList<Integer>[] graph, int one, int two){
        int [][] res = BFS(graph,one);
        String ans = "-> "+ two;
        int currVertex = two;
        while (res[1][currVertex]!=one){
            ans= "-> " + res[1][currVertex] + ans;
            currVertex= res[1][currVertex];
        }
        ans= ""+one + ans;
        return ans;
    }

    public static int[] longestDist (int[] distances){
        int max =-1;
        int v= -1;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] > max && distances[i]!=Integer.MAX_VALUE) {
                max=distances[i];
                v=i;
            }
        }
        return new int[]{max, v};
    }

    public static int diameterDumbWay(ArrayList<Integer>[] graph){
        int diameter = -1;
        int tmp;
        for (int i = 0; i < graph.length; i++) {
            int[][] res = BFS(graph,i);
            tmp = longestDist(res[0])[0];
            if (tmp>diameter) diameter=tmp;
        }
        return diameter;
    }

    public static int diameter(ArrayList<Integer>[] graph){
        int res[][] = BFS(graph,1);
        int u = longestDist(res[0])[1];
        res = BFS(graph,u);
        return longestDist(res[0])[0];
    }

    public static void main(String[] args) {
        ArrayList<Integer>[] graph = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[1].add(5);
        graph[1].add(2);
        graph[2].add(1);
        graph[2].add(6);
        graph[3].add(6);
        graph[3].add(7);
        graph[3].add(4);
        graph[4].add(3);
        graph[4].add(8);
        graph[5].add(1);
        graph[6].add(2);
        graph[6].add(3);
        graph[6].add(7);
        graph[7].add(6);
        graph[7].add(6);
        graph[7].add(8);
        graph[8].add(7);
        graph[8].add(4);
        BFS(graph,2);

        ArrayList<Integer>[] graph2 = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            graph2[i] = new ArrayList<>();
        }
        graph2[2].add(3);
        graph2[3].add(2);
        System.out.println(isConnected(graph));
        CCS(graph2);
        System.out.println(numOfCCS(graph));
        System.out.println(numOfCCS(graph2));
        System.out.println(distOfTwoVertices(graph,5,8));
        path(graph,5,8);
        diameterDumbWay(graph);
        System.out.println(diameter(graph));
    }
}
