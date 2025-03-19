import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LeeDraw extends JPanel implements ActionListener{

    static int xOffset , yOffset;
    static Map<Point, Point>  visited;
    static List<Integer>  drum;
    static int nr;
    static List<List<Integer>> adj;
    static List<Integer> list;
    private Timer timer;
    private Rectangle cell;
    private Graphics2D g2d;
    private Point fin , start;
    private Queue<Point> queue;
    private Point curNode;
    private String Algorithm;
    static boolean verify;
    private int[][] dis , dis2;


    public LeeDraw(Map<Point, Point> vecini1 , int Timer , Queue<Point> queue1, Point curNode1 , String Algorithm1 , int[][] dis1 , int[][] dis21) {

        visited = vecini1;
        fin = new Point(MatrixDraw.Final.x , MatrixDraw.Final.y);
        start = new Point(MatrixDraw.Start.x , MatrixDraw.Start.y);
        queue = queue1;
        Algorithm = Algorithm1;
        curNode = curNode1;
        verify = false;
        dis = dis1;
        dis2 = dis21;

        if(Timer == 0){
            timer = new Timer(100, this);
            timer.start();
        }


    }

    @Override
    public void actionPerformed(ActionEvent e){

            if ("BFS".equals(Algorithm))
                curNode = BFS.BFSAlgorith();
            if ("LEE".equals(Algorithm))
                curNode = LeeAlgorith.LeeAlgorith1();
            if ("DFS".equals(Algorithm))
                curNode = DFS.DFSAlgorith();
            if ("AStar".equals(Algorithm)) {
                curNode = AStarsAlgorithm.AStars();
            }
            if ((curNode.x == fin.x && curNode.y == fin.y) || verify) {
                Proiect.LeeOn = false;
                Proiect.button6.setEnabled(true);
                timer.stop();
            }

            repaint();

    }

    private void drawVisited(Graphics g) {

        for (Point p : visited.keySet()) {
            int index = p.y + (p.x * Proiect.number1);
            cell = MatrixDraw.cells1.get(index);
            g2d.setColor(new Color(144, 118, 64));
            g2d.fill(cell);
            g2d.setColor(Color.BLACK);
            g2d.draw(cell);

            if(dis[p.x][p.y] != 0 && dis2[p.x][p.y] != 0) {
                g.setFont(new Font("Arial", Font.BOLD, MatrixDraw.cellWidth / 5));
                String text = String.valueOf(dis[p.x][p.y]);
                FontMetrics fm = g.getFontMetrics();
                int textSize = fm.stringWidth("1");
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                g.drawString(text, cell.x + textSize, cell.y + textHeight);

                text = String.valueOf(dis2[p.x][p.y]);
                textWidth = fm.stringWidth(text);
                textHeight = fm.getHeight();

                g.drawString(text, cell.x + (MatrixDraw.cellWidth - (textWidth + textSize)), cell.y + (MatrixDraw.cellHeight - (textHeight - textSize)));

                text = String.valueOf(dis2[p.x][p.y] + dis[p.x][p.y]);
                textWidth = fm.stringWidth(text);
                textHeight = fm.getHeight();
                g.drawString(text, cell.x + (MatrixDraw.cellWidth / 2 - textWidth + (textWidth / 2)), cell.y + (MatrixDraw.cellHeight + (textHeight - textSize)) / 2);
            }
        }
    }

    private void drawQueue(Graphics g) {
        for (Point p : queue) {
            int index = p.y + (p.x * Proiect.number1);
            cell = MatrixDraw.cells1.get(index);
            g2d.setColor(new Color(244, 204, 110));
            g2d.fill(cell);
            g2d.setColor(new Color(174, 172, 172));
            g2d.draw(cell);

            if(dis[p.x][p.y] != 0 && dis2[p.x][p.y] != 0) {
                g.setFont(new Font("Arial", Font.BOLD, MatrixDraw.cellWidth / 5));
                String text = String.valueOf(dis[p.x][p.y]);
                FontMetrics fm = g.getFontMetrics();
                int textSize = fm.stringWidth("1");
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                g.drawString(text, cell.x + textSize, cell.y + textHeight);

                text = String.valueOf(dis2[p.x][p.y]);
                textWidth = fm.stringWidth(text);
                textHeight = fm.getHeight();

                g.drawString(text, cell.x + (MatrixDraw.cellWidth - (textWidth + textSize)), cell.y + (MatrixDraw.cellHeight - (textHeight - textSize)));

                text = String.valueOf(dis2[p.x][p.y] + dis[p.x][p.y]);
                textWidth = fm.stringWidth(text);
                textHeight = fm.getHeight();
                g.drawString(text, cell.x + (MatrixDraw.cellWidth / 2 - textWidth + (textWidth / 2)), cell.y + (MatrixDraw.cellHeight + (textHeight - textSize)) / 2);
            }
        }
    }

    private void drawPath(Graphics g) {

        Point pathSegment = curNode;
        g.setColor(Color.WHITE);
        while (pathSegment != null) {
            int index = pathSegment.y+ (pathSegment.x * Proiect.number1);
            cell = MatrixDraw.cells1.get(index);
            g2d.setColor(new Color(170, 51, 106));
            g2d.fillOval(cell.x + MatrixDraw.cellWidth / 4 , cell.y + MatrixDraw.cellHeight / 4 , MatrixDraw.cellWidth / 2, MatrixDraw.cellHeight / 2);
            g2d.setColor(new Color(255, 255, 255));
            g2d.drawOval(cell.x + MatrixDraw.cellWidth / 4 , cell.y + MatrixDraw.cellHeight / 4 , MatrixDraw.cellWidth / 2, MatrixDraw.cellHeight / 2);

            if(dis[pathSegment.x][pathSegment.y] != 0 && dis2[pathSegment.x][pathSegment.y] != 0) {
                g.setFont(new Font("Arial", Font.BOLD, MatrixDraw.cellWidth / 5));
                FontMetrics fm = g.getFontMetrics();
                int textSize = fm.stringWidth("1");
                String text = String.valueOf(dis2[pathSegment.x][pathSegment.y] + dis[pathSegment.x][pathSegment.y]);
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                g.drawString(text, cell.x + (MatrixDraw.cellWidth / 2 - textWidth + (textWidth / 2)), cell.y + (MatrixDraw.cellHeight + (textHeight - textSize)) / 2);
            }

            pathSegment = visited.get(pathSegment);
        }



    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g2d = (Graphics2D) g;
        drawVisited(g2d);
        drawQueue(g2d);
        drawPath(g2d);

        int index = start.y + (start.x * Proiect.number1);
        cell = MatrixDraw.cells1.get(index);
        g2d.setColor(Color.RED);
        g2d.fill(cell);
        g2d.setColor(Color.WHITE);
        g2d.draw(cell);

        index = fin.y + (fin.x * Proiect.number1);
        cell = MatrixDraw.cells1.get(index);
        g2d.setColor(Color.GREEN);
        g2d.fill(cell);
        g2d.setColor(Color.WHITE);
        g2d.draw(cell);
    }

}
