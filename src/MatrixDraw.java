import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;


public class MatrixDraw extends JPanel{

    static MouseAdapter mouseHandler;
    static MouseMotionAdapter mouseMotionHandler;
    static Point selectedCell;
    static int xOffset;
    static int yOffset;
    static List<Rectangle> cells1;
    static int red, green, black;
    static List<String> Colors;
    static int RED, GREEN;
    static int drawing;
    static int stanga, dreapta;
    static Point Start;
    static Point Final;
    static Graphics2D g2d;
    static int mouseX , mouseY;
    static int width , height , cellWidth , cellHeight;
    static Point selectedCell2;
    static int ok = 0;
    static List<Integer> ListNoWhite;
    static int index1 = -1, index2 = -1;
    static int mouse = -1;
    static boolean verify;
    static Point copy;

    public MatrixDraw() {
        selectedCell = null;
        selectedCell2 = null;
        Start = new Point(-1, -1);
        Final = new Point(-1, -1);
        mouse = -1;
        index1 = -1;
        index2 = -1;
        ok = 0;
        RED = -1;
        GREEN = -1;
        red = 0;
        green = 0;
        black = 0;
        drawing = 0;
        stanga = 0;
        dreapta = 0;
        verify = false;
        copy = new Point();

        Colors = new ArrayList<>(Proiect.number1 * Proiect.number1);
        cells1 = new ArrayList<>(Proiect.number1 * Proiect.number1);
        mouseMotionHandler = new CellMouseMotionListener();
        mouseHandler = new CellMouseListener();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseMotionHandler);


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g2d = (Graphics2D) g;


        width = getWidth();
        height = getHeight();
        cellWidth = width / Proiect.number1;
        cellHeight = height / Proiect.number1;

        xOffset = (width - (Proiect.number1 * cellWidth)) / 2;
        yOffset = (height - (Proiect.number1 * cellHeight)) / 2;

        if (cells1.isEmpty()) {
            for (int i = 0; i < Proiect.number1; i++) {
                for (int j = 0; j < Proiect.number1; j++) {
                    Rectangle reg = new Rectangle(xOffset + (j * (cellWidth)), yOffset + (i * (cellHeight)), cellWidth , cellHeight);
                    cells1.add(reg);
                }
            }
        }

        if (selectedCell != null) {
            SelectedCell();
        }

        DisplayMatrix();
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        ListNoWhite = new ArrayList<>();
        for (int i = 0; i < Proiect.number1 * Proiect.number1; i++){
            if (!Colors.get(i).equals("White") && !Colors.get(i).equals("Black")) {
                ListNoWhite.add(i);
            }
            else {
                g2d.draw(cells1.get(i));
            }
        }

        g2d.setColor(new Color(255, 207, 157));
        for(int i = 0 ; i < ListNoWhite.size() ; i++)
            g2d.draw(cells1.get(ListNoWhite.get(i)));

    }

    static void DisplayMatrix() {

        for (int i = 0; i < Proiect.number1 * Proiect.number1; i++) {
            if (Colors.get(i).equals("Red")) {
                Rectangle cell1 = cells1.get(i);
                g2d.setColor(Color.RED);
                g2d.fill(cell1);
            } else if (Colors.get(i).equals("Green")) {
                Rectangle cell1 = cells1.get(i);
                g2d.setColor(Color.GREEN);
                g2d.fill(cell1);
            } else if (Colors.get(i).equals("Black") && !Proiect.clear) {
                Rectangle cell1 = cells1.get(i);
                g2d.setColor(new Color(92, 60, 11, 255));
                g2d.fill(cell1);
            } else if (Colors.get(i).equals("Black") && Proiect.clear) {
                Rectangle cell1 = cells1.get(i);
                g2d.setColor(new Color(196, 132, 29, 255));
                g2d.fill(cell1);
                Colors.set(i, "White");
            } else if (Colors.get(i).equals("White")) {
                Rectangle cell1 = cells1.get(i);
                g2d.setColor(new Color(196, 132, 29, 255));
                g2d.fill(cell1);
            }

        }

        if(mouse != -1) {
            if (Colors.get(mouse).equals("White")) {
                Rectangle cell1 = cells1.get(mouse);
                g2d.setColor(new Color(251, 178, 61, 255));
                g2d.fill(cell1);
            }
        }

    }

    private void SelectedCell() {

        int index = selectedCell.x + (selectedCell.y * Proiect.number1);

        if (red == 1 && Colors.get(index).equals("White")) {
            if(RED != -1) {
                Colors.set(RED, "White");
            }
            if (Colors.get(index).equals("White")) {
                Colors.set(index, "Red");
            }
            RED = index;
            Start.x = selectedCell.y;
            Start.y = selectedCell.x;
        }

        if (green == 1 && Colors.get(index).equals("White")) {
            if(GREEN != -1)
                Colors.set(GREEN, "White");
            if (Colors.get(index).equals("White")) {
                Colors.set(index, "Green");
            }
            GREEN = index;
            Final.x = selectedCell.y;
            Final.y = selectedCell.x;
        }

        if (black == 1 && Colors.get(index).equals("White") && stanga == 1) {
            Colors.set(index, "Black");
        } else if (black == 1 && Colors.get(index).equals("Black") && dreapta == 1) {
            Colors.set(index, "White");
        }

    }


    private class CellMouseListener extends MouseAdapter {

            public void mousePressed(MouseEvent e) {

                drawing = 1;

                if(red == 1 || green == 1 || black == 1)
                    if (Proiect.matrixDraw.isAncestorOf(Proiect.leeDraw))
                        Proiect.matrixDraw.remove(Proiect.leeDraw);

                if (!Proiect.matrixDraw.isAncestorOf(Proiect.leeDraw)) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        stanga = 1;
                        dreapta = 0;
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        stanga = 0;
                        dreapta = 1;
                    }

                    width = getWidth();
                    height = getHeight();
                    cellWidth = width / Proiect.number1;
                    cellHeight = height / Proiect.number1;


                    selectedCell = null;

                    if (e.getX() >= xOffset && e.getY() >= yOffset) {

                        int column = (e.getX() - xOffset) / cellWidth;
                        int row = (e.getY() - yOffset) / cellHeight;

                        if (column >= 0 && row >= 0 && column < Proiect.number1 && row < Proiect.number1) {

                            selectedCell = new Point(column, row);

                        }

                        repaint();
                    }

                }

            }

            public void mouseReleased(MouseEvent e) {

                if(Proiect.resetLee == 1)
                {
                    drawing = 0;
                }
                else
                {
                    drawing = 0;
                    red = 0;
                    green = 0;
                }
            }

    };

    private class CellMouseMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {

            if (drawing == 1) {

                if(selectedCell != null)
                    if(MatrixDraw.Colors.get(selectedCell.x + (selectedCell.y * Proiect.number1)).equals("Red") ||
                            MatrixDraw.Colors.get(selectedCell.x + (selectedCell.y * Proiect.number1)).equals("Green")) {
                        copy = selectedCell;
                    }
                selectedCell = null;

                width = getWidth();
                height = getHeight();
                cellWidth = width / Proiect.number1;
                cellHeight = height / Proiect.number1;

                if (e.getX() >= xOffset && e.getY() >= yOffset) {

                    int column = (e.getX() - xOffset) / cellWidth;
                    int row = (e.getY() - yOffset) / cellHeight;

                    if (column >= 0 && row >= 0 && column < Proiect.number1 && row < Proiect.number1) {

                        selectedCell = new Point(column, row);

                        mouse = selectedCell.x + (selectedCell.y * Proiect.number1);
                        if (Colors.get(selectedCell.x + (selectedCell.y * Proiect.number1)).equals("Red") && !Proiect.LeeOn && black == 0 && green == 0 && Proiect.resetLee == 0) {
                            red = 1;
                        }
                        else if (Colors.get(selectedCell.x + (selectedCell.y * Proiect.number1)).equals("Green") && !Proiect.LeeOn && black == 0 & red == 0 && Proiect.resetLee == 0)
                            green = 1;

                        if (Colors.get(column + (row * Proiect.number1)).equals("White") && Proiect.resetLee == 0) {
                            if (red == 1) {
                                green = 0;
                                Start.x = selectedCell.y;
                                Start.y = selectedCell.x;
                                moveDrumWithDraggedStartOrFinal("Red");
                            } else if (green == 1) {
                                red = 0;
                                Final.x = selectedCell.y;
                                Final.y = selectedCell.x;
                                moveDrumWithDraggedStartOrFinal("Green");
                            }
                        }

                    }

                    repaint();
                }


            }
        }

        public void mouseMoved(MouseEvent e) {

            if ( e.getX() <= xOffset || e.getY() <= yOffset || e.getX() >= (getWidth() - xOffset-1) || e.getY() >= (getHeight() - yOffset -1)){

                mouse = -1;
                index1 = -1;
                index2 = -1;
            }
            else if (e.getX() >= xOffset && e.getY() >= yOffset) {

                int column = (e.getX() - xOffset) / cellWidth;
                int row = (e.getY() - yOffset) / cellHeight;

                if (column >= 0 && row >= 0 && column < Proiect.number1 && row < Proiect.number1) {

                    index2 = index1;
                    index1 = column + (row * Proiect.number1);
                    if (index2 != index1 && index2 != -1) {
                        mouse = index1;
                    } else if (index1 != index2)
                        mouse = index1;
                }

            }

            repaint();

        }


    }

    public void moveDrumWithDraggedStartOrFinal(String color){
        black = 0;
        if (color.equals("Red")) {
            red = 1;
            green = 0;
            Start.x = selectedCell.y;
            Start.y = selectedCell.x;
        } else if (color.equals("Green")) {
            red = 0;
            green = 1;
            Final.x = selectedCell.y;
            Final.y = selectedCell.x;
        }

        if(Proiect.matrixDraw.isAncestorOf(Proiect.leeDraw))
            Proiect.matrixDraw.remove(Proiect.leeDraw);

        if(Proiect.selectedOption.equals("BFS") ) {
            BFS Bfs = new BFS();
            BFS.BFSAlgorithAll();
            Proiect.leeDraw = new LeeDraw(Bfs.ReturnVecini(), 1 , Bfs.ReturnQueue() , Bfs.ReturnCurNode() , "BFS" , Bfs.ReturnDisG() , Bfs.ReturnDisH());

        }
        else if (Proiect.selectedOption.equals("LEE")){
            LeeAlgorith Lee = new LeeAlgorith();
            LeeAlgorith.LeeAlgorithAll();
            Proiect.leeDraw = new LeeDraw(Lee.ReturnVecini(), 1 , Lee.ReturnQueue() , Lee.ReturnCurNode() , "LEE" , Lee.ReturnDisG() , Lee.ReturnDisH());
        }
        else if (Proiect.selectedOption.equals("DFS")){
            DFS Dfs = new DFS();
            DFS.DFSAlgorithAll();
            Proiect.leeDraw = new LeeDraw(Dfs.ReturnVecini(), 1 , Dfs.ReturnQueue() , Dfs.ReturnCurNode() , "DFS" , Dfs.ReturnDisG() , Dfs.ReturnDisH());
        }
        else if (Proiect.selectedOption.equals("AStar")){
            AStarsAlgorithm AStar = new AStarsAlgorithm();
            AStarsAlgorithm.AStarsAll();
            Proiect.leeDraw = new LeeDraw(AStar.ReturnVecini(), 1 , AStar.ReturnQueue() , AStar.ReturnCurNode() , "DFS" , AStar.ReturnDisG() , AStar.ReturnDisH());
        }

        Proiect.matrixDraw.add(Proiect.leeDraw);

        Proiect.leeDraw.setOpaque(false);
        Proiect.leeDraw.setBounds(0, 0, Proiect.width, Proiect.height);
        Proiect.LeeOn = false;
    }



}