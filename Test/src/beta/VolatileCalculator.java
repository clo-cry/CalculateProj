package beta;

import beta.test.L.RoundButton;
import beta.test.L.simpleSoundPlayer;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.Border;
import java.lang.Math;

public class VolatileCalculator {
    public static boolean needRead = false;
    private volatile boolean turn = false;
    private byte count = 0;
    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 70;
    private static final int MARGIN_X = 28;
    private static final int MARGIN_Y = 90;

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
        JFrame.setDefaultLookAndFeelDecorated(false);
        frame = new JFrame("Calculator");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        frame.setLocationRelativeTo(null); // Move window to center

        frame.setUndecorated(true);
        RoundRectangle2D shape = new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 40, 40);
        frame.setShape(shape);

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
                g.drawRect(x, y, x + width, y + height);
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
            if (count != 0)
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String fileName = "归零.wav";
                        String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                        simpleSoundPlayer player = new simpleSoundPlayer();
                        player.play(thePath);
                    }
                }).start();
            needRead = false;
            count = 1;
            inText.setText("0");
            opt = ' ';
            val = 0;
        });
        btnC.doClick();

        JButton btnEsc = initBtn("x",350,20,e -> {
            System.exit(0);
        });
        btnEsc.setSize(30,30);
        btnEsc.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        btnEsc.setText("×");
        btnEsc.setMargin(new Insets(0,0,0,0));


        btnBack = initBtn("<-", x[1], y[1], event -> {
            repaintFont();
            String str = inText.getText();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "清除.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "取余.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "除.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "7.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "8.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "9.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "乘.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "4.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "5.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "6.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "减.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "1.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "2.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "3.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "加.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "点.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "0.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            needRead = false;
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String fileName = "等于.wav";
                    String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                    simpleSoundPlayer player = new simpleSoundPlayer();
                    player.play(thePath);
                }
            }).start();
            turn = true;
            needRead = true;
        });
        btnEqual.setSize(2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (turn) {
                        ReadSign.read(inText.getText());
                        turn = false;
                    }
                }
            }
        }).start();


        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close button clicked? = End The process
        frame.setVisible(true);
    }


    private JButton initBtn(String label, int x, int y, ActionListener event) {
        JButton btn = new RoundButton(label, 80);
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