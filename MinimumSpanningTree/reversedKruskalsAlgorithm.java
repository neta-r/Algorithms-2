package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;

public class reversedKruskalsAlgorithm {
    static class edge implements Comparable<edge> {
        int weight;
        int src, dest;

        edge(int w, int s, int d) {
            this.weight = w;
            this.src = s;
            this.dest = d;
        }

        @Override
        public int compareTo(edge o) {
            return Integer.compare(this.weight, o.weight) * -1;
        }
    }

    static ArrayList<Integer>[] toBfs(ArrayList<edge> tree, int numOfVertices){
        ArrayList<Integer>[] graph = new ArrayList[numOfVertices];
        for (int i=0; i<numOfVertices; i++) graph[i]= new ArrayList<>();
        for (edge ed:tree){
            graph[ed.src].add(ed.dest);
            graph[ed.dest].add(ed.src);
        }
        return graph;
    }

    public static ArrayList<edge> reversedKruskal(edge[] graph, int numOfVertices) {
        ArrayList<edge> tree = new ArrayList<>(Arrays.asList(graph));
        ArrayList<Integer>[] bfsTree = toBfs(tree,numOfVertices);
        Arrays.sort(graph);
        for (edge ed:graph){
            if (tree.size()==numOfVertices-1) break;
            tree.remove(ed);
            bfsTree[ed.src].remove((Integer) ed.dest);
            bfsTree[ed.dest].remove((Integer) ed.src);
            if (!BFS.isConnected(bfsTree)) {
                tree.add(ed);
                bfsTree[ed.src].add(ed.dest);
                bfsTree[ed.dest].add(ed.src);
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        edge[] graph= {new edge(7,0,1), new edge(5,0,3),new edge(9,1,3), new edge(8,1,2),
        new edge(5,2,4), new edge(15,3,4), new edge(7,1,4), new edge(6,3,5), new edge(8,4,5)
                , new edge(9,4,6), new edge(11,5,6)};
        reversedKruskal(graph,7);
    }
}
