package FifthWeek;

public class MinHeapPQ<Item extends Comparable<Item>> implements PriorityQueue<Item> {

    Item[] items;
    int size;

    public MinHeapPQ(int capacity) {
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
        // while item at k is not root and less than parent,
        // exchange with parent..
        while ((k > 1) && less(k,k/2)) {
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k) {
        // while item at k is bigger than one or more of its existing children,
        // exchange with smaller child (so heap order is preserved)
        while (2*k <= size) {

            int j = 2*k;  // start with left child (for now)

            // if right child exists and is smaller, update choice
            // remember, if an exchange happens, then one of the children
            // becomes the parent of the other.  to preserve heap order
            // we need the smaller child promoted to parent.
            if ((j < size) && less(j+1,j))
                j++;

            if (less(k,j)) // item at k is already smaller than its children - stop
                break;

            exch(k,j);  // swap child and parent k
            k = j;      // update position of this sinking parent,
        }
    }
}

