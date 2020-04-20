import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class CalculatorGUI {

    private JFrame calcFrame;

    public CalculatorGUI() {

        // initialize JFrame
        calcFrame = new JFrame();

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
        calcFrame.setSize(442, 695);
        calcFrame.getContentPane().setBackground(new Color(31,31,31));
        calcFrame.setResizable(true);
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


        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setBackground(new Color(31 ,31 , 31));

        GridLayout gridLayout = new GridLayout(5,4);

        keyboardPanel.setLayout(gridLayout);
        mainPanel.add(keyboardPanel, BorderLayout.SOUTH);

        // Buttons

        JButton button = new JButton(); // percent of number
        button.setText("%");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // square of number
        button.setText("x\u00B2");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // square root of number
        button.setText("\u221A");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // Division sign
        button.setText("\u00F7");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        for(int i=0 ; i<12 ; i++) {

            if(i == 3) {

                button = new JButton(); // Multiplication sign
                button.setText("\u00D7");
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i == 7){

                button = new JButton(); // Multiplication sign
                button.setText("-");
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i == 11) {

                button = new JButton(); // Multiplication sign
                button.setText("+");
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i < 3) {

                button = new JButton();
                button.setText("" + (7 + i));
                button.setPreferredSize(new Dimension(50, 75));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i < 7) {

                button = new JButton();
                button.setText("" + (i));
                button.setPreferredSize(new Dimension(50, 75));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i < 11) {

                button = new JButton();
                button.setText("" + (i - 7));
                button.setPreferredSize(new Dimension(50, 75));
                button.setBackground(new Color(6, 6, 6));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            }

        }

        JButton btn = new JButton();
        btn.setText("\u00B1");
        btn.setBackground(new Color(13, 13, 13));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(btn);

        btn = new JButton();
        btn.setText("0");
        btn.setBackground(new Color(6, 6, 6));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(btn);

        btn = new JButton();
        btn.setText(".");
        btn.setBackground(new Color(13, 13, 13));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(btn);

        button = new JButton();
        button.setText("=");
        button.setPreferredSize(new Dimension(50, 75));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        // center JPanel

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
        JButton button = new JButton(); // square number
        button.setText("x\u00B2");
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
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);


        // Second row

        button = new JButton(); // square root of number
        button.setText("\u221A");
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);

        button = new JButton(); // ten power of number
        button.setText("<html><div><p>10<sup>x</sup></p></div></html>");
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
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
        keyboardPanel.add(button);


        for(int i=0 ; i<15 ; i++) {

            if(i == 4) {

                button = new JButton(); // Multiplication sign
                button.setText("\u00D7");
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i == 9){

                button = new JButton(); // negative sign
                button.setText("-");
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i == 14) {

                button = new JButton(); // plus sign
                button.setText("+");
                button.setPreferredSize(new Dimension(50, 60));
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
                keyboardPanel.add(button);

            } else if (i == 0) {

                button = new JButton(); // Euler number
                button.setText("e");
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
        button.setPreferredSize(new Dimension(50, 60));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 1));
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
