import javax.swing.*;
import java.awt.*;


public interface AlgorithAll {

    void OneDraw();

    default void Start() throws Exception {

        if(MatrixDraw.Start.equals(new Point(-1,-1)) || MatrixDraw.Final.equals(new Point(-1,-1))) {
            Proiect.button6.setEnabled(true);
            throw new Exception("Trebuie sa fie si Start si Final puse ca sa mearga Algorithmul");
        }
        MatrixDraw.red = 0;
        MatrixDraw.green = 0;
        MatrixDraw.black = 0;


    }

}

class LeeAlgorithDraw implements AlgorithAll{

    public void OneDraw(){
        try {
            Start();

            Proiect.LeeOn = true;
            LeeAlgorith Lee = new LeeAlgorith();

            Proiect.leeDraw = new LeeDraw(Lee.ReturnVecini() , 0 , Lee.ReturnQueue() , Lee.ReturnCurNode() , "LEE" , Lee.ReturnDisG() , Lee.ReturnDisH());
            Proiect.matrixDraw.add(Proiect.leeDraw);
            Proiect.leeDraw.setOpaque(false);
            Proiect.leeDraw.setBounds(0, 0, Proiect.width, Proiect.height);

        } catch (Exception c) {
            JOptionPane.showMessageDialog( null, c.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}

class BFSAlgorithmDraw implements AlgorithAll{

    public void OneDraw(){
        try {

            Start();

            Proiect.LeeOn = true;
            BFS Bfs = new BFS();
            Proiect.leeDraw = new LeeDraw(Bfs.ReturnVecini() , 0 , Bfs.ReturnQueue() , Bfs.ReturnCurNode() , "BFS" , Bfs.ReturnDisG() , Bfs.ReturnDisH());
            Proiect.matrixDraw.add(Proiect.leeDraw);
            Proiect.leeDraw.setOpaque(false);
            Proiect.leeDraw.setBounds(0, 0, Proiect.width, Proiect.height);


        } catch (Exception c) {
            JOptionPane.showMessageDialog( null, c.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}

class DFSAlgorithmDraw implements AlgorithAll{

    public void OneDraw(){
        try {

            Start();

            Proiect.LeeOn = true;
            DFS Dfs = new DFS();

            Proiect.leeDraw = new LeeDraw(Dfs.ReturnVecini() , 0 , Dfs.ReturnQueue() ,  Dfs.ReturnCurNode() , "DFS" , Dfs.ReturnDisG() , Dfs.ReturnDisH());
            Proiect.matrixDraw.add(Proiect.leeDraw);
            Proiect.leeDraw.setOpaque(false);
            Proiect.leeDraw.setBounds(0, 0, Proiect.width, Proiect.height);


        } catch (Exception c) {
            JOptionPane.showMessageDialog( null, c.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}

class AStarsAlgorithmDraw implements AlgorithAll{

    public void OneDraw(){
        try {
            Start();

            Proiect.LeeOn = true;
            AStarsAlgorithm AStar = new AStarsAlgorithm();

            Proiect.leeDraw = new LeeDraw(AStar.ReturnVecini() , 0 , AStar.ReturnQueue() , AStar.ReturnCurNode() , "AStar" , AStar.ReturnDisG() , AStar.ReturnDisH());
            Proiect.matrixDraw.add(Proiect.leeDraw);
            Proiect.leeDraw.setOpaque(false);
            Proiect.leeDraw.setBounds(0, 0, Proiect.width, Proiect.height);

        } catch (Exception c) {
            JOptionPane.showMessageDialog( null, c.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
