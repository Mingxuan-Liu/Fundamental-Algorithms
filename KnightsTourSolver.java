package SecondWeek;

/**
 * @author Mingxuan Liu
 * @version 2.0.1
 * @date 02/11/2021
 * @helped by Peilin Wu
 */

import java.awt.Point;
import java.nio.file.StandardWatchEventKinds;
import java.util.Scanner;
import java.util.Stack;

public class KnightsTourSolver {

    public static final int NUMBER_OF_KNIGHT_MOVES = 8;

    private int size;               // size of the board - note, sizes greater than 6 result in MANY solutions
    private int[][] visited;        // the array that stores each solution found, for subsequent printing
    private Stack<Integer> stack;   // a stack, for use in accomplishing the backtracking
    private int[] ways;             // an array to record the number of moves already used

    public KnightsTourSolver(int size) {
        this.size = size;
        this.visited = new int[this.size][this.size];
        this.stack = new Stack<Integer>();
        this.ways = new int[size * size + 1];
    }

    /**
     * @param thisPoint: the current position of knight
     * @param way: the move taken in the next step
     */
    public int getNextPoint(int thisPoint, int way){
        return switch (way) {
            case 0 -> thisPoint - 1 - 2 * this.size;
            case 1 -> thisPoint + 1 + 2 * this.size;
            case 2 -> thisPoint - 1 + 2 * this.size;
            case 3 -> thisPoint + 2 - this.size;
            case 4 -> thisPoint - 2 + this.size;
            case 5 -> thisPoint + 1 - 2 * this.size;
            case 6 -> thisPoint - 2 - this.size;
            case 7 -> thisPoint + 2 + this.size;
            default -> throw new IllegalStateException("Unexpected value: " + way);
        };
    }

    public boolean isInTheBoard(int nextPoint){
        return (((nextPoint/this.size < this.size)&&(nextPoint/this.size>=0))&&((nextPoint%this.size < this.size)&&(nextPoint%this.size >=0)));
    }

    public boolean isEmpty(int nextPoint){
            return (this.visited[nextPoint % this.size][nextPoint / this.size] == 0);
    }

    public void printStack() {
        System.out.println(stack);
    }

    public void unitTest(){
        int visitedNum = 1;
        int thisPoint = 0;
        stack.push(thisPoint);
        int xIndex = thisPoint%this.size;
        int yIndex = thisPoint/this.size;
        for (int i = ways[visitedNum]; i < 8; i++) {
            int nextPoint = getNextPoint(thisPoint, i);
            if (isInTheBoard(nextPoint) && isEmpty(nextPoint)) {
                ways[visitedNum] = i;
                stack.push(nextPoint);
                thisPoint = nextPoint;
                visited[xIndex][yIndex] = ++visitedNum;
                printStack();
                break;
            }
            ways[visitedNum]++;
        }
    }

    /**
     * @variable numSolutions: the number of solutions already found.
     * @variable visitedNum: the number of points already visited.
     * @variable xIndex: the X-Coordinate of current point.
     * @variable yIndex: the y-Coordinate of current point.
     * @variable thisPoint: the number sequence of current point.
     * @variable nextPoint: the number sequence of next point.
     */
    public void findSolutions() {
        int numSolutions = 1;
        int visitedNum = 1;
        this.visited[0][0] = visitedNum;
        int thisPoint = 0;
        stack.push(thisPoint);
        while (true) {
            int xIndex = thisPoint%this.size;
            int yIndex = thisPoint/this.size;
            if (visitedNum == size * size) {
                printBoard(numSolutions++);
            }
            for (int i = ways[visitedNum]; i < 8; i++) {
                int nextPoint = getNextPoint(thisPoint, i);
                if (isInTheBoard(nextPoint) && isEmpty(nextPoint)) {
                    ways[visitedNum] = i;
                    stack.push(nextPoint);
                    thisPoint = nextPoint;
                    visited[xIndex][yIndex] = ++visitedNum;
                    break;
                }
                ways[visitedNum]++;
            }
            if (ways[visitedNum] >= 8) {
                stack.pop();
                ways[visitedNum] = 0;
                ways[--visitedNum]++;
                visited[xIndex][yIndex] = 0;
                if (stack.isEmpty()) {
                    System.out.println("Search Complete");
                    break;
                }
                thisPoint = stack.lastElement();
            }
        }
    }

    public void printBoard(int numSolutions) {  // call this method each time a solution is found
        System.out.println("Solution " + numSolutions);
        for (int r = 0; r < this.size; r++) {
            for (int c = 0; c < this.size; c++)
                System.out.print(visited[r][c] + "\t");
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        System.out.println("Enter board size to solve: ");
        Scanner scanner = new Scanner(System.in);
        int boardSize = scanner.nextInt();
        scanner.close();
        KnightsTourSolver knightsTourSolver = new KnightsTourSolver(boardSize);
        knightsTourSolver.findSolutions();
        //knightsTourSolver.unitTest();
    }
}
