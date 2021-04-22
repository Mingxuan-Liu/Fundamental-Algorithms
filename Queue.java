package ThirdWeek;

/**
 * First create a stack interface so that all needed operations can be guaranteed.
 * @param <Item>
 */
public interface Queue<Item> extends Iterable<Item> {

    boolean isEmpty();
    int size();
    void enqueue(Item item);
    Item dequeue();
}
