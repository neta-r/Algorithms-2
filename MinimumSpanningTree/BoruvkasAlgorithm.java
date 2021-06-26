package MinimumSpanningTree;

import java.util.ArrayList;

public class BoruvkasAlgorithm {
    static class edge implements Comparable<edge>{
        int weight;
        char src, dest;
        edge (int w, char s, char d){
            this.weight=w;
            this.src=s;
            this.dest=d;
        }
        @Override
        public int compareTo(edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }
    public static ArrayList<edge> boruvka(edge[] graph,int numOfVert){
        ArrayList<edge> tree= new ArrayList<edge>();
        KruskalsAlgorithm.union[] arr= new KruskalsAlgorithm.union[numOfVert];
        for (int i=0; i< arr.length; i++){
            arr[i]= KruskalsAlgorithm.makeSet(i);
        }
        boolean flag= true;
        while (flag) {
            flag=false;
            edge[] closest = new edge[numOfVert];
            for (edge ed : graph) {
                int uRoot = KruskalsAlgorithm.find(arr[ed.src - 'a']).name;
                int vRoot = KruskalsAlgorithm.find(arr[ed.dest - 'a']).name;
                if (uRoot == vRoot) continue;
                if (closest[vRoot] == null || ed.weight < closest[vRoot].weight) {
                    closest[vRoot] = ed;
                }
                if (closest[uRoot] == null || ed.weight < closest[uRoot].weight) {
                    closest[uRoot] = ed;
                }
            }
            for (BoruvkasAlgorithm.edge edge : closest) {
                if (edge != null) {
                    flag=true;
                    if (!tree.contains(edge)) {
                        tree.add(edge);
                        KruskalsAlgorithm.Union(arr[edge.src - 'a'], arr[edge.dest - 'a']);
                    }
                }
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        edge[] graph = {new edge(6,'a','b'), new edge(19, 'b','c'), new edge(9,'c','d'),
        new edge(14,'d','e'), new edge(21,'e','f'), new edge(2,'e','g'), new edge(25,'g','b'),
        new edge(11,'g','a'),new edge(8,'g','h'), new edge(17,'h','a')};
        boruvka(graph,8);
    }
}
