package TenthWeek;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author Mingxuan Liu
 * @version 04/05/2021
 * This program completes the in-order traversal of a binary search tree in a non-recursive way
 * @param <Key> The key in nodes that could be compared by implementing the Comparable interface
 */

public class BinaryTreeInOrderIterable <Key extends Comparable<Key>> implements Iterable<Key> {

    private class Node {
        private final Key key; // the input key from clients
        private Node left;
        private Node right;

        public Node(Key key) {
            this.key = key;
        }
    }

    private Node root;
    private int modCount;

    public void put(Key key) {
        root = putR(root, key);
        modCount++;
    }

    /**
     * A private-facing recursive method that inserts keys into the tree
     * @param n the root of the overall tree or the subtrees
     * @param key the key that clients want to insert
     * @return the new tree with element inserted
     */
    private Node putR(Node n, Key key) {
        if (n == null)  {
            return new Node(key);
        }

        int cmp = key.compareTo(n.key);
        if (cmp < 0)        n.left = putR(n.left, key);
        else if (cmp > 0)   n.right = putR(n.right, key);

        return n;
    }

    /*
    Automatically called by the println() statement and then invoke the Iterator() for traversal
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Key key : this) {
            s.append(key).append(",");
        }
        return s.toString();
    }

    public Iterator<Key> iterator() {
        return new Iterator<>() {
            private final int savedModCount = modCount; // to throw ConcurrentModificationException
            final Stack<Node> toPrint  = new Stack<>(); // to store left child, parent, and right child
            Node curr = root; // a pointer to traverse the tree

            /*
            If the stack is empty, and the curr is null, the pointer has already reached the maximum
             */
            @Override
            public boolean hasNext() {
                if (savedModCount != modCount) throw new ConcurrentModificationException();
                return (!(curr == null && toPrint.isEmpty())); // the traversal is not done yet
            }

            @Override
            public Key next() {
                if (savedModCount != modCount) throw new ConcurrentModificationException();
                while(curr != null){
                    toPrint.push(curr);
                    curr = curr.left; // to climb down the tree until reaching the minimum
                }
                curr = toPrint.peek(); // update, to climb the tree one level up
                curr = curr.right; // point to the right subtree of the parent just updated
                return toPrint.pop().key; // print the minimum, according to the LIFO principle
            }
        };
    }

    public static void main(String[] args) {
        BinaryTreeInOrderIterable<Character> tree = new BinaryTreeInOrderIterable<>();
        String charsToPut = "thequickbrownfoxjumpedoverthelazydog";
        for (int i = 0; i < charsToPut.length(); i++) {
            tree.put(charsToPut.charAt(i));
        }

        System.out.println(tree);  // As the sentence "The quick brown fox jumped over the lazy dog"
        // includes every letter of the alphabet, the output
        // should be "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,t,u,v,w,x,y,z,"

        // UNCOMMENT BELOW CODE TO TEST IDENTIFICATION OF CONCURRENT MODIFICATION
        // (Should throw a ConcurrentModificationException...)

        StringBuilder s = new StringBuilder();
        int cnt = 0;
        for (Character key : tree) {
            s.append(key).append(",");
            cnt++;
            if (cnt == 10) tree.put('X');
        }
        System.out.println(s);
    }
}


