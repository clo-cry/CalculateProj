package beta;

import beta.test.L.RoundButton;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.Border;
import java.lang.Math;

public class VolatileCalculator {

    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 70;
    private static final int MARGIN_X = 22;
    private static final int MARGIN_Y = 58;

    private JFrame frame; // Main window

    private final JTextField inText; // Input
    private JButton btnC, btnBack, btnMod, btnDiv, btnMul, btnSub, btnAdd,
            btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnPoint, btnEqual;

    private char opt = ' '; // Save the operator
    private boolean go = true; // For calculate with Opt != (=)
    private boolean addWrite = true; // Connect numbers in display
    private double val = 0; // Save the value typed for calculation


    public VolatileCalculator() {
        frame = new JFrame("Calculator");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null); // Move window to center

        frame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(Color.DARK_GRAY);
                g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            }
        });

        int[] x = {MARGIN_X, MARGIN_X + 90, MARGIN_X + 180, MARGIN_X + 270, MARGIN_X + 360};
        int[] y = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 180, MARGIN_Y + 260, MARGIN_Y + 340, MARGIN_Y + 420};

        inText = new JTextField("0");
        inText.setBounds(x[0], y[0], 350, 70);
        inText.setForeground(new Color(11, 61, 2));
        Insets insets = inText.getInsets();
        inText.setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.GRAY);
                g.drawRect(x,y,x+width,y+height);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return insets;
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        inText.setEditable(false);
        inText.setBackground(new Color(168, 203, 74));
        inText.setFont(new Font("Comic Sans MS", Font.PLAIN, 33));
        frame.add(inText);

        btnC = initBtn("C", x[0], y[1], event -> {
            repaintFont();
            inText.setText("0");
            opt = ' ';
            val = 0;
        });

        btnC.doClick();

        btnBack = initBtn("<-", x[1], y[1], event -> {
            repaintFont();
            String str = inText.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inText.setText("0");
            } else {
                inText.setText(str2.toString());
            }
        });

        btnMod = initBtn("%", x[2], y[1], event -> {
            repaintFont();
            if (Pattern.matches("(-?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("-?[\\d]+[.]0*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '%';
                    go = false;
                    addWrite = false;
                }
        });

        btnDiv = initBtn("/", x[3], y[1], event -> {
            repaintFont();
            if (Pattern.matches("(-?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("-?[\\d]+[.]0*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '/';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '/';
                }
        });

        btn7 = initBtn("7", x[0], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("7");
                } else {
                    inText.setText(inText.getText() + "7");
                }
            } else {
                inText.setText("7");
                addWrite = true;
            }
            go = true;
        });

        btn8 = initBtn("8", x[1], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("8");
                } else {
                    inText.setText(inText.getText() + "8");
                }
            } else {
                inText.setText("8");
                addWrite = true;
            }
            go = true;
        });

        btn9 = initBtn("9", x[2], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("9");
                } else {
                    inText.setText(inText.getText() + "9");
                }
            } else {
                inText.setText("9");
                addWrite = true;
            }
            go = true;
        });

        btnMul = initBtn("*", x[3], y[2], event -> {
            repaintFont();
            if (Pattern.matches("(-?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("-?[\\d]+[.]0*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '*';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '*';
                }
        });

        btn4 = initBtn("4", x[0], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("4");
                } else {
                    inText.setText(inText.getText() + "4");
                }
            } else {
                inText.setText("4");
                addWrite = true;
            }
            go = true;
        });

        btn5 = initBtn("5", x[1], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("5");
                } else {
                    inText.setText(inText.getText() + "5");
                }
            } else {
                inText.setText("5");
                addWrite = true;
            }
            go = true;
        });

        btn6 = initBtn("6", x[2], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("6");
                } else {
                    inText.setText(inText.getText() + "6");
                }
            } else {
                inText.setText("6");
                addWrite = true;
            }
            go = true;
        });

        btnSub = initBtn("-", x[3], y[3], event -> {
            repaintFont();
            if (Pattern.matches("(-?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("-?[\\d]+[.]0*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }

                    opt = '-';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '-';
                }
        });

        btn1 = initBtn("1", x[0], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("1");
                } else {
                    inText.setText(inText.getText() + "1");
                }
            } else {
                inText.setText("1");
                addWrite = true;
            }
            go = true;
        });

        btn2 = initBtn("2", x[1], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("2");
                } else {
                    inText.setText(inText.getText() + "2");
                }
            } else {
                inText.setText("2");
                addWrite = true;
            }
            go = true;
        });

        btn3 = initBtn("3", x[2], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("3");
                } else {
                    inText.setText(inText.getText() + "3");
                }
            } else {
                inText.setText("3");
                addWrite = true;
            }
            go = true;
        });

        btnAdd = initBtn("+", x[3], y[4], event -> {
            repaintFont();
            if (Pattern.matches("(-?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("-?[\\d]+[.]0*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '+';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '+';
                }
        });

        btnPoint = initBtn(".", x[0], y[5], event -> {
            repaintFont();
            if (addWrite) {
                if (!inText.getText().contains(".")) {
                    inText.setText(inText.getText() + ".");
                }
            } else {
                inText.setText("0.");
                addWrite = true;
            }
            go = true;
        });

        btn0 = initBtn("0", x[1], y[5], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("0*", inText.getText())) {
                    inText.setText("0");
                } else {
                    inText.setText(inText.getText() + "0");
                }
            } else {
                inText.setText("0");
                addWrite = true;
            }
            go = true;
        });

        btnEqual = initBtn("=", x[2], y[5], event -> {
            if (Pattern.matches("(-?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("-?[\\d]+[.]0*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '=';
                    addWrite = false;
                }
        });
        btnEqual.setSize(2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);

        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close button clicked? = End The process
        frame.setVisible(true);
    }

    private JComboBox<String> initCombo(String[] items, int x, int y, String toolTip, Consumer consumerEvent) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(toolTip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        combo.addItemListener(consumerEvent::accept);
        frame.add(combo);

        return combo;
    }

    private JButton initBtn(String label, int x, int y, ActionListener event) {
        JButton btn = new RoundButton(label,80);
        btn.setForeground(new Color(77, 1, 16));
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(event);
        btn.setFocusable(false);
        frame.add(btn);

        return btn;
    }

    public double calc(double x, String input, char opt) {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
        double y = Double.parseDouble(input);
        switch (opt) {
            case '+' -> {
                return x + y;
            }
            case '-' -> {
                return x - y;
            }
            case '*' -> {
                return x * y;
            }
            case '/' -> {
                return x / y;
            }
            case '%' -> {
                return x % y;
            }
            case '^' -> {
                return Math.pow(x, y);
            }
            default -> {
                inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
                return y;
            }
        }
    }

    private void repaintFont() {
        try {
            inText.setFont(loadFont());
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Font loadFont() throws FontFormatException, IOException {
        String fontFileName = "font.TTF";
        InputStream is = this.getClass().getResourceAsStream(fontFileName);
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        Font fontBase = font.deriveFont(Font.PLAIN, 50);
        return fontBase;
    }

    public static void main(String[] args) {

        new VolatileCalculator();
    }
}