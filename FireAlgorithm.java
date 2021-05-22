import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FireAlgorithm {

    public static void simpleFireAlgorithm (ArrayList<Integer>[] graph){
        int numOfEdges = 0;
        int iteration = 1;
        Queue<Integer> leaves = new LinkedList<>();
        int[] iterationArr = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            numOfEdges+=graph[i].size();
            if (graph[i].size()==1){
                leaves.add(i);
                iterationArr[i] = 1;
            }
        }
        while (numOfEdges!=0){
            int v = leaves.remove();
            int w = graph[v].get(0);
            graph[v].remove(0);
            numOfEdges--;
            if (numOfEdges==1) {
                printData(v,w,iteration);
                break;
            }
            for (int i = 0; i < graph[w].size(); i++) {
                if (graph[w].get(i)==v){
                    graph[w].remove(i);
                    numOfEdges--;
                    break;
                }
            }
            if (graph[w].size()==1){
                leaves.add(w);
                iterationArr[w] = iterationArr[v]+1;
                iteration =  iterationArr[v]+1;
            }
        }
    }

    public static void printData(int v , int w, int iteration){
        int center1 = w;
        if (iteration%2==0){
            int center2 = v;
            System.out.println("Num of centers: 2");
            System.out.println("The centers are: "+center1 +" and: "+center2);
            System.out.println("The radius is: "+(iteration));
            System.out.println("The diameter is: "+((2*iteration)-1));
        }
        else {
            System.out.println("Num of centers: 1");
            System.out.println("The centers are: "+center1);
            System.out.println("The radius is: "+(iteration-1));
            System.out.println("The diameter is: "+2*(iteration-1));
        }
    }

    public static void smartFireAlgorithm (ArrayList<Integer>[] graph){
        int numOfEdges = 0;
        int iteration = 1;
        int[] deg = new int[graph.length];
        Queue<Integer> leaves = new LinkedList<>();
        int[] iterationArr = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            numOfEdges += graph[i].size();
            deg[i] = graph[i].size();
            if (graph[i].size()==1) {
                leaves.add(i);
                iterationArr[i] = 1;
            }
        }
        while (numOfEdges!=0){
            int v = leaves.remove();
            int w= -1;
            for (int nei: graph[v]){
                if (deg[nei]!=0) { //it wasn't burt
                    w = nei;
                    break;
                }
            }
            //graph[v].remove(0);
            deg[v]--;
            numOfEdges--;//v is not nei with w
            if (numOfEdges==1) {
                printData(v,w,iteration);
                break;
            }
            numOfEdges--; //w is not nei with v
            deg[w]--;
            if (deg[w]==1){
                leaves.add(w);
                iterationArr[w] = iterationArr[v]+1;
                iteration =  iterationArr[v]+1;
            }
        }
    }


    public static void main(String[] args) {
        ArrayList<Integer>[] graph = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[1].add(2);
        graph[2].add(1);
        graph[2].add(3);
        graph[2].add(4);
        graph[3].add(2);
        graph[4].add(2);
        graph[4].add(5);
        graph[4].add(6);
        graph[4].add(7);
        graph[5].add(4);
        graph[6].add(4);
        graph[7].add(4);
        graph[7].add(8);
        graph[8].add(7);
        smartFireAlgorithm(graph);
        System.out.println("--");
        simpleFireAlgorithm(graph);
        System.out.println("*****");
        ArrayList<Integer>[] graph2 = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            graph2[i] = new ArrayList<>();
        }
        graph2[0].add(1);
        graph2[1].add(0);
        graph2[1].add(2);
        graph2[2].add(1);
        graph2[2].add(3);
        graph2[3].add(2);
        smartFireAlgorithm(graph2);
        System.out.println("--");
        simpleFireAlgorithm(graph2);
    }
}
