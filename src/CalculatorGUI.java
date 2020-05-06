import com.sun.org.apache.bcel.internal.generic.CALOAD;
import com.sun.org.apache.bcel.internal.generic.JsrInstruction;
import javafx.application.Platform;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class CalculatorGUI {

    private JFrame calcFrame;
    private List<JButton> standardButton;
    private List<JButton> scientificButton;
    private String output = "";
    private String outputSci = "";
    private boolean first = false;
    private double firstNumber;
    private double secondNumber;
    private Process process = Process.NOTHING;
    private boolean firstSci = false;
    private double firstNumberSci;
    private double secondNumberSci;
    private Process processSci = Process.NOTHING;
    private boolean isShift = false;
    private boolean isRadians;

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
        calcFrame.setSize(430, 710);
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

        calcFrame.setJMenuBar(menu(tabs));
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
        display.setToolTipText("Screen of Standard Calculator");
        display.setEditable(false);
        display.setSize(410, 600);
        display.setLocation(0, 0);
        display.setFont(new Font("Santa Fe LET", Font.PLAIN, 54));
        display.setText(" 0");
        display.setBackground(new Color(31, 31, 31));
        display.setForeground(Color.white);
        display.setLineWrap(true);

        display.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if(e.getKeyChar() == '1' || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                    output += "1";
                    display.setText(output);
                } else if(e.getKeyChar() == '2' || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                    output += "2";
                    display.setText(output);
                } else if(e.getKeyChar() == '3' || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                    output += "3";
                    display.setText(output);
                } else if(e.getKeyChar() == '4' || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                    output += "4";
                    display.setText(output);
                } else if(e.getKeyChar() == '5' || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                    output += "5";
                    display.setText(output);
                } else if(e.getKeyChar() == '6' || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                    output += "6";
                    display.setText(output);
                } else if(e.getKeyChar() == '7' || e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                    output += "7";
                    display.setText(output);
                } else if(e.getKeyChar() == '8' || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                    output += "8";
                    display.setText(output);
                } else if(e.getKeyChar() == '9' || e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                    output += "9";
                    display.setText(output);
                } else if(e.getKeyChar() == '0' || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                    if(!output.equals("0")) {
                        output += "0";
                        display.setText(output);
                    }
                } else if(e.getKeyChar() == '\b' || e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if(output.length()>0) {
                        output = output.substring(0, output.length()-1);
                        display.setText(output);
                    }
                } else if(e.getKeyChar() == '/' && !first) { // division for keyboard

                    try {

                        firstNumber = Double.parseDouble(output);
                        first = true;
                        output = "";
                        display.setText(output);
                        process = Process.DIVISION;
                        System.out.println(firstNumber);

                    } catch(NumberFormatException err) {
                        err.printStackTrace();
                    }

                } else if(e.getKeyChar() == '-' && !first) { // subtraction for keyboard

                    try {

                        firstNumber = Double.parseDouble(output);
                        first = true;
                        output = "";
                        display.setText(output);
                        process = Process.SUBTRACT;
                        System.out.println(firstNumber);

                    } catch(NumberFormatException err) {
                        err.printStackTrace();
                    }

                } else if(e.getKeyChar() == '+' && !first) { // summation for keyboard

                    try {

                        firstNumber = Double.parseDouble(output);
                        first = true;
                        output = "";
                        display.setText(output);
                        process = Process.SUM;
                        System.out.println(firstNumber);

                    } catch(NumberFormatException err) {
                        err.printStackTrace();
                    }

                } else if(e.getKeyChar() == '*' && !first) { // multiplication for keyboard

                    try {

                        firstNumber = Double.parseDouble(output);
                        first = true;
                        output = "";
                        display.setText(output);
                        process = Process.MULTIPLY;
                        System.out.println(firstNumber);

                    } catch(NumberFormatException err) {
                        err.printStackTrace();
                    }

                } else if((e.getKeyChar() == '=' || e.getKeyChar() == '\n') && first) {

                    try {

                        secondNumber = Double.parseDouble(output);

                        if(process == Process.SUM) {

                            firstNumber = CalculatorFunction.sum(firstNumber, secondNumber);
                            output = "" + firstNumber;
                            display.setText(output);

                            first = false;

                        } else if(process == Process.SUBTRACT) {

                            firstNumber = CalculatorFunction.subtract(firstNumber, secondNumber);
                            output = "" + firstNumber;
                            display.setText(output);

                            first = false;

                        } else if(process == Process.MULTIPLY) {

                            firstNumber = CalculatorFunction.multiply(firstNumber, secondNumber);
                            output = "" + firstNumber;
                            display.setText(output);

                            first = false;

                        } else if(process == Process.DIVISION) {

                            firstNumber = CalculatorFunction.division(firstNumber, secondNumber);
                            if((int)firstNumber == Integer.MAX_VALUE) {
                                output = "Error";
                                display.setText(output);
                            } else {
                                output = "" + firstNumber;
                                display.setText(output);
                            }

                            first = false;

                        }

                    } catch (NumberFormatException err) {
                        err.printStackTrace();
                    }

                }


            }
        });

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
        button.setToolTipText("Each Left click remove char by char and Each Right click remove all of chars");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(output.length()>0){

                        if(output.equals("Error")) {

                            output = "";

                        } else {

                            output = output.substring(0, output.length()-1);

                        }
                        display.setText(output);

                    }

                } else if(e.getButton() == MouseEvent.BUTTON3) {

                    output = "";
                    display.setText(output);

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // square of number
        button.setText("x\u00B2");
        standardButton.add(button);
        button.setToolTipText("Square of expected number");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    try {

                        double number = Integer.parseInt(output);
                        output = "" + CalculatorFunction.square(number);
                        display.setText(output);

                    } catch (Exception err) {
                        err.printStackTrace();
                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // square root of number
        button.setText("\u221A");
        standardButton.add(button);
        button.setToolTipText("Square root of expected number");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    try {

                        double number = Integer.parseInt(output);
                        output = "" + CalculatorFunction.squareRoot(number);
                        display.setText(output);

                    } catch (Exception err) {
                        err.printStackTrace();
                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // Division sign
        button.setText("\u00F7");
        standardButton.add(button);
        button.setToolTipText("Division");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton1 = button;
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(!first){

                        try {

                            firstNumber = Double.parseDouble(output);
                            first = true;
                            output = "";
                            display.setText(output);
                            process = Process.DIVISION;
                            System.out.println(firstNumber);

                        } catch(NumberFormatException err) {
                            err.printStackTrace();
                        }

                    } else {

                        try {

                            secondNumber = Double.parseDouble(output);

                            if(process == Process.SUM) {

                                firstNumber = CalculatorFunction.sum(firstNumber, secondNumber);
                                output = "" + firstNumber;
                                display.setText(output);
                                display.revalidate();
                                display.repaint();


                                Timer timer = new Timer( 2000, err -> {

                                    output = "";
                                    display.setText(output);

                                });
                                timer.setRepeats( false );//make sure the timer only runs once
                                timer.start();


                            } else if(process == Process.SUBTRACT) {

                                firstNumber = CalculatorFunction.subtract(firstNumber, secondNumber);
                                output = "" + firstNumber;
                                display.setText(output);


                            } else if(process == Process.MULTIPLY) {

                                firstNumber = CalculatorFunction.multiply(firstNumber, secondNumber);
                                output = "" + firstNumber;
                                display.setText(output);


                            } else if(process == Process.DIVISION) {

                                firstNumber = CalculatorFunction.division(firstNumber, secondNumber);

                                if((int)firstNumber == Integer.MAX_VALUE) {
                                    output = "Error";
                                    display.setText(output);
                                } else {
                                    output = "" + firstNumber;
                                    display.setText(output);
                                    display.revalidate();
                                    display.repaint();


                                    Timer timer = new Timer( 2000, err -> {

                                        output = "";
                                        display.setText(output);

                                    });
                                    timer.setRepeats( false );//make sure the timer only runs once
                                    timer.start();
                                }

                            }

                        } catch (NumberFormatException err ) {
                            err.printStackTrace();
                        }

                    }

                }

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
                button.setToolTipText("Multiplication");
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton2 = button;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            if(!first) {

                                try {

                                    firstNumber = Double.parseDouble(output);
                                    first = true;
                                    output = "";
                                    display.setText(output);
                                    process = Process.MULTIPLY;
                                    System.out.println(firstNumber);

                                } catch(NumberFormatException err) {
                                    err.printStackTrace();
                                }


                            } else {

                                try {

                                    secondNumber = Double.parseDouble(output);

                                    if(process == Process.SUM) {

                                        firstNumber = CalculatorFunction.sum(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);
                                        display.revalidate();
                                        display.repaint();


                                        Timer timer = new Timer( 2000, err -> {

                                            output = "";
                                            display.setText(output);

                                        });
                                        timer.setRepeats( false );//make sure the timer only runs once
                                        timer.start();

//                                        first = false;

                                    } else if(process == Process.SUBTRACT) {

                                        firstNumber = CalculatorFunction.subtract(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);

                                        first = false;

                                    } else if(process == Process.MULTIPLY) {

                                        firstNumber = CalculatorFunction.multiply(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);
                                        display.revalidate();
                                        display.repaint();


                                        Timer timer = new Timer( 2000, err -> {

                                            output = "";
                                            display.setText(output);

                                        });
                                        timer.setRepeats( false );//make sure the timer only runs once
                                        timer.start();

                                    } else if(process == Process.DIVISION) {

                                        firstNumber = CalculatorFunction.division(firstNumber, secondNumber);
                                        if((int)firstNumber == Integer.MAX_VALUE) {
                                            output = "Error";
                                            display.setText(output);
                                        } else {
                                            output = "" + firstNumber;
                                            display.setText(output);
                                        }

                                        first = false;

                                    }

                                } catch (NumberFormatException err ) {
                                    err.printStackTrace();
                                }

                            }

                        }

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
                button.setToolTipText("Subtraction");
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton3 = button;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            if(!first) {

                                try {

                                    firstNumber = Double.parseDouble(output);
                                    first = true;
                                    output = "";
                                    display.setText(output);
                                    process = Process.SUBTRACT;
                                    System.out.println(firstNumber);

                                } catch(NumberFormatException err) {
                                    err.printStackTrace();
                                }


                            } else {

                                try {

                                    secondNumber = Double.parseDouble(output);

                                    if(process == Process.SUM) {

                                        firstNumber = CalculatorFunction.sum(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);
                                        display.revalidate();
                                        display.repaint();


                                        Timer timer = new Timer( 2000, err -> {

                                            output = "";
                                            display.setText(output);

                                        });
                                        timer.setRepeats( false );//make sure the timer only runs once
                                        timer.start();

//                                        first = false;

                                    } else if(process == Process.SUBTRACT) {

                                        firstNumber = CalculatorFunction.subtract(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);
                                        display.revalidate();
                                        display.repaint();


                                        Timer timer = new Timer( 2000, err -> {

                                            output = "";
                                            display.setText(output);

                                        });
                                        timer.setRepeats( false );//make sure the timer only runs once
                                        timer.start();

                                    } else if(process == Process.MULTIPLY) {

                                        firstNumber = CalculatorFunction.multiply(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);
                                        display.revalidate();
                                        display.repaint();


                                        Timer timer = new Timer( 2000, err -> {

                                            output = "";
                                            display.setText(output);

                                        });
                                        timer.setRepeats( false );//make sure the timer only runs once
                                        timer.start();

                                    } else if(process == Process.DIVISION) {

                                        firstNumber = CalculatorFunction.division(firstNumber, secondNumber);
                                        if((int)firstNumber == Integer.MAX_VALUE) {
                                            output = "Error";
                                            display.setText(output);
                                        } else {
                                            output = "" + firstNumber;
                                            display.setText(output);
                                        }

                                        first = false;

                                    }

                                } catch (NumberFormatException err ) {
                                    err.printStackTrace();
                                }

                            }

                        }

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
                button.setToolTipText("Summation");
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton4 = button;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            if(!first) {

                                try {

                                    firstNumber = Double.parseDouble(output);
                                    first = true;
                                    output = "";
                                    display.setText(output);
                                    process = Process.SUM;
                                    System.out.println(firstNumber);

                                } catch(NumberFormatException err) {
                                    err.printStackTrace();
                                }


                            } else {

                                try {

                                    secondNumber = Double.parseDouble(output);

                                    if(process == Process.SUM) {

                                        firstNumber = CalculatorFunction.sum(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);
                                        display.revalidate();
                                        display.repaint();


                                        Timer timer = new Timer( 2000, err -> {

                                            output = "";
                                            display.setText(output);

                                    });
                                        timer.setRepeats( false );//make sure the timer only runs once
                                        timer.start();

//                                        first = false;

                                    } else if(process == Process.SUBTRACT) {

                                        firstNumber = CalculatorFunction.subtract(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);

                                        first = false;

                                    } else if(process == Process.MULTIPLY) {

                                        firstNumber = CalculatorFunction.multiply(firstNumber, secondNumber);
                                        output = "" + firstNumber;
                                        display.setText(output);

                                        first = false;

                                    } else if(process == Process.DIVISION) {

                                        firstNumber = CalculatorFunction.division(firstNumber, secondNumber);
                                        if((int)firstNumber == Integer.MAX_VALUE) {
                                            output = "Error";
                                            display.setText(output);
                                        } else {
                                            output = "" + firstNumber;
                                            display.setText(output);
                                        }

                                        first = false;

                                    }

                                } catch (NumberFormatException err ) {
                                    err.printStackTrace();
                                }

                            }

                        }

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

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            output += number;
                            display.setText(output);

                        }

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

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            output += (number);
                            display.setText(output);

                        }

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

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            output += number;
                            display.setText(output);

                        }

                    }
                });
                keyboardPanel.add(button);

            }

        }

        JButton btn = new JButton();
        btn.setText("\u00B1");
        standardButton.add(button);
        btn.setToolTipText("Change number sign");
        btn.setBackground(new Color(13, 13, 13));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(!output.equals("0") && !output.equals("")) {
                        if(output.startsWith("-")) {
                            output = output.replace("-", "");
                        } else {
                            output = "-" + output;
                        }
                    }
                    display.setText(output);

                }

            }
        });
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

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(!output.equals("0")) {

                        output += 0;
                        display.setText(output);

                    }

                }

            }
        });
        keyboardPanel.add(btn);

        btn = new JButton();
        btn.setText(".");
        standardButton.add(button);
        btn.setToolTipText("Make decimal number");
        btn.setBackground(new Color(13, 13, 13));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(output.equals("") || output.equals("0")) {

                        output = "0.";

                    } else {

                        if(!output.contains(".")) {
                            output += ".";
                        }

                    }
                    display.setText(output);

                }

            }
        });
        keyboardPanel.add(btn);

        button = new JButton(); // equation sign
        button.setText("=");
        standardButton.add(button);
        button.setToolTipText("Equal");
        button.setPreferredSize(new Dimension(50, 75));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton5 = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(first) {

                        try {

                            secondNumber = Double.parseDouble(output);

                            if(process == Process.SUM) {

                                firstNumber = CalculatorFunction.sum(firstNumber, secondNumber);
                                output = "" + firstNumber;
                                display.setText(output);

                                first = false;

                            } else if(process == Process.SUBTRACT) {

                                firstNumber = CalculatorFunction.subtract(firstNumber, secondNumber);
                                output = "" + firstNumber;
                                display.setText(output);

                                first = false;

                            } else if(process == Process.MULTIPLY) {

                                firstNumber = CalculatorFunction.multiply(firstNumber, secondNumber);
                                output = "" + firstNumber;
                                display.setText(output);

                                first = false;

                            } else if(process == Process.DIVISION) {

                                firstNumber = CalculatorFunction.division(firstNumber, secondNumber);
                                if((int)firstNumber == Integer.MAX_VALUE) {
                                    output = "Error";
                                    display.setText(output);
                                } else {
                                    output = "" + firstNumber;
                                    display.setText(output);
                                }

                                first = false;

                            }

                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }

                    }

                }

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


        // Text Area

        JTextArea display = new JTextArea();
        display.setToolTipText("Screen of Scientific Calculator");
        display.setEditable(false);
        display.setSize(400, 300);
        display.setFont(new Font("Santa Fe LET", Font.PLAIN, 54));
        display.setText(" 0");
        display.setBackground(new Color(31, 31, 31));
        display.setForeground(Color.white);
        display.setLineWrap(true);
        display.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if(e.getKeyChar() == '1' || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                    outputSci += "1";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '2' || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                    outputSci += "2";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '3' || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                    outputSci += "3";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '4' || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                    outputSci += "4";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '5' || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                    outputSci += "5";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '6' || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                    outputSci += "6";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '7' || e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                    outputSci += "7";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '8' || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                    outputSci += "8";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '9' || e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                    outputSci += "9";
                    display.setText(outputSci);
                } else if(e.getKeyChar() == '0' || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                    if(!outputSci.equals("0")) {
                        outputSci += "0";
                        display.setText(outputSci);
                    }
                } else if(e.getKeyChar() == '\b' || e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if(outputSci.length()>0) {
                        outputSci = outputSci.substring(0, outputSci.length()-1);
                        display.setText(outputSci);
                    }
                } else if(e.getKeyChar() == '/' && !firstSci) { // division for keyboard

                    try {

                        firstNumberSci = Double.parseDouble(outputSci);
                        firstSci = true;
                        outputSci = "";
                        display.setText(outputSci);
                        processSci = Process.DIVISION;
                        System.out.println(firstNumberSci);

                    } catch(NumberFormatException err) {
                        err.printStackTrace();
                    }

                } else if(e.getKeyChar() == '-' && !firstSci) { // subtraction for keyboard

                    try {

                        firstNumberSci = Double.parseDouble(outputSci);
                        firstSci = true;
                        outputSci = "";
                        display.setText(outputSci);
                        processSci = Process.SUBTRACT;
                        System.out.println(firstNumberSci);

                    } catch(NumberFormatException err) {
                        err.printStackTrace();
                    }

                } else if(e.getKeyChar() == '+' && !firstSci) { // summation for keyboard

                    try {

                        firstNumberSci = Double.parseDouble(outputSci);
                        firstSci = true;
                        outputSci = "";
                        display.setText(outputSci);
                        processSci = Process.SUM;
                        System.out.println(firstNumberSci);

                    } catch(NumberFormatException err) {
                        err.printStackTrace();
                    }

                } else if(e.getKeyChar() == '*' && !firstSci) { // multiplication for keyboard

                    try {

                        firstNumberSci = Double.parseDouble(outputSci);
                        firstSci = true;
                        outputSci = "";
                        display.setText(outputSci);
                        processSci = Process.MULTIPLY;
                        System.out.println(firstNumberSci);

                    } catch(NumberFormatException err) {
                        err.printStackTrace();
                    }

                } else if((e.getKeyChar() == '=' || e.getKeyChar() == '\n') && firstSci) {

                    try {

                        secondNumberSci = Double.parseDouble(outputSci);

                        if(processSci == Process.SUM) {

                            firstNumberSci = CalculatorFunction.sum(firstNumberSci, secondNumberSci);
                            outputSci = "" + firstNumberSci;
                            display.setText(outputSci);

                            firstSci = false;

                        } else if(processSci == Process.SUBTRACT) {

                            firstNumberSci = CalculatorFunction.subtract(firstNumberSci, secondNumberSci);
                            outputSci = "" + firstNumberSci;
                            display.setText(outputSci);

                            firstSci = false;

                        } else if(processSci == Process.MULTIPLY) {

                            firstNumberSci = CalculatorFunction.multiply(firstNumberSci, secondNumberSci);
                            outputSci = "" + firstNumberSci;
                            display.setText(outputSci);

                            firstSci = false;

                        } else if(processSci == Process.DIVISION) {

                            firstNumberSci = CalculatorFunction.division(firstNumberSci, secondNumberSci);
                            if((int)firstNumberSci == Integer.MAX_VALUE) {
                                outputSci = "Error";
                                display.setText(outputSci);
                            } else {
                                outputSci = "" + firstNumberSci;
                                display.setText(outputSci);
                            }

                            firstSci = false;

                        }

                    } catch (NumberFormatException err) {
                        err.printStackTrace();
                    }

                }


            }
        });


        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(410, 120));
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 31, 31), 2));


        // Buttons

        // First row
        JButton button = new JButton(); // AC button
        button.setText("AC");
        scientificButton.add(button);
        button.setToolTipText("Each Left click remove char by char and Each Right click remove all chars");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(outputSci.length()>0){

                        if(outputSci.equals("Error")) {

                            outputSci = "";

                        } else {

                            outputSci = outputSci.substring(0, outputSci.length()-1);

                        }
                        display.setText(outputSci);

                    }

                } else if(e.getButton() == MouseEvent.BUTTON3) {

                    outputSci = "";
                    display.setText(outputSci);

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // x power of y
        button.setText("<html><div><p>x<sup>y</sup></p></div></html>");
        scientificButton.add(button);
        button.setToolTipText("binary operator for power");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 23));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(!firstSci) {

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            firstSci = true;
                            outputSci = "";
                            display.setText(outputSci);
                            processSci = Process.POWER;
                            System.out.println(firstNumberSci);

                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }

                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // sin or cos
        button.setText("<html><div><p>Sin<sub>Cos</sub></p></div></html>");
        scientificButton.add(button);
        button.setToolTipText("If shift be enable, calculate cos else calculate sin");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 18));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(isShift && firstSci) {

                        try {

                            secondNumberSci = Double.parseDouble(outputSci);
                            if(isRadians){

                                outputSci = "" + Math.cos(secondNumberSci);

                            } else {

                                outputSci = "" + Math.cos(Math.toDegrees(secondNumberSci));

                            }
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if (!isShift && firstSci) {

                        try {

                            secondNumberSci = Double.parseDouble(outputSci);
                            if(isRadians) {

                                outputSci = "" + Math.sin(secondNumberSci);

                            } else {

                                outputSci = "" + Math.sin(Math.toDegrees(secondNumberSci));

                            }
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if(isShift && !firstSci) {

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            if(isRadians) {

                                outputSci = "" + Math.cos(firstNumberSci);

                            } else {

                                outputSci = "" + Math.cos(Math.toDegrees(firstNumberSci));

                            }
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if(!isShift && !firstSci) {

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            if(isRadians) {

                                outputSci = "" + Math.sin(firstNumberSci);

                            } else {

                                outputSci = "" + Math.sin(Math.toDegrees(firstNumberSci));

                            }
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // tan or cot
        button.setText("<html><div><p>Tan<sub>Cot</sub></p></div></html>");
        scientificButton.add(button);
        button.setToolTipText("If shift be enabled, calculate cot else calculate tan");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 18));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(isShift && firstSci) {

                        try {

                            secondNumberSci = Double.parseDouble(outputSci);
                            if(isRadians) {

                                outputSci = "" + (1/Math.tan(secondNumberSci));

                            } else {

                                outputSci = "" + (1/Math.tan(Math.toDegrees(secondNumberSci)));

                            }
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if (!isShift && firstSci) {

                        try {

                            secondNumberSci = Double.parseDouble(outputSci);
                            if(isRadians) {

                                outputSci = "" + Math.tan(secondNumberSci);

                            } else {

                                outputSci = "" + Math.tan(Math.toDegrees(secondNumberSci));

                            }
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if(isShift && !firstSci) {

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            if(isRadians) {

                                outputSci = "" + (1/Math.tan(firstNumberSci));

                            } else {

                                outputSci = "" + (1/Math.tan(Math.toDegrees(firstNumberSci)));

                            }
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if(!isShift && !firstSci) {

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            if(isRadians) {

                                outputSci = "" + Math.tan(firstNumberSci);

                            } else {

                                outputSci = "" + Math.tan(Math.toDegrees(firstNumberSci));

                            }
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    }

                }

            }
        });
        keyboardPanel.add(button);


        button = new JButton(); // Shift
        button.setText("Shift");
        scientificButton.add(button);
        button.setToolTipText("This button can change state of some buttons");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 22));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton6 = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(isShift) {
                        isShift = false;
                    } else {
                        isShift = true;
                    }

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                System.out.println(e.getLocationOnScreen().toString());
                finalButton6.setBackground(Color.red);
                finalButton6.setForeground(Color.white);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                if(!isShift) {
                    System.out.println(e.getLocationOnScreen().toString());
                    finalButton6.setBackground(new Color(13, 13, 13));
                    finalButton6.setForeground(Color.white);
                }

            }
        });
        keyboardPanel.add(button);


        // Second row

        button = new JButton(); // square root of number
        button.setText("\u221A");
        scientificButton.add(button);
        button.setToolTipText("Square root of expected number");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    try {

                        double number = Double.parseDouble(outputSci);
                        outputSci = "" + CalculatorFunction.squareRoot(number);
                        display.setText(outputSci);

                    } catch (Exception err) {
                        err.printStackTrace();
                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // square of number
        button.setText("x\u00B2");
        scientificButton.add(button);
        button.setToolTipText("Square of expected number");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    try {

                        double number = Double.parseDouble(outputSci);
                        outputSci = "" + CalculatorFunction.square(number);
                        display.setText(outputSci);

                    } catch (Exception err) {
                        err.printStackTrace();
                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // logarithm of number
        button.setText("<html><div> <p>log2<sub>10</sub></p> </div></html>");
        scientificButton.add(button);
        button.setToolTipText("If shift be enabled, calculate log in base 10 else calculate log in base 2");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(isShift && firstSci) {

                        try {

                            secondNumberSci = Double.parseDouble(outputSci);
                            outputSci = "" + Math.log10(secondNumberSci);
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if (!isShift && firstSci) {

                        try {

                            secondNumberSci = Double.parseDouble(outputSci);
                            outputSci = "" + Math.log(secondNumberSci);
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if(isShift && !firstSci) {

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            outputSci = "" + Math.log10(firstNumberSci);
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    } else if(!isShift && !firstSci) {

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            outputSci = "" + Math.log(firstNumberSci);
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {

                            err.printStackTrace();

                        }

                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // exponential of number
        button.setText("Exp");
        scientificButton.add(button);
        button.setToolTipText("Exponential of expected number");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 23));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(firstSci) {

                        try {

                            secondNumberSci = Double.parseDouble(outputSci);
                            outputSci = "" + Math.exp(secondNumberSci);
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }

                    } else {

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            outputSci = "" + Math.exp(firstNumberSci);
                            display.setText(outputSci);

                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }

                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // Division sign
        button.setText("\u00F7");
        scientificButton.add(button);
        button.setToolTipText("Division");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton1 = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(!firstSci){

                        try {

                            firstNumberSci = Double.parseDouble(outputSci);
                            firstSci = true;
                            outputSci = "";
                            display.setText(outputSci);
                            processSci = Process.DIVISION;
                            System.out.println(firstNumberSci);

                        } catch(NumberFormatException err) {
                            err.printStackTrace();
                        }

                    } else {

                        secondNumberSci = Double.parseDouble(outputSci);

                        firstNumberSci = CalculatorFunction.division(firstNumberSci, secondNumberSci);
                        if(firstNumberSci == Integer.MAX_VALUE) {

                            outputSci = "ERROR";
                            display.setText(outputSci);

                        } else {

                            outputSci = "" + firstNumberSci;
                            display.setText(outputSci);

                            Timer timer = new Timer( 2000, ee -> {

                                outputSci = "";
                                display.setText(outputSci);

                            } );
                            timer.setRepeats( false );//make sure the timer only runs once
                            timer.start();

                        }

                    }

                }

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
                button.setToolTipText("Multiplication");
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton2 = button;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            if(!firstSci) {

                                try {

                                    firstNumberSci = Double.parseDouble(outputSci);
                                    firstSci = true;
                                    outputSci = "";
                                    display.setText(outputSci);
                                    processSci = Process.MULTIPLY;
                                    System.out.println(firstNumberSci);

                                } catch(NumberFormatException err) {
                                    err.printStackTrace();
                                }

                            } else {

                                secondNumberSci = Double.parseDouble(outputSci);

                                firstNumberSci = CalculatorFunction.multiply(firstNumberSci, secondNumberSci);
                                outputSci = "" + firstNumberSci;
                                display.setText(outputSci);

                                Timer timer = new Timer( 2000, ee -> {

                                    outputSci = "";
                                    display.setText(outputSci);

                                } );
                                timer.setRepeats( false );//make sure the timer only runs once
                                timer.start();

                            }

                        }

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
                button.setToolTipText("Subtraction");
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton3 = button;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            if(!firstSci) {

                                try {

                                    firstNumberSci = Double.parseDouble(outputSci);
                                    firstSci = true;
                                    outputSci = "";
                                    display.setText(outputSci);
                                    processSci = Process.SUBTRACT;
                                    System.out.println(firstNumberSci);

                                } catch(NumberFormatException err) {
                                    err.printStackTrace();
                                }


                            } else {

                                secondNumberSci = Double.parseDouble(outputSci);

                                firstNumberSci = CalculatorFunction.subtract(firstNumberSci, secondNumberSci);
                                outputSci = "" + firstNumberSci;
                                display.setText(outputSci);

                                Timer timer = new Timer( 2000, ee -> {

                                    outputSci = "";
                                    display.setText(outputSci);

                                } );
                                timer.setRepeats( false );//make sure the timer only runs once
                                timer.start();

                            }

                        }

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
                button.setToolTipText("summation");
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                JButton finalButton4 = button;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            if(!firstSci) {

                                try {

                                    firstNumberSci = Double.parseDouble(outputSci);
                                    firstSci = true;
                                    outputSci = "";
                                    display.setText(outputSci);
                                    processSci = Process.SUM;
                                    System.out.println(firstNumberSci);

                                } catch(NumberFormatException err) {
                                    err.printStackTrace();
                                }


                            } else {

                                secondNumberSci = Double.parseDouble(outputSci);

                                firstNumberSci = CalculatorFunction.sum(firstNumberSci, secondNumberSci);
                                outputSci = "" + firstNumberSci;
                                display.setText(outputSci);

                                Timer timer = new Timer( 2000, ee -> {

                                    outputSci = "";
                                    display.setText(outputSci);

                                } );
                                timer.setRepeats( false );//make sure the timer only runs once
                                timer.start();

                            }

                        }

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
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            outputSci = "" + Math.E;
                            display.setText(outputSci);

                        }

                    }
                });
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
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            outputSci = "" + Math.PI;
                            display.setText(outputSci);

                        }

                    }
                });
                keyboardPanel.add(button);

            } else if (i == 10) {

                button = new JButton(); // plus-negative sign
                button.setText("\u00B1");
                scientificButton.add(button);
                button.setToolTipText("Change number sign");
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            if(!outputSci.equals("0") && !outputSci.equals("")) {
                                if(outputSci.startsWith("-")) {
                                    outputSci = outputSci.replace("-", "");
                                } else {
                                    outputSci = "-" + outputSci;
                                }
                            }
                            display.setText(outputSci);

                        }

                    }
                });
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
                final int number = 6 + i;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        outputSci += number;
                        display.setText(outputSci);

                    }
                });
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
                final int number = i - 2;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        outputSci += number;
                        display.setText(outputSci);

                    }
                });
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
                final int number = i - 10;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        outputSci += number;
                        display.setText(outputSci);

                    }
                });
                keyboardPanel.add(button);

            }

        }

        // Last row

        button = new JButton(); // factorial
        button.setText("n!");
        scientificButton.add(button);
        button.setToolTipText("Calculate factorial of expected number");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(firstSci) {

                    try {

                        secondNumberSci = Double.parseDouble(outputSci);
                        outputSci = "" + CalculatorFunction.factorial((int)secondNumberSci);
                        display.setText(outputSci);

                    } catch (NumberFormatException err){
                        err.printStackTrace();
                    }

                } else {

                    try {

                        firstNumberSci = Double.parseDouble(outputSci);
                        outputSci = "" + CalculatorFunction.factorial((int)firstNumberSci);
                        display.setText(outputSci);

                    } catch (NumberFormatException err) {
                        err.printStackTrace();
                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // Random number generator
        button.setText("<html><p>DEG<sub>Rad</sub></p></html>");
        scientificButton.add(button);
        button.setToolTipText("if shift be ON is Radian else will be Degree");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 16));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));

        final JButton finalButton = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    isRadians = !isRadians;

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                finalButton.setBackground(Color.red);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                if(!isRadians) {
                    finalButton.setBackground(new Color(13, 13, 13));
                }

            }
        });
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
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(!outputSci.equals("0")) {

                        outputSci += 0;
                        display.setText(outputSci);

                    }

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // dot
        button.setText(".");
        scientificButton.add(button);
        button.setToolTipText("Make decimal number");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(outputSci.equals("") || outputSci.equals("0")) {

                        outputSci = "0.";

                    } else {

                        if(!outputSci.contains(".")) {
                            outputSci += ".";
                        }

                    }
                    display.setText(outputSci);

                }

            }
        });
        keyboardPanel.add(button);

        button = new JButton(); // equal sign
        button.setText("=");
        scientificButton.add(button);
        button.setToolTipText("Equal");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        JButton finalButton5 = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton() == MouseEvent.BUTTON1) {

                    if(firstSci) {

                        try {

                            secondNumberSci = Double.parseDouble(outputSci);

                            if(processSci == Process.SUM) {

                                firstNumberSci = CalculatorFunction.sum(firstNumberSci, secondNumberSci);
                                outputSci = "" + firstNumberSci;
                                display.setText(outputSci);

                                firstSci = false;

                            } else if(processSci == Process.SUBTRACT) {

                                firstNumberSci = CalculatorFunction.subtract(firstNumberSci, secondNumberSci);
                                outputSci = "" + firstNumberSci;
                                display.setText(outputSci);

                                firstSci = false;

                            } else if(processSci == Process.MULTIPLY) {

                                firstNumberSci = CalculatorFunction.multiply(firstNumberSci, secondNumberSci);
                                outputSci = "" + firstNumberSci;
                                display.setText(outputSci);

                                firstSci = false;

                            } else if(processSci == Process.DIVISION) {

                                firstNumberSci = CalculatorFunction.division(firstNumberSci, secondNumberSci);
                                if((int)firstNumberSci == Integer.MAX_VALUE) {
                                    outputSci = "Error";
                                    display.setText(outputSci);
                                } else {
                                    outputSci = "" + firstNumberSci;
                                    display.setText(outputSci);
                                }

                                firstSci = false;

                            } else if(processSci == Process.POWER) {

                                firstNumberSci = CalculatorFunction.power(firstNumberSci, secondNumberSci);
                                outputSci = "" + firstNumberSci;
                                display.setText(outputSci);

                                firstSci = false;

                            }

                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }

                    }

                }

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

        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        return mainPanel;

    }


    private JMenuBar menu(JTabbedPane tabs) {

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);

        JMenuItem copyScreen = new JMenuItem("Copy Screen", KeyEvent.VK_C);
        copyScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        System.out.println(tabs.getSelectedIndex());
        copyScreen.addActionListener(e -> {
            if(tabs.getSelectedIndex() == 0) {

                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(output), null);

            } else if(tabs.getSelectedIndex() == 1) {

                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(outputSci), null);

            }
        });


        JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_E);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        exit.addActionListener(e -> {
            calcFrame.dispose();
            System.exit(0);
        });

        menu.add(copyScreen);
        menu.add(exit);

        menuBar.add(menu);

        return menuBar;
    }

}
