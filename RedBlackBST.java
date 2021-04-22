package TenthWeek;

import ThirdWeek.Queue;
import ThirdWeek.QueueArray;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {

        Key key;
        Value val;
        Node left;
        Node right;
        int size;
        boolean color;

        Node(Key key, Value val, int size, boolean color) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.color = color;
        }
    }

    public int size() {
        return root.size;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null)
            return new Node(key,val,1,RED);
        int cmp = key.compareTo(h.key);
        if  (cmp < 0)
            h.left = put(h.left, key, val);
        else if (cmp > 0)
            h.right = put(h.right, key, val);
        else
            h.val = val;

        if (isRed(h.right) && ! isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public Iterable<Key> queueOfKeysInOrder() {
        Queue<Key> q = new QueueArray<Key>();
        enqueueKeysInOrderFromNode(root, q);
        return q;
    }

    private void enqueueKeysInOrderFromNode(Node n, Queue<Key> q) {
        if (n == null) return;
        enqueueKeysInOrderFromNode(n.left, q);
        q.enqueue(n.key);
        enqueueKeysInOrderFromNode(n.right,q);
    }

    public static void main(String[] args) {
        RedBlackBST<String,String> rbt = new RedBlackBST<String,String>();
        String[] strsToPut = "S O R T E X A M P L E".split(" ");
        for (String s : strsToPut) {
            rbt.put(s, "");
        }
        Iterable<String> inOrderStrs = rbt.queueOfKeysInOrder();
        for (String s : inOrderStrs) {
            System.out.print(s + " ");
        }
    }
}
