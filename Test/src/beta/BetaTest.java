package beta;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BetaTest extends JFrame {
    private static final int s_w = 640, s_h = 480;
    private static JPanel panel;
    private static BufferedImage screenBuffer;

    public BetaTest() {
        panel = (JPanel) getContentPane();
        panel.setPreferredSize(new Dimension(s_w, s_h));
        panel.setMinimumSize(new Dimension(s_w, s_h));
        panel.setLayout(null);

        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        screenBuffer = new BufferedImage(s_w,s_h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) screenBuffer.getGraphics();
        BufferedImage image;
        try {
            image = ImageIO.read(new File("img/Oyama.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        screenBuffer = image;

        panel.getGraphics().drawImage(screenBuffer,0,0,this);
    }

    public static void main(String[] args) {
        new BetaTest();
    }
}
