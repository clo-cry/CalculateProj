package beta.test.L;

import com.sun.awt.AWTUtilities;

import javax.swing.*;

public class test_frame_alpha extends JFrame {

    public test_frame_alpha() {
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // 去除窗口边框
        setVisible(true);

        // 创建一个透明背景
        AWTUtilities.setWindowOpaque(this, false);
    }

    public static void main(String[] args) {
        new test_frame_alpha();
    }
}
