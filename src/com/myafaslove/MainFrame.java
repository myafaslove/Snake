package com.myafaslove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame {
    private Snake snake;//蛇
    private JPanel jPanel;//游戏棋盘
    private Node food;//食物

    public MainFrame() throws HeadlessException {
        //初始化窗体
        initFrame();
        //初始化游戏棋盘
        initGamePanel();
        //初始化蛇
        initSnake();
        //初始化食物
        initFood();
        //初始化定时器
        initTimer();
        //设置键盘监听，让蛇受控
        setKeyListener();


    }

    private void initFood() {
        food = new Node();
        food.random();
    }

    //设置键盘监听
    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            //当键盘按下时会自动调用此方法
            @Override
            public void keyPressed(KeyEvent e) {
                //键盘中每一个建都有一个编号
//                System.out.println(e.getKeyCode());
//                获取键盘编号
//                UP 38 DOWN 40 LEFT 37 RIGHT 39
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP://上键
                        if (snake.getDirection() != Direction.DOWN) {
                            //改变蛇的运动方向
                            snake.setDirection(Direction.UP);
                        }
                        break;

                    case KeyEvent.VK_DOWN://下键
                        if (snake.getDirection() != Direction.UP) {
                            snake.setDirection(Direction.DOWN);
                        }
                        break;

                    case KeyEvent.VK_LEFT://左键
                        if (snake.getDirection() != Direction.RIGHT) {
                            snake.setDirection(Direction.LEFT);
                        }
                        break;

                    case KeyEvent.VK_RIGHT://右键
                        if (snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                        break;
                }
            }
        });
    }

    //初始化定时器
    private void initTimer() {
        //在指定时间内调用蛇移动的方法
        //在指定时间内调用蛇移动的方法
        Timer timer = new Timer();

        //初始化定时任务
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                snake.move();
                //判断蛇头是否和食物重合
                Node head = snake.getBody().getFirst();
                if (head.getX() == food.getX() && head.getY() == food.getY()) {
                    snake.eat(food);
                    food.random();
                }
                //重绘游戏棋盘
                jPanel.repaint();
            }
        };

        //每100ms执行一次定时任务
        timer.scheduleAtFixedRate(timerTask, 0, 100);//延迟
    }

    private void initSnake() {
        snake = new Snake();
    }

    private void initGamePanel() {
        jPanel = new JPanel() {
            //绘制游戏棋盘中的内容
            @Override
            public void paint(Graphics g) {
                //清空棋盘
                g.clearRect(0, 0, 600, 600);
                //Graphics g 可以看作为画笔 提供了很多方法，可以绘制一些基本图形
                //绘制40条横线
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(0, i * 15, 600, i * 15);
                }
                //绘制40条竖线
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(i * 15, 0, i * 15, 600);
                }
                //绘制🐍
                LinkedList<Node> body = snake.getBody();
                for (Node node : body) {
                    g.fillRect(node.getX() * 15, node.getY() * 15, 15, 15);
                }

                //绘制食物
                g.fillRect(food.getX() * 15, food.getY() * 15, 15, 15);
            }
        };

        //把棋盘啊添加到窗体中
        add(jPanel);
    }

    private void initFrame() {
        //窗体大小
        setSize(607, 630);
        //位置
        setLocationRelativeTo(null);
        //关闭按钮作用,退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //大小不可变
        setResizable(false);
    }


    public static void main(String[] args) {
        //创建窗体对象，并显示
        new MainFrame().setVisible(true);
    }
}
