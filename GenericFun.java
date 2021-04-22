package SecondWeek;

import java.util.Objects;
import java.util.Stack;
import java.util.Scanner;

public class GenericFun<Item extends Comparable<Item>> {
    private Item item;
    private Item[] items;

    public GenericFun(Item item, Item anotherItem){
        this.item = item;
        this.items = (Item[]) (new Comparable[10]);
        item.compareTo(anotherItem);
        System.out.println(item);
    }

    public static <T> void printArray(T[] array){
        for (T element : array){
            System.out.printf("%s ", element); // print elements separated by spaces
        }
        System.out.println();
    }

    public static void main (String[] args){
        Stack<String> stack = new Stack<String>();
        GenericFun<String> gf = new GenericFun<String>("bob","fred");
        System.out.println("I RAN");
    }
}
