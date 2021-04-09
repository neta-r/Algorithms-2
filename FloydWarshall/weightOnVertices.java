package FloydWarshall;

public class weightOnVertices {
    private static int[][] toMatrix(int[] weight, boolean[][] graph) {
        int[][] matrix = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j]) matrix[i][j] = weight[i] + weight[j];
                else {
                    if (i == j) matrix[i][j] = 0;
                    else matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        return matrix;
    }

    public static int[][] onVertices(int[] weight, boolean[][] graph) {
        int[][] matrix = toMatrix(weight, graph);
        WeightedUndirectedGraph.floyd_warshall(matrix);
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if(i!=j) matrix[i][j] = (matrix[i][j]+weight[i]+weight[j])/2;
            }
        }
        return matrix;
    }

    public static  node[][] floyd_warshallString(int[] weight, boolean[][] graph) {
        int[][] matrix = toMatrix(weight, graph);
        node[][]ans = WeightedUndirectedGraph.floydWarshallPaths(matrix);
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if(i!=j) ans[i][j].weight = (ans[i][j].weight+weight[i]+weight[j])/2;
            }
        }
        return ans;
    }


    public static String path(int[] weight, boolean[][] graph, int vi, int vj){
        node[][] matrix = floyd_warshallString(weight, graph);
        return matrix[vi][vj].path;
    }

    public static void main(String[] args) {
        boolean[][] graph = {{false, true, false, true},
                {true, false, true, false},
                {false, true, false, true},
                {true, false, true, false}};
        int[] weight = {4, 3, 2, 5};
        int[][] myGraph = onVertices(weight, graph);
        node[][] stringGraph = floyd_warshallString(weight,graph);
    }
}
