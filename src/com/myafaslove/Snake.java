package com.myafaslove;

import java.util.LinkedList;

/**
 * Snake类表示蛇
 * 一条蛇有多个节点使用LinkedList集合存储Node节点，蛇出生时有6个节点。
 */
public class Snake {
    //蛇的身体
    private LinkedList<Node> body;
    //蛇的运动方向,默认向左
    private Direction direction = Direction.LEFT;
    //蛇是否活着
    private boolean isLiving = true;

    //构造方法，在创建Snake对象时执行
    public Snake() {
        //初始化蛇身体
        initSnake();
    }

    //初始化蛇身体
    private void initSnake() {
        //创建集合
        body = new LinkedList<>();
        //创建6个节点放到窗口中
        body.add(new Node(16, 20));
        body.add(new Node(17, 20));
        body.add(new Node(18, 20));
        body.add(new Node(19, 20));
        body.add(new Node(20, 20));
        body.add(new Node(21, 20));
        body.add(new Node(22, 20));
        body.add(new Node(23, 20));
        body.add(new Node(24, 20));
        body.add(new Node(25, 20));
    }

    //蛇会沿着蛇头的方向移动
    //控制蛇移动
    //在蛇的运动方向加一个节点，蛇尾减一个节点
    public void move() {

        if (isLiving) {
            //获取蛇头
            Node head = body.getFirst();
            switch (direction) {
                case UP:
                    //在蛇头的上面添加一个节点
                    body.addFirst(new Node(head.getX(), head.getY() - 1));
                    break;
                case DOWN:
                    body.addFirst(new Node(head.getX(), head.getY() + 1));
                    break;
                case LEFT:
                    body.addFirst(new Node(head.getX() - 1, head.getY()));
                    break;
                case RIGHT:
                    body.addFirst(new Node(head.getX() + 1, head.getY()));
                    break;
            }
            //蛇尾减一个节点
            body.removeLast();

            //判断蛇是否撞墙
            head = body.getFirst();
            if (head.getX() < 0 || head.getY() < 0 || head.getX() >= 40 || head.getY() >= 40) {
                isLiving = false;
            }

            //判断蛇是否撞到自己
            for (int i = 1; i < body.size(); i++) {
                Node node = body.get(i);
                if (head.getX() == node.getX() && head.getY() == node.getY()) {
                    isLiving = false;
                    break;
                }
            }
        }
    }

    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //吃食物   沿着蛇头的移动方向添加一个节点
    public void eat(Node food) {
        //获取蛇头
        Node head = body.getFirst();
        switch (direction) {
            case UP:
                //在蛇头的上面添加一个节点
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }
}
