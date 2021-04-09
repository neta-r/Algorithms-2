package FloydWarshall;

public class graphWithNegativeEdges {

    public static boolean isThereNegativeCircleInUndirectedGraph(int[] weight){
        for (int i=0; i<weight.length; i++){
            if (weight[i]<0) return true;
        }
        return false;
    }

    public static boolean isThereNegativeCircleInDirectedGraph(int[][] graph){
        WeightedUndirectedGraph.floyd_warshall(graph);
        for (int i=0; i<graph.length; i++){
            if (graph[i][i]<0) return true;
        }
        return false;
    }

    public static String negativeCircle (int[][] graph){
            node [][] strGraph = WeightedUndirectedGraph.floydWarshallPaths(graph);
            for (int i=0; i<graph.length; i++){
                if (strGraph[i][i].weight<0) return strGraph[i][i].path;
            }
        return "";
    }

    public static void main(String[] args) {
        int[] undirectedGraph = {1,5,3,8};
        System.out.println(isThereNegativeCircleInUndirectedGraph(undirectedGraph));
        int[][] directedGraph = {{0,4,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,0,2},
                {-10, Integer.MAX_VALUE, 0}};
        System.out.println(negativeCircle(directedGraph));
    }

}
