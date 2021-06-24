package MinimumSpanningTree;

import java.util.PriorityQueue;

public class PrimsAlgorithm{
    static class Pair implements Comparable<Pair>{
        int nodeKey, nodeName;

        Pair(int k, int n) {
            this.nodeKey = k;
            this.nodeName = n;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.nodeKey,o.nodeKey);
        }
    }

    public static int[] prim(int[][] graph, int root) {
        boolean[] visited = new boolean[graph.length];
        int[] fathers = new int[graph.length];
        int[] keys = new int[graph.length];
        PriorityQueue<Pair> Q = new PriorityQueue<>();
        for (int i = 0; i < graph.length; i++) {
            if (i != root) Q.add(new Pair(i, Integer.MAX_VALUE));
            else Q.add(new Pair(i, 0));
            fathers[i] = -1;
            keys[i] = Integer.MAX_VALUE;
        }
        keys[root] = 0;
        while (Q.size()!=0){
            int u = Q.remove().nodeKey;
            if (!visited[u]) {
                for (int i = 0; i < graph.length; i++) {
                    if (graph[u][i] != 0) {
                        int v = i;
                        if (!visited[v] && keys[v] > graph[v][u]) {
                            keys[v] = graph[v][u];
                            fathers[v] = u;
                            Q.add(new Pair(v, keys[v]));
                        }
                    }
                }
                visited[u] = true;
            }
        }
        return fathers;
    }

    public static void main(String[] args) {
        int[][] graph = new int[9][9];
        graph[0][1] = graph[1][0] = 4;
        graph[0][2] = graph[2][0] = 8;
        graph[1][2] = graph[2][1] = 11;
        graph[1][3] = graph[3][1] = 8;
        graph[2][4] = graph[4][2] = 7;
        graph[2][5] = graph[5][2] = 1;
        graph[3][4] = graph[4][3] = 2;
        graph[3][6] = graph[6][3] = 7;
        graph[3][7] = graph[7][3] = 4;
        graph[4][5] = graph[5][4] = 6;
        graph[5][7] = graph[7][5] = 2;
        graph[6][7] = graph[7][6] = 14;
        graph[6][8] = graph[8][6] = 9;
        graph[7][8] = graph[8][7] = 10;
        prim(graph,0);
    }
}

