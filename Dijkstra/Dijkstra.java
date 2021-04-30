package Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;


public class Dijkstra {
    public static int[] Dijkstra(int[][] graph, int src) {
        int[] dist = new int[graph.length];
        int[] fathers = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(fathers, -1);
        dist[src] = 0;
        fathers[src] = Integer.MIN_VALUE;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < graph.length; i++) queue.add(i);
        int min;
        while (!queue.isEmpty()) {
            min = dequeue(dist, queue);
            queue.remove(min);
            for (int j = 0; j < graph.length; j++) {
                if (graph[min][j] != 0&&queue.contains(j)) {
                    if (dist[j] > dist[min] + graph[min][j]) {
                        dist[j] = dist[min] + graph[min][j];
                        fathers[j] = min;
                    }
                }
            }
        }
        return dist;
    }

    public static int DijkstraWithDest(int[][] graph, int src, int dest) {
        int[] dist = new int[graph.length];
        int[] fathers = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(fathers, -1);
        dist[src] = 0;
        fathers[src] = Integer.MIN_VALUE;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int min=-1;
        for (int i = 0; i < graph.length; i++) queue.add(i);
        while (min==dest) {
            min = dequeue(dist, queue);
            queue.remove(min);
            for (int j = 0; j < graph.length; j++) {
                if (graph[min][j] != 0&&queue.contains(j)) {
                    if (dist[j] > dist[min] + graph[min][j]) {
                        dist[j] = dist[min] + graph[min][j];
                        fathers[j] = min;
                    }
                }
            }
        }
        return dist[dest];
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
        int[][] graph = {{0, 4, 2, 0, 0},
                {4, 0, 0, 1, 2},
                {2, 0, 0, 6, 0},
                {0, 1, 6, 0, 3},
                {0, 2, 0, 3, 0}};
        Dijkstra(graph, 0);
    }
}
