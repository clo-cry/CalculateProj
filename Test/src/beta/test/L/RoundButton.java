package beta.test.L;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundButton extends JButton {
    private final int radius;
    private Shape shape;
    public RoundButton(String label, int radius){
        super(label);
        this.radius = radius;
        setContentAreaFilled(false);
    }

    @Override
    public void setBounds(int x, int y, int width, int height){
        super.setBounds(x, y, width, height);
        shape = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, radius, radius);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if(getModel().isArmed()){
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.GRAY);
        }
        ((Graphics2D) g).fill(shape);
        super.paintComponent(g);
    }
//
    @Override
    protected void paintBorder(Graphics g){
        g.setColor(getForeground());
//        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
        ((Graphics2D)g).draw(shape);
    }

    @Override
    public boolean contains(int x, int y){
        return shape != null && shape.contains(x, y);
    }
}


