package FifthWeek;

public interface PriorityQueue<Item extends Comparable<Item>> {

    void put(Item item);
    Item remove();
    int size();

}
