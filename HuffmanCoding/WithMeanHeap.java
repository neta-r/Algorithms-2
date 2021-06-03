package HuffmanCoding;

import java.util.ArrayList;

public class WithMeanHeap {
    public static String[][] huffman(char[] chars, int[] percentage) {
        int[][] mat = getMat(chars, percentage);
        String[][] ans = new String[2][chars.length];
        for (int i = 0; i < chars.length; i++) {
            ans[0][i] = String.valueOf(chars[i]);
            ans[1][i] = StaticMethod.getPath(i, mat);
        }
        return ans;
    }

    public static int[][] getMat(char[] chars, int[] percentage) {
        int[][] mat = new int[2 * chars.length - 1][4];
        if (chars.length == percentage.length) {
            // visited , freq , father , left , right
            MinHeap Q = new MinHeap(percentage.length);
            for (int i = 0; i < percentage.length; i++) {
                mat[i][0] = percentage[i];
                Q.insert(percentage[i]);
            }
            int index = percentage.length;
            int x, y , z;
            for (int i = 0; i < chars.length - 1; i++) {
                x = Q.remove();
                y = Q.remove();
                z = x + y;
                Q.insert(z);
                int index1 = getIndex(mat,x);
                int index2 = getIndex(mat,y);

                mat[index1][1] = index;
                mat[index2][1] = index;
                mat[index][0] = z;
                mat[index][2] = index1;
                mat[index][3] = index2;
                index++;
            }
        }
        return mat;
    }

    private static int getIndex(int[][] mat, int val){
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][0] == val) {
                return i;
            }
        }
        return -1;
    }


    public static class MinHeap {
        private int[] Heap;
        private int index;
        private int size;

        public MinHeap(int size) {
            this.size = size;
            this.index = 0;
            Heap = new int[size];
        }

        public int size() {
            return index;
        }

        private int parent(int i) {
            return (i - 1) / 2;
        }

        private int leftChild(int i) {
            return (i * 2) + 1;
        }

        private int rightChild(int i) {
            return (i * 2) + 2;
        }

        private boolean isLeaf(int i) {
            if (rightChild(i) >= size || leftChild(i) >= size) return true;
            return false;
        }

        public void insert(int element) {
            if (index >= size) return;
            Heap[index] = element;
            int current = index;

            while (Heap[current] < Heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
            index++;
        }

        public int remove() {
            int popped = Heap[0];
            Heap[0] = Heap[--index];
            if (index > 1) minHeapify(0);
            return popped;
        }

        private void minHeapify(int i) {
            if (!isLeaf(i)) {
                if (Heap[i] > Heap[leftChild(i)] ||
                        Heap[i] > Heap[rightChild(i)]) {
                    if (Heap[leftChild(i)] < Heap[rightChild(i)]) {
                        swap(i, leftChild(i));
                        minHeapify(leftChild(i));
                    } else {
                        swap(i, rightChild(i));
                        minHeapify(rightChild(i));
                    }
                }
            }
        }

        public void minHeap() {
            for (int i = (index - 1 / 2); i >= 1; i--) minHeapify(i);
        }

        private void swap(int x, int y) {
            int tmp;
            tmp = Heap[x];
            Heap[x] = Heap[y];
            Heap[y] = tmp;
        }

        public static void main(String[] args) {
            int[] percentage = {10, 18, 24, 48};
            char[] chars = {'a', 'b', 'c', 'd'};
            huffman(chars, percentage);
        }
    }
}
