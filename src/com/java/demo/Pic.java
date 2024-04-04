package com.java.demo;

import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.util.List;
public class Pic {
    static int goalCatchNumber = 0;
    static int countScore = 0;
    static int flashNum = 3;
    static boolean flashFlag = false;
    static int level =  1;
    int goalScore = 8*level;
    Date startTime = new Date();
    Date endTime = new Date();
    List<Object> objList = new ArrayList<>();    //心心组
    GameWin frame;
    Image background = Toolkit.getDefaultToolkit().getImage("images/background.jpg");
    Image sky = Toolkit.getDefaultToolkit().getImage("images/sky.jpg");
    Image baby = Toolkit.getDefaultToolkit().getImage("images/baby.png");
    Image flash = Toolkit.getDefaultToolkit().getImage("images/flash.png");
    Pic(GameWin frame){
        this.frame = frame;
        boolean creatFlag = true;
        for(int i=0;i<3;i++){
            Heart heart;
            double random = Math.random();
            if(random<0.3)      {heart = new plusHeart();}
            else if(random>0.7) {heart = new miniHeart();}
            else                {heart = new Heart();}
            for(Object obj:objList){
                if(heart.getRec().intersects(obj.getRec())){
                    creatFlag = false;
                }
            }
            if(creatFlag){objList.add(heart);}
            else{
                creatFlag = true;
                i--;
            }
        }
        for(int i=0;i<3;i++){
            flyHeart flyHeart;
            double random = Math.random();
            if(random<0.3)      {flyHeart = new miniFlyHeart();}
            else if(random>0.7) {flyHeart = new plusFlyHeart();}
            else                {flyHeart = new flyHeart();}
            for(Object obj:objList){
                if(flyHeart.getRec().intersects(obj.getRec())){
                    creatFlag = false;
                }
            }
            if(creatFlag){objList.add(flyHeart);}
            else{
                creatFlag = true;
                i--;
            }
        }
    }
    void painSelf(Graphics g){
        switch(GameWin.state){
            case 1://游戏界面
                g.drawImage(sky, 0, 0, null);
                g.drawImage(background, 0, 200, null);
                g.drawImage(baby, 290, 85, null);
                g.drawImage(flash, 30, 65, null);
//                drawWord(g, 15, Color.black, "开始时间:", 0, 140,startTime.getTime());
//                drawWord(g, 15, Color.black, "结束时间:", 0, 160,endTime.getTime());
                drawWord(g, 15, Color.black, "抓取数  :", 0, 140,Pic.goalCatchNumber);
                drawWord(g, 15, Color.black, "level", 510, 80, level);
                drawWord(g, 15, Color.black, "* ", 100, 100,flashNum);
                drawWord(g, 15, Color.black, "积分：", 510, 100,countScore);
                drawWord(g, 15, Color.black, "目标积分：", 510, 120,goalScore);
                endTime = new Date();
                long time = 20-(endTime.getTime()-startTime.getTime())/1000;
                drawWord(g, 15, Color.black, "时间:", 510, 60,(time>0?time:0));
                break;
            case 2://失败界面
                drawWord(g, 80, Color.black, "LOSED!", 200, 270,-1);
                drawWord(g, 30, Color.black, "积分：", 200, 350,countScore);
//                drawWord(g, 15, Color.black, "state:", 0, 160,Win.state);
                break;
            case 3://通关界面
                drawWord(g, 80, Color.red, "WINNER!", 200, 270,-1);
                drawWord(g, 30, Color.red, "积分：", 200, 350,countScore);
                break;
            default:
                break;
        }
    }
    //绘制字符串
    public static void drawWord(Graphics g,int size,Color color,String str,int x,int y,long num){//字符串加数据初始化
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        if(num != -1){
            g.drawString(str+num, x, y);
        }else{
            g.drawString(str, x, y);
        }
    }
    //true timeover  false timeremind
    boolean gameTime(){//获取计时时间
        long tim = (endTime.getTime() - startTime.getTime())/1000;
        if(tim > 20){return true;}
        return false;
    }
    void rePic(){//初始化游戏数据
        goalCatchNumber = 0;
        level = 1;
        goalScore = level*8;
        countScore = 0;
        flashNum = 3;
        flashFlag = false;
    }
}
