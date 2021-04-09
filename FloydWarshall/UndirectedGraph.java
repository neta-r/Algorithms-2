package FloydWarshall;

import java.util.ArrayList;

public class UndirectedGraph { //1
    public static boolean[][] floyd_warshall(boolean[][] graph) {
        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    graph[i][j] = (graph[i][j] || graph[i][k] && graph[k][j]);
                }
            }
        }
        for (int i = 0; i < graph.length; i++) {
            graph[i][i] = false;
        }
        return graph;
    }

    public static boolean isTherePath (boolean[][] graph, int v_i, int v_j){ //2
        if (v_i> graph.length||v_j> graph.length||v_i<0 || v_j<0) return false;
        floyd_warshall(graph);
        return graph[v_i][v_j];
    }


    public static boolean isConnected_inefficient(boolean[][] graph) { //O(n^2) //3
        floyd_warshall(graph);
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (!graph[i][j] && i != j) return false;
            }
        }
        return true;
    }

    public static int[] isConnected_efficient(boolean[][] graph) { //3
        floyd_warshall(graph);
        int[] ccs = new int[graph.length];
        int n = 0;
        for (int i = 0; i < graph.length; i++) {
            if (ccs[i] == 0) {
                n++;
                ccs[i] = n;
                for (int j = i + 1; j < graph.length; j++) {
                    if (i != j && graph[i][j] && graph[j][i] && ccs[j] == 0) {
                        ccs[j] = n;
                    }
                }
            }
        }
        return ccs;
    }

    public static int num_of_ccs(boolean[][] graph){ //4
        int[] arr=isConnected_efficient(graph);
        return arr.length;
    }

    public static ArrayList<ArrayList<Integer>> ccs(boolean[][] graph) { //5
        ArrayList<ArrayList<Integer>> ccs= new ArrayList<>();
        floyd_warshall(graph);
        int[] arr = new int[graph.length];
        int n = 0;
        for (int i = 0; i < graph.length; i++) {
            if (arr[i] == 0) {
                n++;
                arr[i] = n;
                ArrayList<Integer> component = new ArrayList<>();
                component.add(i);
                ccs.add(n-1,component);
                for (int j = i + 1; j < graph.length; j++) {
                    if (i != j && graph[i][j] && graph[j][i] && arr[j] == 0) {
                        arr[j] = n;
                        ccs.get(n-1).add(j);
                    }
                }
            }
        }
        return ccs;
    }

    ///better function in WeightedUndirectedGraph
    public static String[][] floyd_warshallString(boolean[][] graph) { //6
        String[][] ans = new String[graph.length][graph.length];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans.length; j++) {
                if (graph[i][j]) ans[i][j] = String.valueOf(i) + "->" + String.valueOf(j);
                else ans[i][j] = "";
            }
        }
        for (int k = 0; k < ans.length; k++) {
            for (int i = 0; i < ans.length; i++) {
                for (int j = 0; j < ans.length; j++) {
                    if (ans[i][j].equals("")) { //graph[i][j]==false
                        if ((!ans[i][k].equals("")) && (!ans[k][j].equals(""))) {
                            int numOfDigits = (int)Math.log(j);
                            if (numOfDigits<0) numOfDigits=0;
                            ans[i][j] = ans[i][k] + "->" + ans[k][j].substring(2+numOfDigits);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i][i] = "";
        }
        return ans;
    }


    public static String findPath (boolean[][] graph, int v_i, int v_j){ //6
        if (v_i> graph.length||v_j> graph.length||v_i<0 || v_j<0) return "";
        String[][] g= floyd_warshallString(graph);
        return g[v_i][v_j];
    }

    public static boolean isTherePathBottleProblem (int n, int m, int i1, int j1, int i2, int j2){
        if (i1>n || i2>n) return false;
        if (j1>m || j2>m) return false;
        boolean[][] graph = bottleProblem.fillMatrix(n,m);
        floyd_warshall(graph);
        int i= i1 * (m+1) + j1;
        int j= i2 * (m+1) + j2;
        return graph[i][j];
    }


    public static void main(String[] args) {
//        boolean[][] graph = {{false, false, true, false, false},
//                             {true, false, true, false, false},
//                             {true, true, false, false, false},
//                             {false, true, false, false, false},
//                             {false, true, false, false, false}};
//        ArrayList<ArrayList<Integer>> ccs= ccs(graph);
        System.out.println(isTherePathBottleProblem(1,2,0,1,1,2));
    }
}