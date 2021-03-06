package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/6 17:11
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/6    tianhr            v1.0.0               修改原因
 */
public class Tank {

    private int x ,y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 2;
    private Group group = Group.BAD;

    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    Rectangle rect = new Rectangle();

    Random random = new Random();

    private boolean move = true;
    private TankFrame tf = null ;
    private Boolean living = true;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

        public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living) tf.tanks.remove(this);
//        Color c = g.getColor();
//        g.setColor(Color.pink);
//        g.fillRect(x,y,50,50);
//        g.setColor(c);
        switch (dir) {
            case LEFT :
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case UP :
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT :
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN :
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;
            default:
                break;
        }
        move();

    }

    private void move() {
        if(!move) return;
        switch (dir) {
            case LEFT:
                x -=SPEED;
                break;
            case UP:
                y -=SPEED;
                break;
            case RIGHT:
                x +=SPEED;
                break;
            case DOWN:
                y +=SPEED;
                break;
            default:
                break;
        }
        if (this.group ==Group.BAD && random.nextInt(10)>8) this.fire();
        if (this.group ==Group.BAD && random.nextInt(100)>96) randomDir();
        boundsCheck();
        //更新矩阵
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y <32) y = 32;
        if (this.x > TankFrame.GAME_WIDTH - WIDTH - 2) x = TankFrame.GAME_WIDTH - WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - HEIGHT - 2) y =TankFrame.GAME_HEIGHT - HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    //开火，发射炮弹
    public void fire() {
        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bulletList.add(new Bullet(bx,by,this.dir,this.group,this.tf));
    }

    public void die() {
        this.living = false;
    }
}
