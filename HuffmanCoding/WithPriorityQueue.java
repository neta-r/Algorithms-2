package HuffmanCoding;

import java.util.PriorityQueue;

public class WithPriorityQueue {
    static class node implements Comparable {
        char c;
        int percent;
        node father;
        node right;
        node left;
        int index;

        node(int p, int in) {
            index = in;
            percent = p;
        }

        node(int p, char ch, int in) {
            index = in;
            percent = p;
            c = ch;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(this.percent,((node)o).percent);
        }
    }

    private static node getNextMin(PriorityQueue<node> p1, PriorityQueue<node> p2) {
        if (p1.size() != 0 && p2.size() != 0) {
            if (p1.peek().percent < p2.peek().percent) {
                node ans = p1.peek();
                p1.poll();
                return ans;
            } else {
                node ans = p2.peek();
                p2.poll();
                return ans;
            }
        } else if (p1.size() != 0 && p2.size() == 0) {
            node ans = p1.peek();
            p1.poll();
            return ans;
        } else {
            node ans = p2.peek();
            p2.poll();
            return ans;
        }
    }

    public static String[][] huffman(char[] chars, int[] percentage) {
        String[][] ans = new String[2][chars.length];
        PriorityQueue<node> p1 = new PriorityQueue<>();
        PriorityQueue<node> p2 = new PriorityQueue<>();
        node[] nds = new node[percentage.length];
        for (int i = 0; i < percentage.length; i++) {
            node tmp = new node(percentage[i], chars[i], i);
            p1.add(tmp);
            nds[i] =tmp;
        }
        int index = percentage.length;
        for (int i = 0; i < percentage.length - 1; i++) {
            node x = getNextMin(p1, p2);
            node y = getNextMin(p1, p2);
            node nd = new node(x.percent + y.percent, index);
            nd.right = y;
            nd.left = x;
            x.father = nd;
            y.father = nd;
            p2.add(nd);
            index++;
        }
        for (int i = 0; i < chars.length; i++) {
            ans[0][i] = String.valueOf(chars[i]);
            ans[1][i] = getPath(nds[i]);
        }
        return ans;
    }

    static String getPath(node nd) {
        String ans = "";
        boolean flag = true;
        node curr = nd;
        while (flag) {
            node last = curr;
            curr= curr.father;
            if (curr.right.percent == last.percent) ans = "0"+ans; //right son to the parent
            if (curr.left.percent == last.percent) ans = "1"+ans; //left son to the parent
            if (curr.father == null) flag = false;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] percentage = {10, 18, 24, 48};
        char[] chars = {'a', 'b', 'c', 'd'};
        huffman(chars, percentage);
    }
}