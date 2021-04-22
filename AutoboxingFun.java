package SecondWeek;

import java.util.Stack;

public class AutoboxingFun {

    public static Integer square(Integer x){
        return x*x;
    }

    public static void main(String[] args){
        Stack<String> stack = new Stack<String>();
        stack.push("bob");
        Stack<Integer> stackOfInts = new Stack<Integer>();

        Integer a = 1000; // result of autoboxing
        System.out.println(a.equals(3));
        Character c= 'x';
        Double d = 3.14;

        // Byte, Long, Short, Boolean, Float.
        System.out.println(square(3));

        Stack<Integer> s = new Stack<Integer>();
        s.push(17);
        s.push(18);
        Integer y = s.pop();
        System.out.println(y.equals(20));
    }
}
