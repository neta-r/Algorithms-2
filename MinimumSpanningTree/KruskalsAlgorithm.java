package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;

public class KruskalsAlgorithm {

    static class edge implements Comparable<edge>{
        int weight;
        int src, dest;
        edge (int w, int s, int d){
            this.weight=w;
            this.src=s;
            this.dest=d;
        }
        @Override
        public int compareTo(edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    static class union{
        int name;
        union parent;
        int rank;

        union(int name){this.name=name;}
    }

    static union makeSet(int i) {
        union x= new union(i);
        x.parent = x;
        x.rank = 0;
        return x;
    }

    static void Union(union x, union y) {
        union xRoot = find(x);
        union yRoot = find(y);
        if (xRoot==yRoot) return;
        if (xRoot.rank<yRoot.rank)xRoot.parent=yRoot;
        else if (xRoot.rank>yRoot.rank) yRoot.parent=xRoot;
        else {
            yRoot.parent=xRoot;
            xRoot.rank++;
        }
    }

    static union find(union x) {
        if (x.parent != x) {
            x.parent = find(x.parent);
        }
        return x.parent;
    }

    public static ArrayList<edge> kruskal(edge[] graph) {
        ArrayList<edge> tree = new ArrayList<>();
        union[] arr= new union[graph.length];
        for (int i=0; i< graph.length; i++){
            arr[i]= makeSet(i);
        }
        Arrays.sort(graph);
        for(edge ed: graph){
            if (find(arr[ed.src])!=find(arr[ed.dest])){
                tree.add(ed);
                Union(arr[ed.src],arr[ed.dest]);
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        edge[] graph= {new edge(4,0,1),new edge(8,0,2),new edge(11,1,2), new edge(8,1,3),
        new edge(2,3,4), new edge(7,2,4),new edge(1,2,5), new edge(6,4,5), new edge(4,3,7),
        new edge(2,5,7), new edge(7,3,6), new edge(14,6,7),new edge(10,7,8), new edge(9,6,8)};
        kruskal(graph);
    }
}
