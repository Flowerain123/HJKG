package com.java.demo;

import java.awt.event.*;
import javax.swing.*;

public class StartWin extends JFrame {
    JButton button;
    Listener listener = new Listener();
    JLabel background;

    ImageIcon backgroundMax = new ImageIcon("images/backgroundMax.jpg");
    public StartWin() {

        init();
        this.setBounds(100,100,640,544);
        this.setTitle("nmd");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        background = new JLabel(backgroundMax);
        background.setBounds(0,0,backgroundMax.getIconWidth(),backgroundMax.getIconHeight());
        add(background);
    }

    void init() {
        setLayout(null);
        button = new JButton("开始游戏");
//        button.setPreferredSize(new Dimension(200, 50));
        button.setBounds(170,200,300,100);//想要使用此方法不能使用布局管理器
        button.addActionListener(listener);
        add(button);
    }

    public class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new GameWin().setVisible(true);
        }
    }
}
