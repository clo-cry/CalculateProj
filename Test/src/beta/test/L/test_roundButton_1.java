package beta.test.L;

import javax.swing.*;

public class test_roundButton_1 extends JFrame {
    public void launch() {
        setSize(640,480);
        setLayout(null);
        RoundButton btn = new RoundButton("1",90);
        btn.setText("ghasd");
        btn.setBounds(100,100,80,80);
        btn.setBorderPainted(false);
        add(btn);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new test_roundButton_1().launch();
    }
}
