package beta.test.L;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class test_font extends JFrame {
    public JPanel panel;
    private int s_w = 640, s_h = 480;
    private BufferedImage screenBuffer;
    public test_font() {
        panel = (JPanel) getContentPane();
        panel.setPreferredSize(new Dimension(s_w, s_h));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
//
//        URL imageURL = this.getClass().getResource("Oyama.png");
//
//        try {
//            screenBuffer = ImageIO.read(imageURL);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        BufferedImage bi = new BufferedImage(s_w,s_h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(Color.DARK_GRAY);
        g2d.fillRect(0,0,s_w,s_h);
        g2d.setPaint(Color.GREEN);
        g2d.drawOval(100,100,50,50);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        try {
            g2d.setFont(loadFont());
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2d.drawString("4:04 PM",300,200);


        while (true)
            panel.getGraphics().drawImage(bi,0,0,this);

    }

    public Font loadFont() throws FontFormatException,IOException{
        String fontFileName = "font.TTF";
        InputStream is = this.getClass().getResourceAsStream(fontFileName);
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        Font fontBase = font.deriveFont(Font.PLAIN,50);
        return fontBase;
    }

    public static void main(String[] args) {
//        System.out.println("The quick brown fox jumps over the lazy dog.");
        new test_font();
    }
}
