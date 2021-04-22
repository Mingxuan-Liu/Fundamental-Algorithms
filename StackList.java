package ThirdWeek;

import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;
import java.util.Iterator;

public class StackList<Item> implements Iterable<Item>, Stack<Item>{
    private class Node{
        Item item;
        Node next;
    }

    private Node first; // first node of the linked list
    private int size; // number of items on the stack
    private int modCount; // for the "fail-fast" iterator

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
        size++;
        modCount++;
    }

    @Override
    public Item peek() {
        return first.item;
    }

    @Override
    public Item pop() {
        if (this.isEmpty()){
            throw new EmptyStackException();
        }
        else{
            Item item = first.item;
            first = first.next; // dropped reference to node formerly first accomplishes the deletion
            size--;
            modCount++;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node node = first;
            private int savedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != savedModCount){
                    throw new ConcurrentModificationException();
                }
                return (node != null);
            }

            @Override
            public Item next() {
                if (modCount != savedModCount) {
                    throw new ConcurrentModificationException();
                }
                Item itemToReturn = node.item;
                node = node.next;
                return itemToReturn;
            }
        };
    }
}
