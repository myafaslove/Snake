package com.myafaslove;

import java.util.Random;

/**
 * 节点类：每一条🐍是由若干个节点组成的，每一个节点有横纵坐标来确定位置
 */
public class Node {
    private int x;
    private int y;

    public Node() {

    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //随机生成位置，为后期食物做准备
    public void random() {
        //创建random对象
        Random r = new Random();
        //生成横纵坐标
        this.x = r.nextInt(40);
        this.y = r.nextInt(40);


    }
}
