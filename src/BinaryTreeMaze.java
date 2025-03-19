import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTreeMaze extends JPanel {
    private int width, height;
    private int[][] grid;
    private List<Integer> listGrid;
    private List<Integer> indexGrid;
    private List<int[]> skew;

    public BinaryTreeMaze(int w, int h, String skewDirection) {
        this.width = w;
        this.height = h;
        this.grid = new int[height][width];
        listGrid = new ArrayList<>();
        indexGrid = new ArrayList<>();
        initializeSkew(skewDirection);
        generateMaze();
    }

    private void initializeSkew(String skewDirection) {
        Random random = new Random();
        List<int[]> NW = List.of(new int[]{1, 0}, new int[]{0, -1});
        List<int[]> NE = List.of(new int[]{1, 0}, new int[]{0, 1});
        List<int[]> SW = List.of(new int[]{-1, 0}, new int[]{0, -1});
        List<int[]> SE = List.of(new int[]{-1, 0}, new int[]{0, 1});

        switch (skewDirection) {
            case "NW" -> this.skew = NW;
            case "NE" -> this.skew = NE;
            case "SW" -> this.skew = SW;
            case "SE" -> this.skew = SE;
            default -> {
                List<List<int[]>> directions = List.of(NW, NE, SW, SE);
                this.skew = directions.get(random.nextInt(directions.size()));
            }
        }
    }

    private void generateMaze() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = 1; // Initialize the grid with walls
            }
        }

        for (int row = 1; row < height; row += 2) {
            for (int col = 1; col < width; col += 2) {
                grid[row][col] = 0;
                int[] neighbor = findNeighbor(row, col);
                grid[neighbor[0]][neighbor[1]] = 0;
            }
        }
    }

    private int[] findNeighbor(int currentRow, int currentCol) {
        List<int[]> neighbors = new ArrayList<>();
        for (int[] direction : skew) {
            int neighborRow = currentRow + direction[0];
            int neighborCol = currentCol + direction[1];
            if (neighborRow > 0 && neighborRow < height - 1 && neighborCol > 0 && neighborCol < width - 1) {
                neighbors.add(new int[]{neighborRow, neighborCol});
            }
        }
        if (neighbors.isEmpty()) {
            return new int[]{currentRow, currentCol};
        } else {
            return neighbors.get(new Random().nextInt(neighbors.size()));
        }
    }

    void print(){
        for(int i = 0 ; i < height ; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
    }

    List<Integer> ReturnMaze(){
        int ok;
        int index;
        for(int i = 0 ; i < height ; i++) {
            ok = 0;
            for (int j = 0; j < width; j++) {
                index = j + ( i * Proiect.number1);
                listGrid.add(grid[i][j]);
                indexGrid.add(index);
                if (i != height - 1) {
                    index = j + ( (i+1) * Proiect.number1);
                    listGrid.add(grid[i + 1][j]);
                    indexGrid.add(index);
                    ok = 1;
                }
            }
            if(ok == 1)
                i++;
        }

        return listGrid;
    }

    List<Integer> ReturnIndex() {
        return indexGrid;
    }

}