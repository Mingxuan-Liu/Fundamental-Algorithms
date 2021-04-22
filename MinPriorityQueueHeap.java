package TenthWeek;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MinPriorityQueueHeap<Item extends Comparable<Item>> implements Iterable<Item>{

    Item[] items;
    int size;
    int modCount;

    private void resize(int capacity) {
        Item[] t = (Item[]) (new Comparable[capacity]);
        int upperBound = (capacity > items.length ? items.length : capacity);
        for (int i = 0; i < upperBound; i++) {
            t[i] = items[i];
        }
        this.items = t;
    }

    public MinPriorityQueueHeap() {
        items = (Item[]) (new Comparable[2]);
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
        if (size >= items.length/2) {
            resize(2*items.length);
        }
        items[size+1] = item;
        size++;
        swim(size);
        modCount++;
    }

    public Item removeMinItem() {
        Item n = items[1];
        items[1] = items[size];
        items[size] = null;
        size--;
        sink(1);
        if (size <= items.length/8) {
            resize(items.length/2);
        }
        modCount--;
        return n;
    }

    private void swim(int k) {
        while ((k > 1) && less(k,k/2)) {
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            if ((j < size) && less(j+1,j))
                j++;
            if (less(k,j))
                break;
            exch(k,j);
            k = j;
        }
    }

    public Iterator<Item> iterator() {
        return (new Iterator<Item>() {
            private int nextPos = 0;
            private int savedModCount = modCount;

            public boolean hasNext() {
                if (modCount != savedModCount) throw new ConcurrentModificationException();
                return nextPos < size;
            }

            public Item next() {
                if (modCount != savedModCount) throw new ConcurrentModificationException();
                return items[nextPos++];
            }

        });
    }
}
