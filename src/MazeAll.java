import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public interface MazeAll {

    void OneMaze();

    default void Draw(){

        MatrixDraw.RED = -1;
        MatrixDraw.GREEN = -1;
        MatrixDraw.selectedCell = null;
        if (Proiect.matrixDraw.isAncestorOf(Proiect.drawMaze)) {
            System.out.println("S o sters");
            Proiect.matrixDraw.remove(Proiect.drawMaze);
        }
        Proiect.Maze = true;

    }

    default void DrawMaze(List<Integer> maze1){

        Proiect.matrixDraw.add(Proiect.drawMaze);
        Proiect.timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Proiect.drawMaze.setOpaque(false);
                Proiect.drawMaze.setBounds(0, 0, Proiect.width, Proiect.height );
                Proiect.matrixDraw.revalidate();
                Proiect.matrixDraw.repaint();
                if (DrawMaze.n == maze1.size()) {
                    System.out.println(DrawMaze.n);
                    Proiect.timer.stop();
                    Proiect.Maze = false;
                }
                else {
                    if (DrawMaze.n + Proiect.slider.getValue() <= maze1.size()) {
                        DrawMaze.n += Proiect.slider.getValue() + 1;
                    } else
                        DrawMaze.n = maze1.size();
                }

            }
        });
        Proiect.timer.start();
    }

}

class BinaryTreeMazeDraw implements MazeAll {

    @Override
    public void OneMaze(){

        Draw();

        BinaryTreeMaze maze = new BinaryTreeMaze(Proiect.number1, Proiect.number1, "NW");
        List<Integer> maze1 = maze.ReturnMaze();
        List<Integer> indexMaze = maze.ReturnIndex();


        Proiect.drawMaze = new DrawMaze(maze1, indexMaze , 1);

        DrawMaze(maze1);

    }

}

class BackTrackingMazeDraw implements MazeAll {

    @Override
    public void OneMaze(){

        Draw();

        BacktrackingMazeGenerator maze = new BacktrackingMazeGenerator(Proiect.number1, Proiect.number1);
        List<Integer> maze1 = maze.ReturnMaze();
        List<Integer> indexMaze = maze.ReturnIndex();

        Proiect.drawMaze = new DrawMaze(maze1, indexMaze , 0);

        DrawMaze(maze1);

    }

}

class AldousBroderMazeGeneratorDraw implements MazeAll {

    @Override
    public void OneMaze(){

        Draw();

        AldousBroderMazeGenerator maze = new AldousBroderMazeGenerator(Proiect.number1, Proiect.number1);
        //List<Integer> maze1 = maze.ReturnMaze();
        //List<Integer> indexMaze = maze.ReturnIndex();
        //Proiect.drawMaze = new DrawMaze(maze1, indexMaze , 0);

        //DrawMaze(maze1);

    }

}
