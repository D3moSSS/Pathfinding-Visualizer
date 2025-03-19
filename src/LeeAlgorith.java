import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class LeeAlgorith extends JFrame {

    static Queue<Point> Q ;
    static Map<Point, List<Point>> graph;
    static Map<Point, Point> visited;

    static int[][] a;
    static int n , m;
    static Point Start , Final;
    static Varf[][] mat;
    static Point nod;
    static List<Integer> drum;
    static int nr1;
    static List<Integer> vecini;
    static int ok;
    static int AlgorithmDrum;
    static int[] di , dj;
    static int[][] disG ,disH;

    public static class Varf{
        int color , d;
        Point p;

        public Varf(int color, int d, Point p) {
            this.color = color;
            this.d = d;
            this.p = p;
        }
    }

    public LeeAlgorith() {

        graph = new HashMap<>();
        visited = new HashMap<>();

        di = new int[]{0, 0, 1, -1};
        dj = new int[]{-1, 1, 0, 0};

        a = new int[1000][1000];
        mat = new Varf[1000][1000];
        drum = new ArrayList<>();
        Q = new LinkedList<>();

        ok = 0;
        vecini = new ArrayList<>();
        nr1 = 0;
        Start = new Point(MatrixDraw.Start.x, MatrixDraw.Start.y);
        Final = new Point(MatrixDraw.Final.x, MatrixDraw.Final.y);
        n = Proiect.number1;
        m = Proiect.number1;
        disG = new int[n][n];
        disH = new int[n][n];
        int nr = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (MatrixDraw.Colors.get(nr).equals("Black")) {
                    a[i][j] = 1;
                } else if (MatrixDraw.Colors.get(nr).equals("White"))
                    a[i][j] = 0;
                else if (MatrixDraw.Colors.get(nr).equals("Red")) {
                    a[i][j] = 2;
                } else if (MatrixDraw.Colors.get(nr).equals("Green")) {
                    a[i][j] = 3;
                }
                nr++;
            }


        for (int i = 0; i < Proiect.number1; i++) {
            for (int j = 0; j < Proiect.number1; j++) {
                mat[i][j] = new Varf(0, 0, new Point(0, 0));
            }
        }


        for (int i = 0; i < Proiect.number1; i++)
            for (int j = 0; j < Proiect.number1; j++) {
                if (a[i][j] == 1) {
                    mat[i][j].color = 2;
                    mat[i][j].d = 10000;
                    mat[i][j].p.x = -1;
                    mat[i][j].p.y = -1;
                } else {
                    mat[i][j].color = 0;
                    mat[i][j].d = 10000;
                    mat[i][j].p.x = -1;
                    mat[i][j].p.y = -1;
                }
            }

        mat[Start.x][Start.y].color = 1;
        mat[Start.x][Start.y].d = 0;
        nod = Start;
        Q.add(Start);
        visited.put(Start, null);
    }

    public static Point LeeAlgorith1(){

        int nr = 0;
        while(!Q.isEmpty()) {
            nod = Q.poll();
            if (nod.x == Final.x && nod.y == Final.y) {
                break;
            }
            for(int k = 0; k < 4; k++) {
                Point vecin = new Point(0,0);
                vecin.x = nod.x + di[k];
                vecin.y = nod.y + dj[k];
                if(vecin.x >= 0 && vecin.x < n && vecin.y >= 0 && vecin.y < m && mat[vecin.x][vecin.y].color == 0) {
                    mat[vecin.x][vecin.y].color = 1;
                    mat[vecin.x][vecin.y].d = mat[nod.x][nod.y].d + 1;
                    mat[vecin.x][vecin.y].p = nod;

                    visited.put(vecin, nod);
                    Q.add(vecin);
                }

            }
            mat[nod.x][nod.y].color = 1;
            if(nr == Proiect.slider.getValue())
                break;
            else
                nr++;
        }

        if(Q.isEmpty() && nod.x != Final.x && nod.y != Final.y)
        {
            isEmpty();
        }

        return nod;

    }

    public Point ReturnCurNode(){
        return nod;
    }

    public Map<Point, Point> ReturnVecini(){
        return visited;
    }

    public Queue<Point> ReturnQueue(){
        return Q;
    }

    public int[][] ReturnDisG(){
        return disG;
    }

    public int[][] ReturnDisH(){
        return disH;
    }

    public static void LeeAlgorithAll() {

        while(!Q.isEmpty()) {
            nod = Q.poll();
            if (nod.x == Final.x && nod.y == Final.y) {
                break;
            }
            for(int k = 0; k < 4; k++) {
                Point vecin = new Point(0,0);
                vecin.x = nod.x + di[k];
                vecin.y = nod.y + dj[k];
                if(vecin.x >= 0 && vecin.x < n && vecin.y >= 0 && vecin.y < m && mat[vecin.x][vecin.y].color == 0) {
                    mat[vecin.x][vecin.y].color = 1;
                    mat[vecin.x][vecin.y].d = mat[nod.x][nod.y].d + 1;
                    mat[vecin.x][vecin.y].p = nod;

                    visited.put(vecin, nod);
                    Q.add(vecin);
                }

            }
            mat[nod.x][nod.y].color = 1;
        }

        if(Q.isEmpty() && nod.x != Final.x && nod.y != Final.y)
        {
            isEmpty();
        }

    }

    public static void isEmpty(){
        LeeDraw.verify = true;
        Proiect.resetLee = 1;
        if (Proiect.matrixDraw.isAncestorOf(Proiect.leeDraw))
            Proiect.matrixDraw.remove(Proiect.leeDraw);
        JOptionPane.showMessageDialog( null, "Nu s o gasit drum pentru LEE ! ", "Error", JOptionPane.INFORMATION_MESSAGE);

    }

}
