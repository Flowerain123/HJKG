package com.java.demo;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JFrame;


public class GameWin extends JFrame{
    static int state = 1;//程序状态
    Pic pic = new Pic(this);//背景组件
    Line line = new Line(this);//
    Image backImage;    //画布
    Timer timer;
    public GameWin(){//窗体初始化
//        this.setSize(640, 544);
        this.setBounds(500, 500, 640, 544);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Found you");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){//添加点击事件//具有中断性质
                super.mouseClicked(e);
                switch(state){
                    case 1://游戏中点击
                        if(e.getButton()==1 && line.mode==1){
                            line.mode = 2;
                        }
                        if(e.getButton()==3 && line.mode==4){
                            Pic.flashNum--;
                            if(Pic.flashNum > -1){
                                Pic.flashFlag = true;
                            }
                            else{
                                Pic.flashNum = 0;
                            }
                         }
                        break;
                    case 2://结束界面点击
                        if(e.getButton()==1){
                            state = 1;
                            pic.rePic();
                            line.reLine();
                        }
                        break;
                    case 3:
                        if(e.getButton()==1){
                            state = 1;
                            pic.rePic();
                            line.reLine();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revalidate();
                repaint();
                nextLevel();
            }
        });
        timer.start();
    }

    public void paint(Graphics g){//绘制窗体内元素
        if(GameWin.state == 1){
            backImage = this.createImage(640,544);//背景
            Graphics gImage = backImage.getGraphics();
            pic.painSelf(gImage);
            for(Object obj:pic.objList){//画心
                obj.painSelf(gImage);
            }
            line.painSelf(gImage);
            g.drawImage(backImage, 0, 0, null);
        }
    }

    public void nextLevel(){//判断游戏进行状态
        if(Pic.countScore >= pic.goalScore && !(pic.gameTime()) && (Pic.goalCatchNumber >= 4) || Pic.countScore-pic.goalScore>=10){//时间内 达成目标分数 满4个
            Pic.goalCatchNumber = 0;//重制目标个数
            Pic.level++;
            Pic.flashNum++;//小关卡奖励flash
            dispose();
            timer.stop();
            GameWin win1 = new GameWin();
            pic.startTime = new Date();
        }
        else if(pic.gameTime() && Pic.countScore<pic.goalScore && state == 1){
            state = 2;
            dispose();
            timer.stop();
            GameWin win1 = new GameWin();
        }
        if(Pic.level >= 10&& state == 1){
            state = 3;
            dispose();
            timer.stop();
            GameWin win1 = new GameWin();
        }
    }

}

