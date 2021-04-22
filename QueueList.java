package ThirdWeek;

import java.lang.reflect.Modifier;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueList<Item> implements Queue<Item>, Iterable<Item> {
    private class Node{
        Item item;
        Node next;
    }

    private Node first; // the head of the list (and front of the line/queue)
    private Node last; // the tail of the list (and end of the line/queue)
    private int size;
    private int modCount;

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(Item item) {
        Node node = new Node();
        node.item = item;

        if (this.isEmpty()){
            first = node;
        }else{
            last.next = node;
        }
        last = node; // an operation that should be done in both cases
        size++;
        modCount++;
    }

    @Override
    public Item dequeue() {
        if (this.isEmpty()){
            throw new NoSuchElementException();
        }
        else if (first == last){
            Item itemToReturn = first.item;
            first = null;
            last = null;
            size--;
            modCount++;
            return itemToReturn;
        }
        else{
            Item itemToReturn = first.item;
            first = first.next;
            size--;
            modCount++;
            return itemToReturn;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node node = first;
            private int savedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (savedModCount!=modCount) throw new ConcurrentModificationException();
                return (node != null);
            }

            @Override
            public Item next() {
                if (savedModCount!=modCount) throw new ConcurrentModificationException();
                Item itemToReturn = node.item;
                node = node.next;
                return itemToReturn;
            }
        };
    }
}
