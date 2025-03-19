import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AStarsAlgorithm extends JFrame {

    static Map<Point, List<Node>> graph;
    static Map<Point, Integer> costVisited;
    static Map<Point, Point> visited;
    static int n;
    static Point start;
    static Point fin;
    static int[][] grid;
    static Node curNode;
    static PriorityQueue<Node> queue;
    static Queue<Point> pointQueue;
    static int[][] disH , disG;
    static PriorityQueue<Node> queue1;
    static int disTotal;

    public AStarsAlgorithm(){

        queue1 = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pointQueue = new LinkedList<>();
        graph = new HashMap<>();
        costVisited = new HashMap<>();
        visited = new HashMap<>();
        queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        n = Proiect.number1;
        start = new Point(MatrixDraw.Start.x, MatrixDraw.Start.y);
        fin = new Point(MatrixDraw.Final.x, MatrixDraw.Final.y);
        grid = new int[n][n];
        disH = new int[n][n];
        disG = new int[n][n];
        generateGrid();
        generateGraphWithOutDia();
        AStarsWithoutGrid();
        curNode = new Node(0, start);
        queue.add(new Node(0, start));
        costVisited.put(start, 0);
        visited.put(start, null);

    }

    static void generateGraphWithOutDia() {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                Point current = new Point(x, y);
                graph.put(current, getNeighborsWithOutDia(x, y));
            }
        }
    }

    static java.util.List<Node> getNeighborsWithOutDia(int x, int y) {
        List<Node> neighbors = new ArrayList<>();
        int[][] ways = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for (int[] way : ways) {
            int nx = x + way[0], ny = y + way[1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                neighbors.add(new Node(0, new Point(nx, ny)));
            }
        }
        return neighbors;
    }

    // Funcție pentru a desena grid-ul
    private void generateGrid() {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if(MatrixDraw.Colors.get(y + (x * Proiect.number1)).equals("Black"))
                    grid[y][x] = 1;
            }
        }
    }

    public static Point AStars() {

        int nr = 0;
        while (!queue.isEmpty()) {

            curNode = queue.poll();
            if (curNode.point.equals(fin)) {
                break;
            }
            List<Node> neighbors = graph.get(curNode.point);
            if (neighbors != null) {
                for (Node neighbor : neighbors) {
                    if (!costVisited.containsKey(neighbor.point) && grid[neighbor.point.y][neighbor.point.x] != 1) {
                        disTotal = (int) Math.ceil(disG[neighbor.point.x][neighbor.point.y] + disH[neighbor.point.x][neighbor.point.y]);
                        queue.add(new Node(disTotal, neighbor.point));
                        costVisited.put(neighbor.point, disTotal);
                        visited.put(neighbor.point, curNode.point);
                    }
                }
            }

            pointQueue.clear();
            for(Node n : queue)
                pointQueue.add(n.point);

            if(nr == Proiect.slider.getValue())
                break;
            else
                nr++;
        }

        if(queue.isEmpty() && curNode.point.x != fin.x && curNode.point.y != fin.y) {
            isEmpty();
        }

        return curNode.point;
    }

    public static void AStarsWithoutGrid() {

        queue.add(new Node(0 , start));
        while (!queue.isEmpty()) {

            curNode = queue.poll();
            List<Node> neighbors = graph.get(curNode.point);
            if (neighbors != null) {
                for (Node neighbor : neighbors) {
                    if (!costVisited.containsKey(neighbor.point)) {
                        disG[neighbor.point.x][neighbor.point.y] = disG[curNode.point.x][curNode.point.y] + 1;
                        disH[neighbor.point.x][neighbor.point.y] = Math.abs(neighbor.point.x - fin.x) + Math.abs(neighbor.point.y - fin.y);
                        queue.add(new Node((int) disG[neighbor.point.x][neighbor.point.y], neighbor.point));
                        costVisited.put(neighbor.point, (int) disG[neighbor.point.x][neighbor.point.y]);
                        visited.put(neighbor.point, curNode.point);
                    }
                }
            }

        }

        visited.clear();
        costVisited.clear();

    }

    public static void AStarsAll() {

        while (!queue.isEmpty()) {

            curNode = queue.poll();
            if (curNode.point.equals(fin)) {
                break;
            }
            List<Node> neighbors = graph.get(curNode.point);
            if (neighbors != null) {
                for (Node neighbor : neighbors) {
                    if (!costVisited.containsKey(neighbor.point) && grid[neighbor.point.y][neighbor.point.x] != 1) {
                        disTotal = disG[neighbor.point.x][neighbor.point.y] + disH[neighbor.point.x][neighbor.point.y];
                        queue.add(new Node(disTotal, neighbor.point));
                        costVisited.put(neighbor.point, disTotal);
                        visited.put(neighbor.point, curNode.point);
                    }
                }
            }

            pointQueue.clear();
            for(Node n : queue)
                pointQueue.add(n.point);

        }

        if(queue.isEmpty() && curNode.point.x != fin.x && curNode.point.y != fin.y) {
            isEmpty();
        }

    }

    public Map<Point, Point> ReturnVecini(){
        return visited;
    }

    public Queue<Point> ReturnQueue(){
        return pointQueue;
    }

    public Point ReturnCurNode(){
        return curNode.point;
    }

    public int[][] ReturnDisG(){
        return disG;
    }

    public int[][] ReturnDisH(){
        return disH;
    }

    static class Node {
        int cost;
        Point point;

        public Node(int cost, Point point) {
            this.cost = cost;
            this.point = point;
        }
    }

    public static void isEmpty(){
        LeeDraw.verify = true;
        Proiect.resetLee = 1;
        if (Proiect.matrixDraw.isAncestorOf(Proiect.leeDraw))
            Proiect.matrixDraw.remove(Proiect.leeDraw);
        JOptionPane.showMessageDialog( null, "Nu s o gasit drum pentru DFS! ", "Error", JOptionPane.INFORMATION_MESSAGE);

    }

}
