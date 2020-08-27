package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/3 17:23
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/3    tianhr            v1.0.0               修改原因
 */
public class TankFrame extends Frame {

    static final int GAME_WIDTH = 1920, GAME_HEIGHT = 1080;
    GameModel gm = GameModel.getInstance();

    public  TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);//不可改变大小
        setTitle("tank war");//标题
        setVisible(true);//显示窗口

        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g){//解决界面闪烁
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        graphics.setColor(c);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public  void paint(Graphics g){
        gm.paint(g);
    }

    class MyKeyListener extends KeyAdapter{

        Boolean bL = false;
        Boolean bU = false;
        Boolean bR = false;
        Boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {//按键按下
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
//            x+=10;
//            repaint();//自动调用画板
        }

        @Override
        public void keyReleased(KeyEvent e) {//按键抬起
//            System.out.println("1111");
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    GameModel.getInstance().getMyTank().fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            Tank myTank = GameModel.getInstance().myTank;
            if (!bL  && !bU && !bR  && !bD) myTank.setMove(false);
            else {
                myTank.setMove(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }


    }
}
