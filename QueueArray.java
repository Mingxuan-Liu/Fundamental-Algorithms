package ThirdWeek;
/* QueueArray<Item>  (array-based, iterable)
 *
 * Methods
 * =======
 * boolean isEmpty()       : returns true if the queue is empty, false otherwise
 * int size()              : returns the number of elements in the queue
 * void enqueue(Item item) : adds item to the end of the queue
 * Item dequeue()          : removes the front-most item from the queue and returns it
 * Iterator<Item> iterator()     : returns a "fail fast" head-to-tail iterator for the queue
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class QueueArray<Item> implements Iterable<Item>, Queue<Item>{

    private Item[] items; // here we utilize the items[] array to implement the queue structure.
    private int    head;
    private int    tail;
    private int    size;
    private int    modCount;  // for "fail fast" iterator

    public QueueArray() {
        items = (Item[]) (new Object[1]);
        head = 0;
        tail = 0;
        size = 0;
        modCount = 0;  // these last 4 assignments are superfluous, of course. Numeric instance variables are 0 by fault.
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (size == items.length - 1) {
            resize(2 * items.length); // note this condition of (size == items.length - 1)
        }

        items[tail++] = item;

        if (tail == items.length) {
            tail = 0;
        }

        size++;
        modCount++;
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Tried to dequeue an empty queue!"); // both enqueue() and dequeue() have this check mechanism.
        }
        else {
            Item itemToReturn = items[head];
            items[head++] = null; //prevents loitering, also for garbage collection
            size--;
            if (head == items.length) {
                head = 0;
            }
            if (size == items.length / 4) { // note this is a kind of integer division
                resize(items.length / 2);
            }

            modCount++;
            return itemToReturn;
        }
    }

    private void resize(int capacity) {
        //Item[] newArray = new Item[capacity];  // <-- Remember, you can't do this!
        Item[] newArray = (Item[]) new Object[capacity];  // <-- so we settle for the "ugly cast"
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(head + i) % items.length];
        }
        items = newArray; // picked up by the garbage collection
        head = 0;
        tail = size;
    }

    public Iterator<Item> iterator() { // this iterator operation is a little bit different from the one of stack
        return (new Iterator<Item>() {

            private int pos = head;
            private int count = 0; // the count record the number of elements that have already been iterated
            private int savedModCount = modCount;  // needed to make iterator "fail fast"

            public boolean hasNext() {
                if (modCount != savedModCount) throw new ConcurrentModificationException();
                return (count < size);
            }

            public Item next() {
                if (modCount != savedModCount) throw new ConcurrentModificationException();
                Item item = items[pos++];
                if (pos == items.length)
                    pos = 0;
                count++;
                return item;
            }
        });
    }
}