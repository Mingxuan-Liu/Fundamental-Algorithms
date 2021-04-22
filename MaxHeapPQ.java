package FifthWeek;

public class MaxHeapPQ<Item extends Comparable<Item>> implements PriorityQueue<Item> {

    Item[] items;
    int size;

    public MaxHeapPQ(int capacity) {
        items = (Item[]) (new Comparable[capacity+1]);
        size = 0;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i <= this.size(); i++) {
            if (items[i] != null)
                s += items[i] + " ";
            else
                s += "- ";
        }
        return s;
    }

    public int size() {
        return size;
    }

    private boolean less(int v, int w) {
        return (items[v]).compareTo(items[w]) < 0;
    }

    private void exch(int i, int j) {
        Item tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public void put(Item item) {
        items[size+1] = item;
        size++;
        swim(size);
    }

    public Item remove() {
        Item n = items[1];
        items[1] = items[size];
        items[size] = null;
        size--;
        sink(1);
        return n;
    }

    private void swim(int k) {
        // while item at k is not root and greater than parent,
        // exchange with parent..
        while ((k > 1) && less(k/2,k)) {
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k) {
        // while item at k is smaller than one or more of its children,
        // exchange with larger child (so heap order is preserved)
        while (2*k <= size) {

            int j = 2*k;  // start with left child (for now)

            // if right child exists and is larger, update choice
            // remember, if an exchange happens, then one of the children
            // becomes the parent of the other.  to preserve heap order
            // we need the larger child promoted to parent.
            if ((j < size) && less(j,j+1))
                j++;

            if (less(j,k)) // item at k is already larger than its children - stop
                break;

            exch(k,j);  // swap child and parent k
            k = j;      // update position of this sinking parent,
        }
    }


}

