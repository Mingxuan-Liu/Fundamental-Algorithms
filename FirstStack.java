package SecondWeek;

import java.util.Scanner;

public class FirstStack {
    private int size;
    private String[] items; // only able to store Strings.

    public FirstStack(int capacity){
        size = 0;
        items = new String[capacity];
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public boolean isFull() {
        return (size == items.length);
    }

    public void push(String s){
        if (this.isFull()){
            throw new RuntimeException("Stack Overflow");
        }else {
            items[size++] = s;
        }
    }

    public String pop(){
        if (this.isEmpty()){
            throw new RuntimeException("Stack Underflow"); // perfectly acceptable to crash the program here.
        }else{
            return items[--size];
        }
    }

    public static void main(String[] args){
        System.out.println("Enter a sequence of words and/or dashes separated by spaces.");
        System.out.println("Words will be pushed to an initially empty stack with capacity 5.");
        System.out.println("Dashes will result in popping the stack and printing the result");

        Scanner scanner = new Scanner(System.in);
        String userInputStr = scanner.nextLine();
        Scanner close;

        String[] userInputs = userInputStr.split(" ");
        FirstStack stack = new FirstStack(5);

        for (int i = 0; i < userInputs.length; i ++){
            if (userInputs[i].equals("-")) System.out.println(stack.pop());
            else stack.push(userInputs[i]);
        }
    }
}
