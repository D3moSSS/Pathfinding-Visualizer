import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.List;

public class DrawMaze extends JPanel{

    static List<Integer> maze;
    static List<Integer> indexMaze;
    static int n , ok;
    static Graphics2D g2d;

    public DrawMaze(List<Integer> maze1 , List<Integer> indexMaze1 , int ok1) {
        maze = maze1;
        n = 0;
        ok = ok1;
        indexMaze = indexMaze1;

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g2d = (Graphics2D) g;

        if(Proiect.Maze && ok == 1) {

            DisplayEmpty();

            for(int i = 0 ; i < n ; i++)
            {
                if(maze.get(i) == 1) {
                    Rectangle cell = MatrixDraw.cells1.get(indexMaze.get(i));
                    MatrixDraw.Colors.set(indexMaze.get(i), "Black");
                    g2d.setColor(new Color(92, 60, 11, 255));
                    g2d.fill(cell);
                }
                else {
                    Rectangle cell = MatrixDraw.cells1.get(indexMaze.get(i));
                    MatrixDraw.Colors.set(indexMaze.get(i), "White");
                    g2d.setColor(new Color(196, 132, 29, 255));
                    g2d.fill(cell);
                }

            }
            g2d.setColor(Color.BLACK);
            for(Rectangle reg : MatrixDraw.cells1){
                g2d.draw(reg);
            }
        }
        else if(Proiect.Maze && ok == 0)
        {
            DisplayFullWithWall();

            for(int i = 0 ; i < n ; i++)
            {
                if(maze.get(i) == 0) {
                    Rectangle cell = MatrixDraw.cells1.get(indexMaze.get(i));
                    MatrixDraw.Colors.set(indexMaze.get(i), "White");
                    g2d.setColor(new Color(196, 132, 29, 255));
                    g2d.fill(cell);
                }

            }
            g2d.setColor(Color.BLACK);
            for(Rectangle reg : MatrixDraw.cells1){
                g2d.draw(reg);
            }
        }

    }

    public void DisplayEmpty(){

        for(int i = 0 ; i < Proiect.number1 * Proiect.number1 ; i++)
        {
            Rectangle cell1 = MatrixDraw.cells1.get(i);
            g2d.setColor(new Color(196, 132, 29, 255));
            g2d.fill(cell1);
        }
    }

    public void DisplayFullWithWall(){

        for(int i = 0 ; i < Proiect.number1 * Proiect.number1 ; i++)
        {
            Rectangle cell1 = MatrixDraw.cells1.get(i);
            g2d.setColor(new Color(92, 60, 11, 255));
            g2d.fill(cell1);
            MatrixDraw.Colors.set(i, "Black");
        }
    }
}
