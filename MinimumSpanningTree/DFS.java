package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    enum color {White, Gray, Black}

    ArrayList<Integer>[] graph;
    color[] colors;
    int[] preds;
    Stack<Integer> cycle;

    DFS(ArrayList<Integer>[] graph) {
        this.graph = graph;
        this.colors = new color[graph.length];
        Arrays.fill(colors, color.White);
        this.preds = new int[graph.length];
        Arrays.fill(preds, -1);
        cycle = new Stack<>();
    }

    boolean hasCircle(ArrayList<Integer>[] graph) {
        boolean ans = false;
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == color.White) {
                ans = dfsVisit(i);
            }
        }
        return ans;
    }

    boolean dfsVisit(int u) {
        boolean ans = false;
        colors[u] = color.Gray;
        for (int v : graph[u]) {
            if (!ans) {
                if (colors[v] == color.Gray && preds[u] != v) {
                    ans = true;
                    getCycle(u, v);
                } else if (colors[v] == color.White) {
                    preds[v] = u;
                    ans = dfsVisit(v);
                }
            }
        }
        colors[u] = color.Black;
        return ans;
    }

    void getCycle(int u, int v) {
        int x = u;
        while (x != v) {
            cycle.push(x);
            x = preds[x];
        }
        cycle.push(v);
        cycle.push(u);
    }
}
