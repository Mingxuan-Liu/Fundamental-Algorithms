package FifthWeek;

import java.util.Scanner;

public class PriorityQueueTest {

    public static void main(String[] args) {
        System.out.println("Use a min heap or max heap for the " +
                "priority queue? (type \"min\" or \"max\"):");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();

        PriorityQueue<String> pq = (userInput.equals("min") ?
                new MinHeapPQ<String>(17) :
                new MaxHeapPQ<String>(17)) ;

        String inputsInOrder = "TPINGOACB";
        for (int i = 0; i < inputsInOrder.length(); i++) {
            String input = "" + inputsInOrder.charAt(i);
            pq.put(input);
            System.out.println("inserted " + input + ", heap: " + pq);
        }

        System.out.println("size of heap: " + pq.size());
        int n = pq.size();
        for (int i = 0; i < n; i++) {
            String output = pq.remove();
            System.out.print("removed " + output + ", heap: " + pq + "\n");
        }
    }

}
