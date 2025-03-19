import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AldousBroderMazeGenerator extends JPanel {
    private static final int CELL_SIZE = 20;
    private final int rows;
    private final int cols;
    private final int[][] maze;
    private final Random random = new Random();

    public AldousBroderMazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
        generateMaze();
        setPreferredSize(new Dimension(cols * CELL_SIZE, rows * CELL_SIZE));
    }

    private void generateMaze() {
        // Initialize maze with walls
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maze[r][c] = 1;
            }
        }

        int startRow = 1 + 2 * random.nextInt(rows / 2);
        int startCol = 1 + 2 * random.nextInt(cols / 2);
        maze[startRow][startCol] = 0;
        int numVisited = 1;

        while (numVisited < (rows / 2) * (cols / 2)) {
            List<int[]> neighbors = findNeighbors(startRow, startCol);
            if (neighbors.isEmpty()) {
                int[] newPos = findRandomNeighbor(startRow, startCol);
                startRow = newPos[0];
                startCol = newPos[1];
                continue;
            }

            boolean progress = false;
            for (int[] neighbor : neighbors) {
                int nrow = neighbor[0];
                int ncol = neighbor[1];
                if (maze[nrow][ncol] == 1) {
                    maze[(nrow + startRow) / 2][(ncol + startCol) / 2] = 0;
                    maze[nrow][ncol] = 0;
                    numVisited++;
                    startRow = nrow;
                    startCol = ncol;
                    progress = true;
                    break;
                }
            }
            if (!progress) {
                int[] newPos = findRandomNeighbor(startRow, startCol);
                startRow = newPos[0];
                startCol = newPos[1];
            }
        }
    }

    private List<int[]> findNeighbors(int row, int col) {
        List<int[]> neighbors = new ArrayList<>();
        if (row > 1 && maze[row - 2][col] == 1) neighbors.add(new int[]{row - 2, col});
        if (row < rows - 2 && maze[row + 2][col] == 1) neighbors.add(new int[]{row + 2, col});
        if (col > 1 && maze[row][col - 2] == 1) neighbors.add(new int[]{row, col - 2});
        if (col < cols - 2 && maze[row][col + 2] == 1) neighbors.add(new int[]{row, col + 2});
        return neighbors;
    }

    private int[] findRandomNeighbor(int row, int col) {
        List<int[]> neighbors = findNeighbors(row, col);
        if (neighbors.isEmpty()) return new int[]{row, col};
        return neighbors.get(random.nextInt(neighbors.size()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aldous-Broder Maze Generator");
        AldousBroderMazeGenerator mazePanel = new AldousBroderMazeGenerator(21, 21);
        frame.add(mazePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}