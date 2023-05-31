package beta.test.L;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class test_font extends JFrame {
    public JPanel panel;
    private int s_w = 640, s_h = 480;
    private BufferedImage screenBuffer;
    public test_font() {
        panel = (JPanel) getContentPane();
        panel.setPreferredSize(new Dimension(s_w, s_h));
        pack();

        Graphics2D g2d = (Graphics2D) screenBuffer.createGraphics();
        g2d.setPaint(Color.CYAN);
        g2d.fillRect(0,0,s_w,s_h);

        panel.getGraphics().drawImage(screenBuffer,0,0,this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("The quick brown fox jumps over the lazy dog.");
        new test_font();
    }
}
