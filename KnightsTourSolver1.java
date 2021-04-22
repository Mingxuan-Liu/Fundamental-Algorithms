package SecondWeek;

import java.awt.Point;
import java.util.Scanner;
import java.util.Stack;

public class KnightsTourSolver1 {

    public static final int NUMBER_OF_KNIGHT_MOVES = 8;

    private int size;               // size of the board - note, sizes greater than 6 result in MANY solutions
    private int[][] visited;        // the array that stores each solution found, for subsequent printing
    private Stack<Point> stack;   // a stack, for use in accomplishing the bactracking
    private int[] ways;  // an array for tracking way number of each steps

    public KnightsTourSolver1(int size) {
        this.size = size;
        this.visited = new int[size][size];
        this.stack = new Stack<>();
        this.ways = new int[size * size + 1];
    }

    public boolean isInTheBoard(Point p) {
        return (p.x >= 0 && p.x < this.size) && (p.y >= 0 && p.y < this.size);
    }

    public boolean isEmpty(Point p) {
        return this.visited[p.x][p.y] == 0;
    }

    public Point getNextPoint(Point p, int way) {
        Point next;
        switch (way) {
            case 0:
                next = new Point(p.x - 2, p.y + 1);
                break;
            case 1:
                next = new Point(p.x - 1, p.y + 2);
                break;
            case 2:
                next = new Point(p.x + 1, p.y + 2);
                break;
            case 3:
                next = new Point(p.x + 2, p.y + 1);
                break;
            case 4:
                next = new Point(p.x + 2, p.y - 1);
                break;
            case 5:
                next = new Point(p.x + 1, p.y - 2);
                break;
            case 6:
                next = new Point(p.x - 1, p.y - 2);
                break;
            case 7:
                next = new Point(p.x - 2, p.y - 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + way);
        }
        return next;
    }

    public void printStack() {  // not necessary, but may prove helpful in debugging...
        System.out.println(stack.toString());
    }

    public void findSolutions() {
        int numSolutions = 0;
        int visitedNum = 1;
        this.visited[0][0] = visitedNum;
        Point thisPoint = new Point(0, 0);
        stack.push(thisPoint);
        while (true) {
            if (visitedNum == size * size) {
                printBoard(++numSolutions);
            }
            for (int i = ways[visitedNum]; i < 8; i++) {
                Point nextPoint = getNextPoint(thisPoint, i);
                if (isInTheBoard(nextPoint) && isEmpty(nextPoint)) {
                    ways[visitedNum] = i;
                    stack.push(nextPoint);
                    thisPoint = nextPoint;
                    visited[thisPoint.x][thisPoint.y] = ++visitedNum;
                    break;
                }
                ways[visitedNum]++;
            }
            if (ways[visitedNum] >= 8) {
                ways[visitedNum] = 0;
                ways[--visitedNum]++;
                visited[thisPoint.x][thisPoint.y] = 0;
                stack.pop();
                if (stack.isEmpty()) {
                    System.out.println("Search Complete");
                    break;
                }
                thisPoint = stack.peek();
            }
        }
    }

    public void printBoard(int numSolutions) {  // call this method each time a solution is found
        System.out.println("Solution " + numSolutions);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++)
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
    }
}
