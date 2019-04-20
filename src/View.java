import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


/** 
 * View class contain all Java Swing component to create User Interface
 */
public class View {
    private Model model;
    private int numberOfStone = 3;
    private Design design;
    private JFrame frame;
    private JLabel curentPlayer;
    private Container container;
    private JPanel pitsBoard;
    private JLabel[] pitss = new JLabel[12];
    private JLabel Mancala_1, Mancala_2;
    private JButton undoButton;

    /**
     * Constructor for the View class
     * View also serve as controller
     * @param Model model
     */
    public View(Model model) {
        this.model = model;
        container = new Container();
        container.setLayout(new BorderLayout(10, 10));

        pitsBoard = new JPanel();
        pitsBoard.setLayout(new GridLayout(2, 6, 8, 8));

        frame = new JFrame("Mancala");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        design = new DesignOne();

        setUp(frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Setup function call the Wellcome frame and set up the game styles and pit value.
     * @param JFrame fram
     */
    public void setUp(JFrame fram) {

        JDialog dialog = new JDialog(fram, true);

        dialog.setTitle("SetUp for Mancala Game");
        dialog.setSize(360, 320);

        JLabel stoneOption = new JLabel("Choose How Many Stones\n: ");
        JRadioButton chooseThree = new JRadioButton("Three", true);
        JRadioButton chooseFour = new JRadioButton("Four");
        ButtonGroup Group_stone_button = new ButtonGroup();
        Group_stone_button.add(chooseThree);
        Group_stone_button.add(chooseFour);
        JPanel panel1 = new JPanel();

        panel1.add(stoneOption);
        panel1.add(chooseThree);
        panel1.add(chooseFour);

        // Get the choice for style
        JLabel designOption = new JLabel("Choose Design: ");
        JRadioButton chooseDesign_1 = new JRadioButton("Design 1", true);
        JRadioButton chooseDesign_2 = new JRadioButton("Design 2");
        ButtonGroup Group_design_button = new ButtonGroup();
        Group_design_button.add(chooseDesign_1);
        Group_design_button.add(chooseDesign_2);
        JPanel panel2 = new JPanel();

        panel2.add(designOption);
        panel2.add(chooseDesign_1);
        panel2.add(chooseDesign_2);

        // Image for the welcome page
        BufferedImage image = null;
        try {
            image = new BufferedImage(334, 150, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(new File("mancala.jpeg"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        JLabel topImage = new JLabel(new ImageIcon(image));
        JPanel panel0 = new JPanel();
        panel0.add(topImage);

        Container container1 = dialog.getContentPane();
        JButton begin = new JButton("Begin");
        begin.setForeground(Color.RED);

        // Controller add action for the begin button
        begin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                container1.setVisible(false);
                model.setStoneNumber(numberOfStone);
                board();
                dialog.dispose();

            }
        });

        // Controller add action for stone number 
        chooseThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfStone = 3;
            }
        });

        // Controller add action for stone number 
        chooseFour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfStone = 4;
            }
        });

        // Controller add action for design
        chooseDesign_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                design = new DesignOne();
            }
        });
        
        // Controller add action for design
        chooseDesign_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                design = new DesignTwo();
            }
        });

        container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
        container1.add(panel0);
        container1.add(panel1);
        container1.add(panel2);
        container1.add(begin);
        dialog.setVisible(true);
        dialog.setResizable(false);

    }

    /**
     * The main panel for game after chosen all the settings
     */
    public void board() {
        model.setStoneNumber(numberOfStone);
        if (model.getPlayer() == 1)
            curentPlayer = new JLabel(" Player A turn");
        if (model.getPlayer() == 2)
            curentPlayer = new JLabel(" Player B turn");
        container.add(curentPlayer, BorderLayout.NORTH);

        Pits mancala1 = new Pits(design, 6, model.getStoneInPits(6));
        Mancala_1 = new JLabel(mancala1);

        JPanel pa = new JPanel();
        pa.setLayout(new BorderLayout(5, 5));
        JTextArea la = new JTextArea();
        la.setFont(new Font("Serif", Font.PLAIN, 18));
        la.setEditable(false);
        la.setBackground(new Color(238, 238, 238));

        la.setText("\nM\nA\nN\nC\nA\nL\nA\n \nA");

        pa.add(Mancala_1, BorderLayout.WEST);
        pa.add(la, BorderLayout.EAST);

        container.add(pa, BorderLayout.EAST);

        Pits mancala2 = new Pits(design, 13, model.getStoneInPits(13));
        Mancala_2 = new JLabel(mancala2);

        JPanel pa1 = new JPanel();
        pa1.setLayout(new BorderLayout(5, 5));
        //
        JTextArea la1 = new JTextArea();
        la1.setFont(new Font("Serif", Font.PLAIN, 18));
        la1.setEditable(false);
        la1.setBackground(new Color(238, 238, 238));
        la1.setText("\nM\nA\nN\nC\nA\nL\nA\n \nB");

        pa1.add(la1, BorderLayout.WEST);
        pa1.add(Mancala_2, BorderLayout.EAST);

        container.add(pa1, BorderLayout.WEST);

        // Controller add mouse listener for the pits 
        for (int i = 0; i < 12; i++) {
            int s = i;
            Pits pit = new Pits(design, pitOrder(i), model.getStoneInPits(pitOrder(i)));
            JLabel pits = new JLabel(pit);

            pits.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (model.getPlayer() == 1) {
                        if (pitOrder(s) < 6) {
                            model.saveHistory();
                            model.play(pitOrder(s));
                            if (model.isFinish()) {
                                JOptionPane.showMessageDialog(frame, model.getWiner(), "Winner of the Game",
                                        JOptionPane.PLAIN_MESSAGE);
                            }

                        }
                    } else {
                        if (pitOrder(s) > 6) {
                            model.saveHistory();
                            model.play(pitOrder(s));
                            if (model.isFinish())
                                JOptionPane.showMessageDialog(frame, model.getWiner(), "Winner of the Game",
                                        JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            });
            pitss[i] = pits;
            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            if (i < 6) {
                JLabel l = new JLabel("B" + String.valueOf(pitOrder(i) - 6));
                p.add(l);
                p.add(pits);
            } else {
                JLabel l = new JLabel("A" + String.valueOf(pitOrder(i) + 1));
                p.add(pits);
                p.add(l);
            }

            pitsBoard.add(p);

        }

        container.add(pitsBoard, BorderLayout.CENTER);

        undoButton = new JButton("Undo: 3");
        
        // Controller add action for the undo button, get undo count from model
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.undo();
                undoButton.setText("undo: " + String.valueOf(model.getUndoCount()));
                if (model.isFinish())
                    undoButton.removeActionListener(this);
            }
        });
        JPanel u = new JPanel();
        u.add(undoButton);
        container.add(u, BorderLayout.SOUTH);

        frame.add(container);

    }

    /**
     * Function get the correct pit number from array
     */
    public int pitOrder(int i) {
        if (i == 0)
            return 12;
        if (i == 1)
            return 11;
        if (i == 2)
            return 10;
        if (i == 3)
            return 9;
        if (i == 4)
            return 8;
        if (i == 5)
            return 7;
        if (i == 6)
            return 0;
        if (i == 7)
            return 1;
        if (i == 8)
            return 2;
        if (i == 9)
            return 3;
        if (i == 10)
            return 4;
        return 5;

    }

    /**
     * Function to update the pits value in the Mancala given data from the model
     */
    public void mancalaRepaint() {
        Mancala_1.setIcon(new Pits(design, 6, model.getStoneInPits(6)));

        Mancala_2.setIcon(new Pits(design, 13, model.getStoneInPits(13)));
    }

    /**
     * Function to update the pits value given value from the model
     */
    public void labelRePaint(int index) {
        pitss[index].setIcon(new Pits(design, pitOrder(index), model.getStoneInPits(pitOrder(index))));

    }

    /**
     * Function to update the current player label
     */
    public void setPlayerlable() {
        if (model.getPlayer() == 1)
            curentPlayer.setText(" Player A turn");
        if (model.getPlayer() == 2)
            curentPlayer.setText(" Player: B turn");
    }

    /**
     * Function to get the Frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Function to update undo count button label
     */
    public void repaintUndo() {
        undoButton.setText("undo: " + String.valueOf(model.getUndoCount()));
    }

}
