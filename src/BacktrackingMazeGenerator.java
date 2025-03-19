import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BacktrackingMazeGenerator extends JPanel {
    private final int rows, cols;
    private final int[][] grid;
    private final int cellSize = 20;
    private List<Integer> listGrid;
    private List<Integer> indexGrid;

    public BacktrackingMazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
        listGrid = new ArrayList<>();
        indexGrid = new ArrayList<>();
        generateMaze();
    }

    public void generateMaze() {
        // Initialize grid with walls (1) and passages (0)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = 1;
            }
        }

        Random rand = new Random();
        int crow = rand.nextInt(rows / 2) * 2 + 1;
        int ccol = rand.nextInt(cols / 2) * 2 + 1;
        List<int[]> track = new ArrayList<>();
        track.add(new int[]{crow, ccol});
        grid[crow][ccol] = 0;
        listGrid.add(grid[crow][ccol]);
        indexGrid.add(ccol + (crow * rows));


        while (!track.isEmpty()) {
            int[] current = track.get(track.size() - 1);
            crow = current[0];
            ccol = current[1];
            List<int[]> neighbors = findNeighbors(crow, ccol);

            if (neighbors.isEmpty()) {
                track.remove(track.size() - 1);
            } else {
                int[] neighbor = neighbors.get(rand.nextInt(neighbors.size()));
                int nrow = neighbor[0];
                int ncol = neighbor[1];
                grid[nrow][ncol] = 0;
                listGrid.add(grid[nrow][ncol]);
                indexGrid.add(ncol + (nrow* rows));
                grid[(nrow + crow) / 2][(ncol + ccol) / 2] = 0;
                listGrid.add(grid[(nrow + crow) / 2][(ncol + ccol) / 2]);
                indexGrid.add(((ncol + ccol) / 2) + (((nrow + crow) / 2) * rows));
                track.add(new int[]{nrow, ncol});
            }
        }
    }

    private List<int[]> findNeighbors(int row, int col) {
        List<int[]> neighbors = new ArrayList<>();

        if (row > 1 && grid[row - 2][col] == 1) neighbors.add(new int[]{row - 2, col});
        if (row < rows - 2 && grid[row + 2][col] == 1) neighbors.add(new int[]{row + 2, col});
        if (col > 1 && grid[row][col - 2] == 1) neighbors.add(new int[]{row, col - 2});
        if (col < cols - 2 && grid[row][col + 2] == 1) neighbors.add(new int[]{row, col + 2});

        return neighbors;
    }

    List<Integer> ReturnMaze(){
        return listGrid;
    }

    List<Integer> ReturnIndex(){
        return indexGrid;
    }
}