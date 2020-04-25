import com.sun.org.apache.bcel.internal.generic.JsrInstruction;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class CalculatorGUI {

    private JFrame calcFrame;
    private List<JButton> standardButton;
    private List<JButton> scientificButton;
    private String output = "";

    public CalculatorGUI() {

        // initialize JFrame
        calcFrame = new JFrame();
        standardButton = new ArrayList<>();
        scientificButton = new ArrayList<>();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.updateComponentTreeUI(calcFrame);

        calcFrame.setTitle("Calculator");
        calcFrame.setSize(430, 685);
        calcFrame.getContentPane().setBackground(new Color(31,31,31));
        calcFrame.setResizable(false);
        calcFrame.setLocationRelativeTo(null);
        calcFrame.setLayout(null);
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize JTabbedPane

        UIManager.put("TabbedPane.unselectedBackground", Color.red);
        UIManager.put("TabbedPane.selected", Color.black);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setSize(425, 650);
        tabs.setLocation(0, 0);
        calcFrame.add(tabs);

        tabs.add("Standard", standardPanel());
        tabs.add("Scientific", scientificPanel());
        tabs.setBackgroundAt(0, new Color(31, 31, 31));

        calcFrame.setVisible(true);
    }


    /**
     * This method create and complete a standard tab of this
     * calculator. this method is just for GUI and implementation
     * of calculator will done later.
     *
     * @return prepared standard tab
     */
    private JPanel standardPanel() {


        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(5);
        borderLayout.setHgap(5);
        JPanel mainPanel = new JPanel(borderLayout);
        mainPanel.setBackground(new Color(31, 31, 31));
        mainPanel.setLocation(5, 5);
        mainPanel.setSize(410, 605);


        // Text Area

        JTextArea display = new JTextArea();
        display.setEditable(true);
        display.setSize(410, 600);
        display.setLocation(0, 0);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setFont(new Font("Santa Fe LET", Font.PLAIN, 54));
        display.setText(output);
        display.setBackground(new Color(31, 31, 31));
        display.setForeground(Color.white);
        display.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(410, 150));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 31, 31), 2));


        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setBackground(new Color(31 ,31 , 31));

        GridLayout gridLayout = new GridLayout(5,4);

        keyboardPanel.setLayout(gridLayout);
        mainPanel.add(keyboardPanel, BorderLayout.SOUTH);

        // Buttons

        JButton button = new JButton(); // AC button
        button.setText("AC");
        standardButton.add(button);
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // square of number
        button.setText("x\u00B2");
        standardButton.add(button);
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // square root of number
        button.setText("\u221A");
        standardButton.add(button);
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // Division sign
        button.setText("\u00F7");
        standardButton.add(button);
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton1 = button;
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton1.setBackground(new Color(0, 191, 255));
                finalButton1.setForeground(Color.black);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton1.setBackground(new Color(13, 13, 13));
                finalButton1.setForeground(Color.white);

            }
        });
        keyboardPanel.add(button);

        for(int i=0 ; i<12 ; i++) {

            if(i == 3) {

                button = new JButton(); // Multiplication sign
                button.setText("\u00D7");
                standardButton.add(button);
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton2 = button;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton2.setBackground(new Color(0, 191, 255));
                        finalButton2.setForeground(Color.black);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton2.setBackground(new Color(13, 13, 13));
                        finalButton2.setForeground(Color.white);

                    }
                });
                keyboardPanel.add(button);

            } else if (i == 7){

                button = new JButton(); // negative sign
                button.setText("-");
                standardButton.add(button);
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton3 = button;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton3.setBackground(new Color(0, 191, 255));
                        finalButton3.setForeground(Color.black);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton3.setBackground(new Color(13, 13, 13));
                        finalButton3.setForeground(Color.white);

                    }
                });
                keyboardPanel.add(button);

            } else if (i == 11) {

                button = new JButton(); // plus sign
                button.setText("+");
                standardButton.add(button);
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton4 = button;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton4.setBackground(new Color(0, 191, 255));
                        finalButton4.setForeground(Color.black);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton4.setBackground(new Color(13, 13, 13));
                        finalButton4.setForeground(Color.white);

                    }
                });
                keyboardPanel.add(button);

            } else if (i < 3) {

                button = new JButton();
                button.setText("" + (7 + i));
                standardButton.add(button);
                button.setPreferredSize(new Dimension(50, 75));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                final int number = 7+i;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        output += number;
                        display.setText(output);

                    }
                });
                keyboardPanel.add(button);

            } else if (i < 7) {

                button = new JButton();
                button.setText("" + (i));
                standardButton.add(button);
                button.setPreferredSize(new Dimension(50, 75));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                final int number = i;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        output += (number);
                        display.setText(output);

                    }
                });
                keyboardPanel.add(button);

            } else if (i < 11) {

                button = new JButton();
                button.setText("" + (i - 7));
                standardButton.add(button);
                button.setPreferredSize(new Dimension(50, 75));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                final int number = i-7;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        output += number;
                        display.setText(output);

                    }
                });
                keyboardPanel.add(button);

            }

        }

        JButton btn = new JButton();
        btn.setText("\u00B1");
        standardButton.add(button);
        btn.setBackground(new Color(13, 13, 13));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(btn);

        btn = new JButton();
        btn.setText("0");
        standardButton.add(button);
        btn.setBackground(new Color(6, 6, 6));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                output += 0;
                display.setText(output);

            }
        });
        keyboardPanel.add(btn);

        btn = new JButton();
        btn.setText(".");
        standardButton.add(button);
        btn.setBackground(new Color(13, 13, 13));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(btn);

        button = new JButton();
        button.setText("=");
        standardButton.add(button);
        button.setPreferredSize(new Dimension(50, 75));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton5 = button;
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton5.setBackground(new Color(0, 191, 255));
                finalButton5.setForeground(Color.black);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton5.setBackground(new Color(13, 13, 13));
                finalButton5.setForeground(Color.white);

            }
        });
        keyboardPanel.add(button);

        // center JPanel

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(31, 31, 31));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        centerPanel.add(scrollPane, BorderLayout.SOUTH);
//        centerPanel.add(display, BorderLayout.SOUTH);

        // Label

        JLabel label = new JLabel();
        label.setText("Standard");
        label.setForeground(Color.white);
        label.setFont(new Font("Santa Fe LET", Font.BOLD, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(new Color(31, 31, 31));
        mainPanel.add(label, BorderLayout.NORTH);

        return mainPanel;
    }


    /**
     * This method is for scientific calculator part
     *
     * @return panel of scientific part
     */
    private JPanel scientificPanel() {

        BorderLayout borderLayout = new BorderLayout(5, 5);
        JPanel mainPanel = new JPanel(borderLayout);
        mainPanel.setBackground(new Color(31, 31, 31));
        mainPanel.setLocation(5, 5);
        mainPanel.setSize(410, 605);

        JPanel keyboardPanel = new JPanel(new GridLayout(6, 5));
        keyboardPanel.setBackground(new Color(31, 31, 31));

        mainPanel.add(keyboardPanel, BorderLayout.SOUTH);

        // Buttons

        // First row
        JButton button = new JButton(); // AC button
        button.setText("AC");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // x power of y
        button.setText("<html><div><p>x<sup>y</sup></p></div></html>");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // sin or cos
        button.setText("<html><div><p>Sin<sub>Cos</sub></p></div></html>");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // tan or cot
        button.setText("<html><div><p>Tan<sub>Cot</sub></p></div></html>");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // Shift
        button.setText("Shift");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton6 = button;
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton6.setBackground(Color.red);
                finalButton6.setForeground(Color.white);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton6.setBackground(new Color(13, 13, 13));
                finalButton6.setForeground(Color.white);

            }
        });
        keyboardPanel.add(button);


        // Second row

        button = new JButton(); // square root of number
        button.setText("\u221A");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // square of number
        button.setText("x\u00B2");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // logarithm of number
        button.setText("log");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // exponential of number
        button.setText("Exp");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // Division sign
        button.setText("\u00F7");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton1 = button;
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton1.setBackground(new Color(0, 191, 255));
                finalButton1.setForeground(Color.black);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton1.setBackground(new Color(13, 13, 13));
                finalButton1.setForeground(Color.white);

            }
        });
        keyboardPanel.add(button);


        for(int i=0 ; i<15 ; i++) {

            if(i == 4) {

                button = new JButton(); // Multiplication sign
                button.setText("\u00D7");
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton2 = button;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton2.setBackground(new Color(0, 191, 255));
                        finalButton2.setForeground(Color.black);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton2.setBackground(new Color(13, 13, 13));
                        finalButton2.setForeground(Color.white);

                    }
                });
                keyboardPanel.add(button);

            } else if (i == 9){

                button = new JButton(); // negative sign
                button.setText("-");
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton3 = button;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton3.setBackground(new Color(0, 191, 255));
                        finalButton3.setForeground(Color.black);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton3.setBackground(new Color(13, 13, 13));
                        finalButton3.setForeground(Color.white);

                    }
                });
                keyboardPanel.add(button);

            } else if (i == 14) {

                button = new JButton(); // plus sign
                button.setText("+");
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton4 = button;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton4.setBackground(new Color(0, 191, 255));
                        finalButton4.setForeground(Color.black);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        System.out.println(e.getLocationOnScreen().toString());
                        finalButton4.setBackground(new Color(13, 13, 13));
                        finalButton4.setForeground(Color.white);

                    }
                });
                keyboardPanel.add(button);

            } else if (i == 0) {

                button = new JButton(); // Euler number
                button.setText("e");
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setToolTipText("EULER's Number");
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i == 5) {

                button = new JButton(); // pi number
                button.setText("\u03C0");
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setToolTipText("PI number");
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i == 10) {

                button = new JButton(); // plus-negative sign
                button.setText("\u00B1");
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i < 4) {

                button = new JButton();
                button.setText("" + (6 + i));
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setPreferredSize(new Dimension(50, 75));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i < 9) {

                button = new JButton();
                button.setText("" + (i-2));
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setPreferredSize(new Dimension(50, 75));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i < 14) {

                button = new JButton();
                button.setText("" + (i - 10));
                scientificButton.add(button);
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            }

        }

        // Last row

        button = new JButton(); // opened Parentheses
        button.setText("(");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // closed Parentheses
        button.setText(")");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // number 0
        button.setText("0");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(6, 6, 6));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // dot
        button.setText(".");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // equal sign
        button.setText("=");
        scientificButton.add(button);
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton5 = button;
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton5.setBackground(new Color(0, 191, 255));
                finalButton5.setForeground(Color.black);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton5.setBackground(new Color(13, 13, 13));
                finalButton5.setForeground(Color.white);

            }
        });
        keyboardPanel.add(button);


        JLabel label = new JLabel();
        label.setText("Scientific");
        label.setBackground(new Color(13, 13, 13));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Santa Fe LET", Font.BOLD, 40));
        label.setBorder(BorderFactory.createLineBorder(new Color(31, 31, 31), 1));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(label, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(31, 31, 31));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Text Area

        JTextPane display = new JTextPane();
        display.setEditable(false);
        display.setSize(400, 300);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setFont(new Font("Santa Fe LET", Font.PLAIN, 54));
        display.setText(" 0");
        display.setBackground(new Color(31, 31, 31));
        display.setForeground(Color.white);
        centerPanel.add(display, BorderLayout.SOUTH);

        return mainPanel;

    }

}
