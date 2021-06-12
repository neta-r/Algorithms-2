import java.util.ArrayList;
import java.util.Stack;

public class EulerCycle {
    public static void eraseNei(ArrayList<Integer>[] graph, int u, int v) {
        int index = -1;
        for (int i = 0; i < graph[u].size(); i++) {
            if (graph[u].get(i) == v) {
                index = i;
                break;
            }
        }
        graph[u].remove(index);
    }

    public static String getEulerCycle(ArrayList<Integer>[] graph) {
        Stack<Integer> S = new Stack<>();
        Stack<Integer> C = new Stack<>();
        S.push(0);
        while (!S.empty()) {
            int u = S.peek();
            if (graph[u].size() == 0) {
                S.pop();
                C.push(u);
            } else {
                int v=graph[u].get(0);
                S.push(v);
                eraseNei(graph, u, v);
                eraseNei(graph, v, u);

            }
        }
        String cycle = Integer.toString(C.pop());
        int size = C.size();
        for (int i = 0; i < size; i++) {
            cycle = cycle + "->" + C.pop();
        }
        return cycle;
    }

    public static void main(String[] args) {
        ArrayList<Integer>[] graph = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(1);
        graph[0].add(2);
        graph[0].add(7);
        graph[0].add(8);
        graph[1].add(0);
        graph[1].add(2);
        graph[2].add(0);
        graph[2].add(3);
        graph[2].add(7);
        graph[2].add(1);
        graph[3].add(2);
        graph[3].add(7);
        graph[3].add(5);
        graph[3].add(4);
        graph[4].add(3);
        graph[4].add(5);
        graph[5].add(4);
        graph[5].add(3);
        graph[5].add(6);
        graph[5].add(7);
        graph[6].add(5);
        graph[6].add(7);
        graph[7].add(2);
        graph[7].add(3);
        graph[7].add(5);
        graph[7].add(6);
        graph[7].add(8);
        graph[7].add(0);
        graph[8].add(7);
        graph[8].add(0);
        System.out.println(getEulerCycle(graph));
    }
}
