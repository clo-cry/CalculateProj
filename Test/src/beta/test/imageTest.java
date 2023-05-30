package beta.test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class imageTest extends JFrame {
    public imageTest() {
        JPanel panel = (JPanel) getContentPane();
        this.setContentPane(panel);
        panel.setPreferredSize(new Dimension(640,480));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        URL imageURL = this.getClass().getResource("Oyama.png");
        BufferedImage bi;
        try {
            bi = ImageIO.read(imageURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Graphics2D g2d = bi.createGraphics();
        g2d.drawRect(10,10,50,50);

        panel.getGraphics().drawImage(bi, 0, 0, this);

//        JPanel jPanel = new JPanel();
//        jPanel.setBounds(0,0,640,480);
//        jPanel.setBackground(Color.blue);

        JButton btn = new JButton("1");
        btn.setBounds(100,100,50,50);
//        btn.setBackground(Color.black);
        btn.setContentAreaFilled(false);
        this.add(btn);
    }

    public BufferedImage readImageFile(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new imageTest();
    }
}
