package FifthWeek;

import java.util.Arrays;

public class HeapSorter<Item extends Comparable<Item>> implements Sorter<Item> {

    Item[] a;

    private boolean less(int v, int w) {
        return (a[v-1]).compareTo(a[w-1]) < 0;
        // the "one off" here accomodates sorting array elements from 0 to length - 1,
        // while heap indices are normally 1 to length to make finding parent
        // and children indices easy
    }

    private void exch(int i, int j) { // we see a "one off" modified version of exch()
        Item tmp = a[i-1];            // for the same reason as above
        a[i-1] = a[j-1];
        a[j-1] = tmp;
    }

    // Importantly, the heap we use here is one where LARGER elements are higher up in the heap!
    // So smaller things will sink to the bottom...

    private void sink(int k, int n) {     // sinks element in position k in heap
        // formed by only the first n elements in array
        // (i.e., it ignores elements to the right
        // of the first n elements)

        while (2*k <= n) {                // while there is a left child in this n-sized heap
            int j = 2*k;                  // let j be the left child
            if ((j < n) && less(j,j+1))   // if there is a right child SMALLER than left child
                j++;                      // make j the right child, so j is always the SMALLEST child
            if (less(j, k))               // if parent is smaller than smallest child, stop sinking
                break;
            exch(k, j);                   // otherwise, exchange sinking parent and biggest child
            k = j;                        // reset k to be the new (now lower) parent and do it
        }                                 // all over again...
    }

    public void sort(Item[] a) {
        this.a = a;
        int n = a.length;

        for (int k = n / 2; k >= 1; k--) { // sink all elements in 2nd to bottom level.
            sink(k, n);                    // then sink all elements in 3rd to bottom level.
        }                                  // continue in this way until one reaches the top level.
        // this puts array elements in heap order quickly

        while (n > 1) {                    // sort from right to left
            exch(1, n--);                  // i.e., pull max off top, exchange to end
            sink(1, n);                    // then leave end untouched and do the same
        }                                  // to smaller list. repeat until done.
    }

    public static void main(String[] args) {
        Character[] a = {'N','O','P','R','B','L','E','M'};
        Sorter<Character> heapSorter = new HeapSorter<Character>();
        heapSorter.sort(a);
        System.out.println(Arrays.toString(a));
    }
}