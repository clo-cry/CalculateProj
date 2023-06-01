package beta.test.L;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class imageTest_2 extends JFrame {
    public imageTest_2() {
        setContentPane(new JPanel() {
            URL url = getClass().getResource("Oyama.png");
            BufferedImage im;

            {
                try {
                    im = ImageIO.read(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(im, 0, 0, this);
            }
        });
        setLayout(null);
        JButton jButton = new JButton();

        getContentPane().add(jButton);
        jButton.setBounds(100, 100, 180, 100);
//        jButton.setFont(new Font("微软雅黑", 1, 30));

        GradientPaint gp = new GradientPaint(0, 0, Color.CYAN, 50, 30, Color.PINK, true);
        BasicButtonUI basicButtonUI = new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
//                super.paint(g, c);
                AbstractButton btn = (AbstractButton) c;
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setPaint(gp);
//                Font font = getFont();
                Font font = new Font("微软雅黑", 1, 30);
                FontRenderContext frc = g2d.getFontRenderContext();
                TextLayout tl = new TextLayout(btn.getText(), font, frc);
                Shape shape = tl.getOutline(null);
                Rectangle rectangle = shape.getBounds();
                g2d.translate((btn.getWidth() - rectangle.width) / 2 - rectangle.x, (btn.getHeight() - rectangle.height) / 2 - rectangle.y);
                g2d.fill(shape);
            }
        };
        jButton.setUI(basicButtonUI);
        jButton.setText("March 7");
//        jButton.setBorderPainted(false);
//        jButton.setBorder(new Border() {
//            int x,y,w,h;
//            @Override
//            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//                g.drawOval(x,y,width,height);
//                this.x = x;
//                this.y = y;
//                this.w = width;
//                this.h = height;
//            }
//
//            @Override
//            public Insets getBorderInsets(Component c) {
//                return new Insets(y,x,y+h,x+w);
//            }
//
//            @Override
//            public boolean isBorderOpaque() {
//                return false;
//            }
//        });
        jButton.setFocusPainted(false);
        jButton.setContentAreaFilled(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 480);
        setVisible(true);
    }

    public static void main(String[] args) {
        new imageTest_2();
    }
}
