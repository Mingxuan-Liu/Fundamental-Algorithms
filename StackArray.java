package ThirdWeek;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class StackArray<Item> implements Stack<Item>,Iterable<Item>{

    private Item[] items; // An array for storing data
    private int size; // Record the length of the stack and the index to put the next element

    private int modCount;

    public StackArray(){
        items = (Item[]) (new Object[1]);
        size = 0;
        modCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(Item item) {
        if (size == items.length){
            resize(2*items.length);
        }
        items[size++] = item;
        modCount++;
    }

    private void resize (int capacity){
        Item[] newArray = (Item[]) (new Object[capacity]);
        for (int i = 0; i < size; i++){
            newArray[i] = items[i];
        }
        items = newArray;
    }

    @Override
    public Item peek() {
        if (! this.isEmpty()){
            return items[size-1];
        }
        else{
            return null;
        }
    }

    @Override
    public Item pop() {
        if (this.isEmpty()){
            throw new RuntimeException("Tried to pop an empty stack!");
        }
        Item item = items[--size];
        items[size] = null;
        if (this.size != 0 &&this.size == items.length/4){
            resize(items.length/2);
        }
        modCount++;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int nextPos = 0;
            private int savedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (modCount != savedModCount) throw new ConcurrentModificationException();
                return nextPos<size;
            }

            @Override
            public Item next() {
                if (modCount != savedModCount) throw new ConcurrentModificationException();
                return items[nextPos++];
            }
        };
    }

    public String toString(){
        String s = "";
        for (Item item:this){
            s += item.toString() + " ";
        }
        return s;
    }
}
