package com.java.demo;

import java.awt.Toolkit;

public class Heart extends Object {//640 544
    Heart(){
        this.x = (int)(Math.random()*550);
        this.y = (int)(Math.random()*280+200);
        this.width = 80;
        this.hight = 73;
        this.m = 2;
        this.count = 3;
        this.type = 1;
        this.img = Toolkit.getDefaultToolkit().getImage("images/heart2.png");
    }
}

class miniHeart extends Heart{
    miniHeart(){
        this.width = 70;
        this.hight = 40;
        this.m = 3;
        this.count = 2;
        this.type = 1;
        this.img = Toolkit.getDefaultToolkit().getImage("images/heart3.png");
    }
}

class plusHeart extends Heart{
    plusHeart(){

        this.width = 150;
        this.hight = 138;
        this.m = 1;

        this.count = 1;
        this.type = 2;
        this.img = Toolkit.getDefaultToolkit().getImage("images/heart1.png");
    }
}
