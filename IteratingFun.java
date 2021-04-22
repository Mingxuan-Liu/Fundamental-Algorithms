package SecondWeek;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class IteratingFun implements Iterable<String>{
    String[] strings;

    public IteratingFun(String[] strings){
        this.strings = Arrays.copyOf(strings,strings.length);
    }

    public Iterator<String> iterator(){
        return new Iterator<String>() {
            private int nextPos = IteratingFun.this.strings.length - 1;
            @Override
            public boolean hasNext() {
                return (nextPos >= 0);
            }

            @Override
            public String next() {
                return strings[nextPos--];
            }
        };
    }

    public static void main(String[] args){
        Stack<String> stack = new Stack<String>();
        stack.push("a");
        stack.push("b");
        stack.push("c");

        for (String s : stack){
            System.out.printf("%s ", s);
        }
        System.out.println();
    }
}
