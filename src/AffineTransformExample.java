import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class AffineTransformExample extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Desenează un dreptunghi pentru a vedea cum se aplică transformarea
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, 100, 50);

        // Creează un obiect AffineTransform
        AffineTransform transform = new AffineTransform();

        // Obține dimensiunile ecranului și ale JPanel-ului
        Dimension panelSize = getSize();
        int panelWidth = panelSize.width;
        int panelHeight = panelSize.height;

        int rectWidth = 100;
        int rectHeight = 50;

        // Calculează poziția pentru a centra dreptunghiul
        double translateX = (panelWidth - rectWidth) / 2.0;
        double translateY = (panelHeight - rectHeight) / 2.0;

        // Aplică translația
        transform.translate(translateX, translateY);

        // Aplică transformarea
        g2d.setTransform(transform);

        // Desenează dreptunghiul transformat
        g2d.fillRect(0, 0, rectWidth, rectHeight);
    }

    public static void main(String[] args) {
        // Creează fereastra principală
        JFrame frame = new JFrame("AffineTransform Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Adaugă panoul cu transformare afine
        AffineTransformExample panel = new AffineTransformExample();
        frame.add(panel);

        // Afișează fereastra
        frame.setVisible(true);
    }
}