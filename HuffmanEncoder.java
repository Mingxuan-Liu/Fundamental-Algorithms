package TenthWeek;

import java.util.Scanner;

public class HuffmanEncoder {

    private static class Node implements Comparable<Node> {
        private final String symbols;
        private int frequency;
        private Node left;
        private Node right;

        public Node(String symbols, int frequency) {
            this.symbols = symbols;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node other) {return this.frequency - other.frequency;}
    }

    private final Node root;
    private String uniqueSymbols = "";

    public HuffmanEncoder(String s) {
        MinPriorityQueueHeap<Node> heap = new MinPriorityQueueHeap<>();
        for (int i = 0; i < s.length(); i++){
            var symbol = Character.toString(s.charAt(i));
            if (!uniqueSymbols.contains(symbol)){
                uniqueSymbols += symbol;
            }
        }
        for (int i = 0; i < uniqueSymbols.length(); i++){
            var tmp = new Node(Character.toString(uniqueSymbols.charAt(i)), 0);
            for (int j = 0; j < s.length(); j++){
                if (tmp.symbols.equals(Character.toString(s.charAt(j)))){
                    tmp.frequency++;
                }
            }
            heap.put(tmp);
        }
        Node temp = null;
        while(heap.size() > 1){
            Node left = heap.removeMinItem();
            Node right = heap.removeMinItem();
            temp = new Node(left.symbols + right.symbols, left.frequency + right.frequency);
            temp.left = left;
            temp.right = right;
            heap.put(temp);
        }
        root = temp;
    }

    private String codeFor(char c) {
        StringBuilder record = new StringBuilder();
        Node pointer = root;
        while (pointer.left != null && pointer.right != null){
            if (pointer.left.symbols.contains(Character.toString(c))){
                record.append("0");
                pointer = pointer.left;
            }
            else if (pointer.right.symbols.contains(Character.toString(c))){
                record.append("1");
                pointer = pointer.right;
            }
        }
        return record.toString();
    }

    public String encode(String s) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            newString.append(codeFor(s.charAt(i)));
        }
        return newString.toString();
    }

    public String decode(String s) {
        Node pointer = root;
        StringBuilder decodedString = new StringBuilder();
        while(!s.isEmpty()) {
            if (s.charAt(0) == '0') {
                pointer = pointer.left;
                s = s.substring(1);
            } else if (s.charAt(0) == '1') {
                pointer = pointer.right;
                s = s.substring(1);
            }
            if (pointer.left == null && pointer.right == null) {
                decodedString.append(pointer.symbols);
                pointer = root;
            }
        }
        return decodedString.toString();
    }

    public void printCodes() {
        for (int i = 0; i < uniqueSymbols.length(); i++) {
            char c = uniqueSymbols.charAt(i);
            System.out.println(c + " : " + codeFor(c));
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to serve as the basis for the Huffman Coding: ");
        String s = scanner.nextLine();  // Try the following: "a_DeaD_DaD_ceDeD_a_baD_babe_a_beaDeD_abaca_beD";
        scanner.close();

        HuffmanEncoder huffmanEncoder = new HuffmanEncoder(s);

        System.out.println("\nCodes Used:");
        huffmanEncoder.printCodes();
        System.out.println();


        String encodedString = huffmanEncoder.encode(s);
        System.out.println("String provided can be encoded in " + encodedString.length() + " bits (+ code information)");
        System.out.println(encodedString);
        System.out.println();

        String decodedString = huffmanEncoder.decode(encodedString);
        System.out.println("Decodes as: " + decodedString);
        System.out.println("Note, this would have required " + (decodedString.length()*8) + " bits required to store in ASCII characters)");
    }
}
