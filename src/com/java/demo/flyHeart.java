package com.java.demo;

import java.awt.Toolkit;

public class flyHeart extends Object {
    flyHeart(){
        this.x = (int)(Math.random()*550);
        this.y = (int)(Math.random()*280+200);
        this.width = 79;
        this.hight = 45;
        this.m = 2;

        this.count = 3;
        this.type = 1;
        this.img = Toolkit.getDefaultToolkit().getImage("images/flyHeart2.png");
    }
}

class miniFlyHeart extends flyHeart {
    miniFlyHeart(){
        this.width = 60;
        this.hight = 34;
        this.m = 3;

        this.count = 1;
        this.img = Toolkit.getDefaultToolkit().getImage("images/flyHeart3.png");
    }
}

class plusFlyHeart extends flyHeart {
    plusFlyHeart(){
        this.width = 154;
        this.hight = 129;
        this.m = 1;

        this.count = 8;
        this.img = Toolkit.getDefaultToolkit().getImage("images/flyHeart1.png");
    }
}

