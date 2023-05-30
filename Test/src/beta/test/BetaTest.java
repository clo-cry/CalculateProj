package beta.test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class BetaTest extends JFrame {
    public static int[] screen;
    public static JPanel panel;
    public static BufferedImage screenBuffer;

    public BetaTest() {

        panel = (JPanel) getContentPane();
        panel.setPreferredSize(new Dimension(640, 480));

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        screenBuffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        DataBuffer dest = screenBuffer.getRaster().getDataBuffer();
        screen = ((DataBufferInt) dest).getData();

        int sky_r = 160, sky_g = 210, sky_b = 240;
        while (true) {
            Arrays.fill(screen, (sky_r << 16) | (sky_g << 8) | sky_b);
            panel.getGraphics().drawImage(screenBuffer, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        new BetaTest();
    }
}
