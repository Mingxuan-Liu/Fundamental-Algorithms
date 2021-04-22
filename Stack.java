package ThirdWeek;

import java.util.Iterator;
/**
 * Stack has many ways to implement. Therefore, it seems handy to create an interface that stores
 * all the basic operations need to execute in following instance classes.
 */
public interface Stack<Item> extends Iterable<Item> {
    boolean isEmpty();
    int size();
    void push(Item item);
    Item peek();
    Item pop();
    Iterator<Item> iterator();
}
