package FloydWarshall;

import java.util.StringTokenizer;

public class WeightedUndirectedGraph {
    public static int[][] floyd_warshall(int[][] graph) {
        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if (graph[k][j] != Integer.MAX_VALUE && graph[i][k] != Integer.MAX_VALUE) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }
        return graph;
    }

    public static node[][] floydWarshallPaths(int[][] graph) {
        node[][] ans = new node[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) { //Initializing node matrix
            for (int j = 0; j < graph.length; j++) {
                ans[i][j] = new node(graph[i][j], i, j);
                if (graph[i][j] != Integer.MAX_VALUE && graph[i][j] != 0 && i != j)
                    ans[i][j].path = String.valueOf(i) + "->" + String.valueOf(j);

            }
        }
        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if (ans[k][j].weight != Integer.MAX_VALUE && ans[i][k].weight != Integer.MAX_VALUE) {
                        if (ans[i][k].weight + ans[k][j].weight < ans[i][j].weight) {
                            ans[i][j].weight = ans[i][k].weight + ans[k][j].weight;
                            ans[i][j].path = ans[i][k].path + "->" + ans[k][j].path;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                ans[i][j].path = removeDuplication(ans[i][j].path);
            }
        }
        return ans;
    }

    public static String removeDuplication(String path) {
        if (path.equals("")) return "";

        StringTokenizer str = new StringTokenizer(path, "->");
        String last = str.nextToken();
        String curr = last;
        StringBuilder ans = new StringBuilder(last);
        while (str.hasMoreElements()) {
            last = curr;
            curr = str.nextToken();
            if (!last.equals(curr)) {
                ans.append("->").append(curr);
            }
        }
        return ans.toString().toString();
    }


    public static boolean isTherePathBottleProblem(int n, int m, int i1, int j1, int i2, int j2) {
        if (i1 > n || i2 > n) return false;
        if (j1 > m || j2 > m) return false;
        boolean[][] graph = bottleProblem.fillMatrix(n, m);
        int[][] weightedGraph = boolToInt(graph);
        floyd_warshall(weightedGraph);
        int i = i1 * (m + 1) + j1;
        int j = i2 * (m + 1) + j2;
        return graph[i][j];
    }

    public static int[][] boolToInt(boolean[][] graph) {
        int[][] ans = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j]) ans[i][j] = 1;
                else ans[i][j] = Integer.MAX_VALUE;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] graph = {{0, 4, Integer.MAX_VALUE, 3},
                {4, 0, 15, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 15, 0, 1},
                {3, Integer.MAX_VALUE, 1, 0}};
        //floyd_warshall(graph);
        floydWarshallPaths(graph);
    }
}

class node {
    int weight, from, to;
    String path;

    node(int weight, int from, int to) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.path = "";
    }
}
