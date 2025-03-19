import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Dijkstra extends JFrame {

    static int n;
    static Map<Point, Integer> costVisited = new HashMap<>();
    static Map<Point, List<Point>> graph;
    static Queue<Point> queue;
    static Map<Point, Point> visited;
    static Point start , fin;
    static Point curNode;
    static int[][] grid;

    public Dijkstra(){
        graph = new HashMap<>();
        queue = new LinkedList<>();
        visited = new HashMap<>();
        start = new Point(MatrixDraw.Start.x , MatrixDraw.Start.y);
        fin = new Point(MatrixDraw.Final.x , MatrixDraw.Final.y);
        n = Proiect.number1;
        grid = new int[n][n];
        generateGrid();
        generateGraph();

        curNode = start;
        queue.add(start);
        costVisited.put(start,0);
        visited.put(start,null);

    }

    private void generateGrid() {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if(MatrixDraw.Colors.get(y + (x * Proiect.number1)).equals("Black"))
                    grid[y][x] = 1;
            }
        }
    }

    private void generateGraph() {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (grid[y][x] == 0) {
                    Point p = new Point(x, y);
                    graph.put(p, getNextNodes(x, y));
                }
            }
        }
    }

    private List<Point> getNextNodes(int x, int y) {
        List<Point> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < n && grid[newY][newX] == 0) {
                neighbors.add(new Point(newX, newY));
            }
        }
        return neighbors;
    }

    public static Point DijkstraAlgorith() {

        if (!queue.isEmpty()) {
            curNode = queue.poll();

            List<Point> nextNodes = graph.get(curNode);
            for (Point nextNode : nextNodes) {

                if (!visited.containsKey(nextNode)) {

                    queue.add(nextNode);
                    visited.put(nextNode, curNode);
                }
            }

        }

        return curNode;
    }

    public Map<Point, Point> ReturnVecini(){
        return visited;
    }

    public Queue<Point> ReturnQueue(){
        return queue;
    }

    public Point ReturnCurNode(){
        return curNode;
    }

    public static void BFSAlgorithAll() {

        while (!queue.isEmpty()) {
            curNode = queue.poll();
            if (curNode.x == fin.x && curNode.y == fin.y) {
                break;
            }

            List<Point> nextNodes = graph.get(curNode);
            for (Point nextNode : nextNodes) {
                if (!visited.containsKey(nextNode)) {
                    queue.add(nextNode);
                    visited.put(nextNode, curNode);
                }
            }

        }

    }
}
