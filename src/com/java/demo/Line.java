package com.java.demo;
import java.awt.*;
public class Line {
    int x = 320;
    int y = 150;
    int endx = 320;
    int endy = 444;

    double length = 50;
    double minLength = 50;
    double maxLength = 390;
    double n = 0;
    int distence = 1;//摆动方向
    int mode = 1;//钩爪状态
    Image heart = Toolkit.getDefaultToolkit().getImage("images/point.png");

    GameWin frame;

    void lineSet(Graphics g){//终点调整绘制
        endx = (int) (x+length*Math.cos(n*Math.PI));
        endy = (int) (y+length*Math.sin(n*Math.PI));
        g.setColor(Color.RED);
        g.drawLine(x-1, y, endx-1, endy);
        g.drawLine(x, y, endx, endy);
        g.drawLine(x+1, y, endx+1, endy);
        g.drawImage(heart, endx-9, endy-8, null);//箭头心心偏移量
    }

    Line(GameWin frame){this.frame = frame;}

    void logic(){//判读抓取
        for(Object obj:this.frame.pic.objList){
            if(endx>obj.x+10 && endx<obj.x+obj.width//心心抓取判断偏移量
                && endy>obj.y && endy<obj.y+obj.hight){
            obj.catchFlag = true;
            mode = 4;
        }
        }

    }

    public void painSelf(Graphics g){
        
        switch (mode){
            case 1://摇摆状态
                if(n<0.1){distence = 1;}
                else if(n>0.9){distence = -1;}
                n = n+0.003*distence;
                lineSet(g);
                break;
            case 2://伸长状态
                if(length < maxLength){
                    length = length+2;
                    lineSet(g);
                    logic();//只有在伸长状态下才应该去检测是否碰撞
                }else{mode = 3;}
               
                break;
            case 3://无抓取缩回状态
                if(length>minLength){
                    length = length-6;
                    lineSet(g);
                }else{
                    mode = 1;
                }
                break;
            case 4://抓取到心心
                if(length>minLength){
                    for(Object obj:this.frame.pic.objList){
                        if(obj.catchFlag){//抓到
                            length = length-obj.m;//
                            lineSet(g);
                            obj.x = endx-obj.getWidth();
                            obj.y = endy-obj.getHight()+10; 
                            if(Pic.flashFlag){//触发闪电判断
                                if(obj.type == 1){//闪电加速
                                    obj.m = 8;
                                    Pic.flashFlag = false;
                                }
                                if(obj.type == 2){//闪电爆炸
                                    obj.x = -200;
                                    obj.y = -200;
                                    obj.catchFlag = false;//取消抓取状态
                                    Pic.flashFlag = false;//取消闪电状态
                                    Pic.countScore += 1;
                                    mode = 3;
                                }
                            }

                            if(length<=minLength){//抓到终点
                                Pic.countScore += obj.count;
                                obj.x = -150;
                                obj.y = -150;
                                obj.catchFlag = false;
                                Pic.goalCatchNumber++;
                                mode = 1;
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
    void reLine(){
        n = 0;
        length = 50;
    }

}
