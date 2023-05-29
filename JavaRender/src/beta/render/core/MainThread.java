package beta.render.core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class MainThread extends JFrame {
    public static int screen_w = 640;
    public static int screen_h = 480;
    public static int h_screen_w = screen_w >> 1;
    public static int h_screen_h = screen_h >> 1;

    public static int frameIndex;
    public static int frameInterval = 33;
    public static int sleepTime;
    public static int framePerSecond;
    public static long lastDraw;
    public static double thisTime, lastTime;

    public static int[] screen;
    public static JPanel panel;
    public static BufferedImage screenBuffer;

    public static void main(String[] args) {
        new MainThread();
    }

    public MainThread() {
        panel = (JPanel) getContentPane();
        panel.setPreferredSize(new Dimension(screen_w, screen_h));
        panel.setMinimumSize(new Dimension(screen_w, screen_h));
        panel.setLayout(null);

        setResizable(false);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        screenBuffer = new BufferedImage(screen_w, screen_h, BufferedImage.TYPE_INT_RGB);
        DataBuffer dest = screenBuffer.getRaster().getDataBuffer();
        screen = ((DataBufferInt) dest).getData();


        int sky_r = 160, sky_g = 210, sky_b = 240;
        int miku_r = 50, miku_g = 180, miku_b = 170;
        while (true) {
//            Arrays.fill(screen, (sky_r << 16) | (sky_g << 8) | sky_b);
            for (int i = 0; i < screen_w; i++) {
                int p = (i + frameIndex * 8) % screen_w;
                for (int j = 0; j < screen_h; j++) {
                    float t1 = Math.abs((float) (h_screen_w - p) / h_screen_w);
                    float t2 = 1f - t1;
                    int r = (int) (sky_r * t1 + miku_r * t2);
                    int g = (int) (sky_g * t1 + miku_g * t2);
                    int b = (int) (sky_b * t1 + miku_b * t2);
                    screen[i + j * screen_w] = (r << 16) | (g << 8) | b;
                }
            }

            frameIndex++;
            if (frameIndex % 30 == 0) {
                double thisTime = System.currentTimeMillis();
                framePerSecond = (int) (1000 / ((thisTime - lastTime) / 30));
                lastTime = thisTime;
            }
            sleepTime = 0;
            while (System.currentTimeMillis() - lastDraw < frameInterval) {
                try {
                    Thread.sleep(1);
                    sleepTime++;
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            lastDraw = System.currentTimeMillis();

            Graphics2D g2d = (Graphics2D) screenBuffer.getGraphics();
            g2d.setColor(Color.BLACK);
            g2d.drawString("FPS： " + framePerSecond + "  " + "Thread Sleep：" + sleepTime + "ms ", 5, 15);

            panel.getGraphics().drawImage(screenBuffer, 0, 0, this);
        }
    }
}
