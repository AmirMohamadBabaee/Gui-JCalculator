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
        calcFrame.setSize(425, 650);
        calcFrame.getContentPane().setBackground(new Color(31,31,31));
        calcFrame.setResizable(false);
        calcFrame.setLocationRelativeTo(null);
        calcFrame.setLayout(null);
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize JTabbedPane

        JTabbedPane tabs = new JTabbedPane();

        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(5);
        borderLayout.setHgap(5);
        JPanel mainPanel = new JPanel(borderLayout);
        mainPanel.setBackground(new Color(31, 31, 31));
        mainPanel.setLocation(5, 5);
        mainPanel.setSize(410, 605);
        calcFrame.add(mainPanel);

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
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
        keyboardPanel.add(button);

        button = new JButton(); // square of number
        button.setText("x\u00B2");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
        keyboardPanel.add(button);

        button = new JButton(); // square root of number
        button.setText("\u221A");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
        keyboardPanel.add(button);

        button = new JButton(); // Division sign
        button.setText("\u00F7");
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
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
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
                keyboardPanel.add(button);

            } else if (i == 7){

                button = new JButton(); // Multiplication sign
                button.setText("-");
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
                keyboardPanel.add(button);

            } else if (i == 11) {

                button = new JButton(); // Multiplication sign
                button.setText("+");
                button.setBackground(new Color(13, 13, 13));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
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
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
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
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
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
                button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
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
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
        keyboardPanel.add(btn);

        btn = new JButton();
        btn.setText("0");
        btn.setBackground(new Color(6, 6, 6));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.BOLD, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
        keyboardPanel.add(btn);

        btn = new JButton();
        btn.setText(".");
        btn.setBackground(new Color(13, 13, 13));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
        keyboardPanel.add(btn);

        button = new JButton();
        button.setText("=");
        button.setPreferredSize(new Dimension(50, 75));
        button.setBackground(new Color(13, 13, 13));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Santa Fe LET", Font.PLAIN, 28));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(31, 31,31), 2));
        keyboardPanel.add(button);

        // Text Area

        JTextPane display = new JTextPane();
        display.setEditable(false);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setFont(new Font("Santa Fe LET", Font.PLAIN, 54));
        display.setText("123456789-+%");
        display.setBackground(new Color(31, 31, 31));
        display.setForeground(Color.white);
        StyledDocument style = display.getStyledDocument();
        SimpleAttributeSet align= new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.);
        style.setParagraphAttributes(0, style.getLength(), align, false);
        mainPanel.add(display, BorderLayout.CENTER);

        // Label

        JLabel label = new JLabel();
        label.setText("MY Calculator");
        label.setForeground(Color.white);
        label.setFont(new Font("Santa Fe LET", Font.BOLD, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(new Color(31, 31, 31));
        mainPanel.add(label, BorderLayout.NORTH);


        calcFrame.setVisible(true);
    }

}
