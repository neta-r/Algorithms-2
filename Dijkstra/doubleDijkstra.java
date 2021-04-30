package Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class doubleDijkstra {
    public static String Dijkstra(int[][] graph, int src, int dest) {
        int[] greenDist = new int[graph.length];
        int[] greenFathers = new int[graph.length];
        int[] redDist = new int[graph.length];
        int[] redFathers = new int[graph.length];
        Arrays.fill(greenDist, Integer.MAX_VALUE);
        Arrays.fill(greenFathers, -1);
        Arrays.fill(redDist, Integer.MAX_VALUE);
        Arrays.fill(redFathers, -1);
        greenDist[src] = 0;
        redDist[dest] = 0;
        greenFathers[src] = Integer.MIN_VALUE;
        redFathers[dest] = Integer.MIN_VALUE;
        PriorityQueue<Integer> greenQueue = new PriorityQueue<>();
        PriorityQueue<Integer> redQueue = new PriorityQueue<>();
        for (int i = 0; i < graph.length; i++) {
            greenQueue.add(i);
            redQueue.add(i);
        }
        int min;
        while (!(stop(greenQueue, redQueue, graph.length))) {
            min = dequeue(greenDist, greenQueue);
            greenQueue.remove(min);
            for (int j = 0; j < graph.length; j++) {
                if (graph[min][j] != 0 && greenQueue.contains(j)) {
                    if (greenDist[j] > greenDist[min] + graph[min][j]) {
                        greenDist[j] = greenDist[min] + graph[min][j];
                        greenFathers[j] = min;
                    }
                }
            }
            min = dequeue(redDist, redQueue);
            redQueue.remove(min);
            for (int j = 0; j < graph.length; j++) {
                if (graph[min][j] != 0 && redQueue.contains(j)) {
                    if (redDist[j] > redDist[min] + graph[min][j]) {
                        redDist[j] = redDist[min] + graph[min][j];
                        redFathers[j] = min;
                    }
                }
            }
        }
        min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < graph.length; i++) {
            greenDist[i] += redDist[i];
            if (greenDist[i] < min) {
                min = greenDist[i];
                minIndex = i;
            }
        }
        greenDist[dest]= Integer.MAX_VALUE;
        String greenAns = "", redAns="";
        int temp=minIndex;
        while (temp!=src){
            temp= greenFathers[temp];
            greenAns= Integer.toString(temp)+ "->" + greenAns;
        }
       // ans += Integer.toString(minIndex)+ " ->";
        temp=minIndex;
        while (temp!=dest){
            temp= redFathers[temp];
            redAns=redAns+ "->" + Integer.toString(temp);
        }
        return greenAns+  Integer.toString(minIndex) +redAns;
    }

    public static boolean stop(PriorityQueue<Integer> greenQueue, PriorityQueue<Integer> redQueue, int numOfVertices) {
        for (int i = 0; i < numOfVertices; i++) {
            if (!(greenQueue.contains(i)) && !(redQueue.contains(i))) return true;
        }
        return false;
    }


    public static int dequeue(int[] dist, PriorityQueue<Integer> queue) {
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = 0; i < dist.length; i++) {
            if (queue.contains(i) && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int[][] graph ={{0, 1, 2, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 5, 0, 0, 0},
                        {2, 0, 0, 7, 0, 3, 0, 0},
                        {0, 0, 7, 0, 3, 8, 0, 0},
                        {0, 5, 0, 3, 0, 0, 1, 4},
                        {0, 0, 3, 8, 0, 0, 2, 0},
                        {0, 0, 0, 0, 1, 2, 0, 5},
                        {0, 0, 0, 0, 4, 0, 5, 0}};
        System.out.println(Dijkstra(graph, 0,7));
    }
}