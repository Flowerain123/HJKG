package com.java.demo;

import java.awt.*;
public class Object {
    int x;
    int y;
    int width;
    int hight;
    int m;
    int count;
    boolean catchFlag = false;
    int type;
    Image img;
    void painSelf(Graphics g){
        g.drawImage(img, x, y, null);
    }
    int getWidth(){
        return (int)(this.width/2);
    }
    int getHight(){
        return (int)(this.hight/2);
    }
    public Rectangle getRec(){
        return new Rectangle(x,y,width,hight);
    }
}
