import java.util.ArrayList;
import java.util.Arrays;

public class TreesIsomorphism {
    static class node<T> {
        T data;
        node[] sons;
        node father;
        int curr;
        int numOfSons;
        String str;

        node(T data, int num) {
            this.data = data;
            this.father = null;
            this.sons = new node[num];
            curr = 0;
            numOfSons = num;
        }

        node(T data, int num, node father) {
            this.data = data;
            this.father = father;
            this.sons = new node[num];
            curr = 0;
            numOfSons = num;
        }

        void addSon(node son) {
            if (numOfSons <= curr) return;
            sons[curr] = son;
            curr++;
        }
    }

    public static <T> void buildStr(node<T> curr) {
        for (node son : curr.sons) {
            if (son.numOfSons != 0) {
                buildStr(son);
            } else {
                son.str = "01";
            }
        }
        if (curr.numOfSons>0) {
            String[] st = new String[curr.numOfSons];
            StringBuilder ans = new StringBuilder("0");
            for (int i = 0; i < curr.numOfSons; i++) {
                st[i] = curr.sons[i].str;
            }
            Arrays.sort(st);
            for (String s : st) {
                ans.append(s);
            }
            ans.append("1");
            curr.str = ans.toString();
        }
    }

    public static <T, S> boolean isIsomorphic(node<T> root1, node<S> root2) {
        buildStr(root1);
        buildStr(root2);
        return root1.str.equals(root2.str);
    }

    public static void main(String[] args) {
        node<Integer> one = new node<Integer>(1, 3);
        node<Integer> two = new node<Integer>(2, 0, one);
        node<Integer> three = new node<Integer>(3, 0, one);
        node<Integer> four = new node<Integer>(4, 2, one);
        node<Integer> five = new node<Integer>(5, 0, four);
        node<Integer> six = new node<Integer>(6, 1, four);
        node<Integer> seven = new node<Integer>(7, 0, six);
        one.addSon(two);
        one.addSon(three);
        one.addSon(four);
        four.addSon(five);
        four.addSon(six);
        six.addSon(seven);
        /*
               1
             / | \
            2  4  3
              / \
             5   6
                 |
                 7
         */
        node<Character> a = new node<Character>('a', 3);
        node<Character> b = new node<Character>('b', 2, a);
        node<Character> c = new node<Character>('c', 0, a);
        node<Character> d = new node<Character>('d', 0, a);
        node<Character> e = new node<Character>('e', 0, b);
        node<Character> f = new node<Character>('f', 1, b);
        node<Character> g = new node<Character>('g', 0, f);
        a.addSon(b);
        a.addSon(c);
        a.addSon(d);
        b.addSon(e);
        b.addSon(f);
        f.addSon(g);
        /*
              a
            / | \
           b  c  d
          / \
         e   f
             |
             g
         */
        System.out.println(isIsomorphic(one,a));
    }
}
