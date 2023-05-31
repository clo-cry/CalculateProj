package beta.test.D;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
class Moon{
    public static void main(String args[]) {
        JFrame f = new JFrame("zzz");
        f.setSize(400, 500);
        f.setLayout(null);

        f.setLayout(new BorderLayout());
        f.add(new JTextField());
        JPanel p=new JPanel();
        p.setLayout(new GridLayout(5,4,5,5));
        f.add(p,"Center");
        f.add(new JTextField(),"North");
        String[] str= {"C","<-","%","/","7","8","9","*","4","5","6","-",
        "1","2","3","+",".","0","="};
        for(int i=0;i<19;i++){
            p.add(new JButton(""+str[i]));
        }



        f.setVisible(true);
    }}

