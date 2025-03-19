import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Proiect extends JFrame{

    JButton button1 , button2 , button3 , button4 , button5 , menu;
    JLabel label1;
    static int number1;
    JTextField textField1;
    static MatrixDraw matrixDraw;
    static JPanel thePanel;
    static boolean clear = false;
    static int Lee;
    static int nr , nr1;
    static MouseListenerForButton mouseListenerForButton;
    static KeyBoardListenerForButton keyBoardListenerForButton;
    static ListenForButton listenForButton;
    static LeeDraw leeDraw;
    static Timer timer;
    static boolean LeeOn, Maze;
    static BinaryTreeMaze maze;
    static DrawMaze drawMaze;
    static JComboBox<String> button7 , button6;
    static int height , width;
    static JSlider slider;
    static MouseWheelListener1 mouseWheelListener;
    static int resetLee;
    static String selectedOption;



    public static void main(String[] args) {

        new Proiect();
        LeeOn = false ; Maze = false;

    }

    private void ComboBox(JComboBox comboBox , String name){
        comboBox.setBounds(50, 50, 200, 30);

        // Setarea unui index implicit care să nu fie valabil
        comboBox.setSelectedIndex(-1);

        // Renderer personalizat pentru a afișa placeholder-ul când nu e selectată nicio opțiune
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if("Maze".equals(name))
                    if (index == -1 && value == null) {
                        setText("Maze Builder");
                    } else {
                        setText(value.toString());
                    }
                else if("Algorithm".equals(name))
                {
                    if (index == -1 && value == null) {
                        setText("Algorithms");
                    } else {
                        setText(value.toString());
                    }
                }

                return this;
            }
        });
        comboBox.setEnabled(false);
        comboBox.addActionListener(listenForButton);
    }

    public Proiect() {

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        int height1 = (int)size.getHeight();

        this.setSize(height1 - height1 / 12, height1- height1 / 12);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Project");

        this.setIconImage(new ImageIcon(getClass().getResource("/algorithm.png")).getImage());

        thePanel = new JPanel();

        thePanel.setLayout(new BorderLayout());

        slider = new JSlider(0, 30, 0);
        slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.setPreferredSize(new Dimension(400, 45));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        Font font = new Font("Serif", Font.ITALIC, 10);
        slider.setFont(font);


        button1 = new JButton("Confirmed");
        button2 = new JButton("Start");
        button3 = new JButton("Final");
        button4 = new JButton("Wall");
        button5 = new JButton("Clear");
        Dimension buttonSize = new Dimension(height1/10, height1/25);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);

        Font buttonFont = new Font("Arial", Font.PLAIN, height1/75);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);
        button4.setFont(buttonFont);
        button5.setFont(buttonFont);


        menu = new JButton("Menu");
        menu.setPreferredSize(buttonSize);
        menu.setFont(buttonFont);

        String[] optiuni1 = {"BinaryTree", "BackTracking"};
        button7 = new JComboBox<>(optiuni1);
        button7.setPreferredSize(buttonSize);
        button7.setFont(buttonFont);
        String[] optiuni2 = {"LEE", "DFS" , "BFS" , "AStar"};
        button6 = new JComboBox<>(optiuni2);
        button6.setPreferredSize(buttonSize);
        button6.setFont(buttonFont);

        listenForButton = new ListenForButton();
        KeyboardListen keyboardListen = new KeyboardListen();
        mouseListenerForButton = new MouseListenerForButton();
        keyBoardListenerForButton = new KeyBoardListenerForButton();
        mouseWheelListener = new MouseWheelListener1();

        slider.addMouseWheelListener(mouseWheelListener);

        button2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "StartButtonPressed");
        button2.getActionMap().put("StartButtonPressed", keyboardListen);

        button4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "WallButtonPressed");
        button4.getActionMap().put("WallButtonPressed", keyboardListen);

        button3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "FinalButtonPressed");
        button3.getActionMap().put("FinalButtonPressed", keyboardListen);

        button1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "CreateButtonPressed");
        button1.getActionMap().put("CreateButtonPressed", keyboardListen);

        button5.addMouseListener(mouseListenerForButton);
        button5.addKeyListener(keyBoardListenerForButton);
        button5.setFocusable(true);

        menu.addActionListener(listenForButton);
        button1.addActionListener(listenForButton);
        button2.addActionListener(listenForButton);
        button3.addActionListener(listenForButton);
        button4.addActionListener(listenForButton);

        ComboBox(button6 , "Algorithm");
        ComboBox(button7 , "Maze");

        label1 = new JLabel("Nr Matrice");
        label1.setFont(buttonFont);

        textField1 = new JTextField("" , 10);
        textField1.setPreferredSize(new Dimension(height1/20 , height1/50));

        JPanel thePanel2 = new JPanel();

        thePanel2.setLayout(new FlowLayout(FlowLayout.CENTER , height1/72 , 10));
        JPanel thePanel3 = new JPanel();

        thePanel3.setLayout(new FlowLayout(FlowLayout.CENTER , 50 , 10));

        thePanel2.add(button1);
        thePanel2.add(label1);
        thePanel2.add(textField1);
        thePanel2.add(button2);
        thePanel2.add(button3);
        thePanel2.add(button4);

        thePanel2.add(button6);
        thePanel2.add(button7);
        thePanel3.add(menu);
        thePanel3.add(slider);
        thePanel3.add(button5);

        button6.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);

        thePanel.add(thePanel2, BorderLayout.NORTH);
        thePanel.add(thePanel3, BorderLayout.SOUTH);


        this.add(thePanel);
        this.setVisible(true);
        this.setResizable(false);
        textField1.requestFocusInWindow();

        width = getWidth();
        height = getHeight();
    }


    private class ListenForButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == button1 && !LeeOn && !Maze)
            {

                try {
                    number1 = Integer.parseInt(textField1.getText());
                    if(thePanel.isAncestorOf(matrixDraw))
                        thePanel.remove(matrixDraw);

                    if(matrixDraw != null) {
                        if (matrixDraw.isAncestorOf(leeDraw))
                            matrixDraw.remove(leeDraw);
                        if(matrixDraw.isAncestorOf(drawMaze))
                            matrixDraw.remove(drawMaze);

                    }

                    LeeOn = false;
                    Maze = false;
                    slider.setMaximum(30);
                    slider.setValue(0);
                    if(number1 % 2 == 1)
                        button7.setEnabled(true);
                    else if(number1 % 2 == 0)
                        button7.setEnabled(false);

                    if(number1 <= 0 || number1 > 100)
                        throw new NumberFormatException();
                    matrixDraw = new MatrixDraw();

                    button6.setEnabled(true);
                    button2.setEnabled(true);
                    button3.setEnabled(true);
                    button4.setEnabled(true);
                    button5.setEnabled(true);

                    button5.setFocusable(true);
                    button5.requestFocusInWindow();
                    button6.requestFocusInWindow();
                    button6.setFocusable(true);

                    for(int i=0; i<number1; i++){
                        for(int j=0; j<number1; j++){
                            MatrixDraw.Colors.add("White");
                        }
                    }

                    textField1.setText("");

                    matrixDraw.setLayout(null);
                    thePanel.add(matrixDraw);
                    thePanel.revalidate();
                    thePanel.repaint();


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Proiect.this, "Please enter a number", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if(e.getSource() == button2){
                if(!LeeOn && !Maze) {
                    MatrixDraw.selectedCell = null;
                    resetLee = 1;
                    MatrixDraw.red = 1;
                    MatrixDraw.green = 0;
                    MatrixDraw.black = 0;
                    textField1.setText("");
                }
            }

            if(e.getSource() == button3){
                if(!Maze && !LeeOn) {
                    MatrixDraw.selectedCell = null;
                    resetLee = 1;
                    MatrixDraw.red = 0;
                    MatrixDraw.green = 1;
                    MatrixDraw.black = 0;
                    textField1.setText("");
                }
            }

            if(e.getSource() == button4){
                if(!LeeOn && !Maze) {
                    MatrixDraw.selectedCell = null;
                    MatrixDraw.red = 0;
                    MatrixDraw.green = 0;
                    MatrixDraw.black = 1;
                    textField1.setText("");
                }
            }

            if(e.getSource() == button7){
                if(!LeeOn && !Maze) {
                    if (matrixDraw.isAncestorOf(leeDraw))
                        Proiect.matrixDraw.remove(Proiect.leeDraw);
                    String selectedOption = (String) button7.getSelectedItem();

                    slider.setMaximum(30);
                    slider.setValue(0);
                    if (selectedOption.equals("BinaryTree")) {

                        MazeAll binary = new BinaryTreeMazeDraw();
                        binary.OneMaze();

                    } else if (selectedOption.equals("BackTracking")) {
                        MazeAll binary = new BackTrackingMazeDraw();
                        binary.OneMaze();
                    }
                    //else if(selectedOption.equals("AldousBroder")) {
                    //    MazeAll binary = new AldousBroderMazeGeneratorDraw();
                    //    binary.OneMaze();
                    //}
                }
            }
            else if(e.getSource() == button6) {

                if (!LeeOn && !Maze && button6.isEnabled()) {
                    resetLee = 0;
                    if (matrixDraw.isAncestorOf(leeDraw)) {
                        matrixDraw.remove(leeDraw);
                    }
                    button6.setEnabled(false);
                    slider.setMaximum(60);
                    slider.setValue(0);
                    selectedOption = (String) button6.getSelectedItem();
                    if (selectedOption.equals("BFS")) {
                        AlgorithAll Bfs = new BFSAlgorithmDraw();
                        Bfs.OneDraw();
                    } else if (selectedOption.equals("LEE")) {

                        AlgorithAll Lee = new LeeAlgorithDraw();
                        Lee.OneDraw();
                    } else if (selectedOption.equals("DFS")) {
                        AlgorithAll Dfs = new DFSAlgorithmDraw();
                        Dfs.OneDraw();

                    }
                    else if(selectedOption.equals("AStar"))
                    {
                        AlgorithAll ast = new AStarsAlgorithmDraw();
                        ast.OneDraw();
                    }
                }

            }
            if(e.getSource() == menu)
            {
                JOptionPane.showMessageDialog(Proiect.this, "<html> Press s pentru Start <br> Press f pentru Final <br> Press w pentru Wall </html>", "Menu", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class KeyboardListen extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button2) {
                button2.doClick();
            }
            if(e.getSource() == button3) {
                button3.doClick();
            }
            if(e.getSource() == button4) {
                button4.doClick();
            }
            if(e.getSource() == button1) {
                button1.doClick();
            }
        }
    }

    private static class MouseWheelListener1 implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent e) {
            if(e.getSource() == slider) {
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    slider.setValue(slider.getValue() + 1);
                } else {
                    slider.setValue(slider.getValue() - 1);
                }
            }
        }
    }

    private class MouseListenerForButton extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if(e.getSource() == button5) {
                if(!LeeOn && !Maze) {
                    if(matrixDraw.isAncestorOf(leeDraw))
                        matrixDraw.remove(leeDraw);
                    MatrixDraw.red = 0;
                    MatrixDraw.green = 0;
                    MatrixDraw.black = 0;
                    MatrixDraw.selectedCell = null;
                    clear = true;
                    thePanel.revalidate();
                    thePanel.repaint();
                }
            }
        }
        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == button5) {
                clear = false;
                MatrixDraw.black = 1;
            }
        }
    }

    private class KeyBoardListenerForButton extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_C && !LeeOn)
            {
                if(matrixDraw.isAncestorOf(leeDraw))
                    matrixDraw.remove(leeDraw);
                MatrixDraw.red = 0;
                MatrixDraw.green = 0;
                MatrixDraw.black = 0;
                MatrixDraw.selectedCell = null;
                clear = true;
                thePanel.revalidate();
                thePanel.repaint();
                button5.doClick();
            }
            else if(e.getKeyCode() == KeyEvent.VK_L)
            {
                new LeeAlgorithDraw();
                //LeeDraw leeDraw = new LeeDraw();
                leeDraw.setOpaque(false);
                leeDraw.setBounds(0,0,getWidth(),getHeight());
                matrixDraw.add(leeDraw);
                //button6.doClick();
            }
        }
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_C)
            {
                clear = false;
                MatrixDraw.black = 1;
            }
            else if(e.getKeyCode() == KeyEvent.VK_L)
            {
                Lee = 1;
            }
        }

    }


}
