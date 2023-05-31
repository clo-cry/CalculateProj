package beta.test.L;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Calculator_0_1_1 extends JFrame implements MouseListener{
    private JTextField textField;

    public Calculator_0_1_1() {
        super("Calculator");

        /**
         * 在下面的这个方法里绘制背景
         * 现在是创建了一个灰色填充
         */
        setContentPane(new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.DARK_GRAY);
                g.fillRect(0,0,250,250);
            }
        });

        // 创建文本字段
        textField = new JTextField(10);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        add(buttonPanel, BorderLayout.CENTER);

        // 创建数字和操作符按钮
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addMouseListener(this);
            buttonPanel.add(button);
        }

//	        this.setContentPane(new JPanel() {
//	        	protected void paintComponent(Graphics g) {
//	        		g.setColor(Color.DARK_GRAY);
//	        		g.fillRect(0, 0, 100, 100);
//	        	};
//	        } );
//
        // 设置窗口大小和关闭操作
        setSize(250, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void mousePressed(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        String label = button.getText();
        if (label.equals("=")) {
            // 计算结果并显示
            String expression = textField.getText();
            try {
                double result = evaluateExpression(expression);
                textField.setText(Double.toString(result));
            } catch (IllegalArgumentException ex) {
                textField.setText("Error");
            }
        }
        else if (label.equals("C")) {
            // 清空文本字段
            textField.setText("");
        }else {
            // 添加数字或操作符到文本字段
            textField.setText(textField.getText() + label);
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    /**
     * 计算表达式的值
     * @param expression 要计算的表达式
     * @return 表达式的值
     * @throws IllegalArgumentException 如果表达式不是有效的算术表达式
     */
    private static double evaluateExpression(String expression) {
        // 使用ScriptEngineManager计算表达式
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            Object result = engine.eval(expression);
            return Double.parseDouble(result.toString());
        } catch (ScriptException ex) {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    public static void main(String[] args) {
        new Calculator_0_1_1();
    }

}