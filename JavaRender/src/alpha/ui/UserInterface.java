package alpha.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {
    public void launch() {
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        JButton btn = new JButton("原神~启动！");
        btn.setFont(new Font("微软雅黑", 0, 30));
        getContentPane().add(btn, BorderLayout.SOUTH);
        ActionListener al = e -> System.out.println("启动！");
        btn.addActionListener(al);

        MP mp = new MP();
        getContentPane().add(mp,BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new UserInterface().launch();
    }

    class MP extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            setBackground(Color.DARK_GRAY);
        }
    }

}
