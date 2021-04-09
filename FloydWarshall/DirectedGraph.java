package FloydWarshall;

import java.util.ArrayList;

public class DirectedGraph {
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

    ///better function in WeightedUndirectedGraph
    public static String[][] floyd_warshallString(boolean[][] graph) {
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
                            int numOfDigits = (int)Math.log10(j)+1;
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


    public static String getPath(boolean[][] graph, int i, int j){
        String[][] ans = floyd_warshallString(graph);
        return ans[i][j];
    }

    public static boolean isConnected(boolean[][] graph) { //O(n)
        floyd_warshall(graph);
        for (int i = 1; i < graph.length; i++) {
            if (!graph[i][0]) return false;
        }
        return true;
    }

    public static int[] LowLinkCcs(boolean[][] graph) {
        floyd_warshall(graph);
        int[] ccs = new int[graph.length];
        int n = 0;
        for (int i = 0; i < graph.length; i++) {
            if (ccs[i] == 0) {
                n++;
                ccs[i] = n;
                for (int j = i + 1; j < graph.length; j++) {
                    if (i != j && graph[i][j] && ccs[j] == 0) {
                        ccs[j] = n;
                    }
                }
            }
        }
        return ccs;
    }

    public static ArrayList<ArrayList<Integer>> ccs(boolean[][] graph) {
        ArrayList<ArrayList<Integer>> ccs = new ArrayList<>();
        floyd_warshall(graph);
        int[] arr = new int[graph.length];
        int n = 0;
        for (int i = 0; i < graph.length; i++) {
            if (arr[i] == 0) {
                n++;
                arr[i] = n;
                ArrayList<Integer> component = new ArrayList<>();
                component.add(i);
                ccs.add(n - 1, component);
                for (int j = i + 1; j < graph.length; j++) {
                    if (i != j && graph[i][j] && arr[j] == 0) {
                        arr[j] = n;
                        ccs.get(n - 1).add(j);
                    }
                }
            }
        }
        return ccs;
    }


    public static void main(String[] args) {
        boolean[][] graph = {{false, true, true, false, false, false, false},
                {true, false, true, false, false, false, false},
                {true, true, false, false, false, false, false},
                {false, false, false, false, true, false, true},
                {false, false, false, true, false, true, false},
                {false, false, false, false, true, false, true},
                {false, false, false, true, false, true, false}};
//        System.out.println("before");
//        for (int i = 0; i < graph.length; i++) {
//            for (int j = 0; j < graph.length; j++) {
//                System.out.print(graph[i][j] + ", ");
//            }
//            System.out.println();
//        }
        String[][] ans = floyd_warshallString(graph);
        System.out.println();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans.length; j++) {
                System.out.print(ans[i][j] + ", ");
            }
            System.out.println();
        }



        floyd_warshall(graph);
        System.out.println();
        System.out.println("after");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + ", ");
            }
            System.out.println();
        }

    }
}


