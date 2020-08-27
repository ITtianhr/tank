package com.mashibing.tank;


import java.awt.*;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/6 17:48
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/6    tianhr            v1.0.0               修改原因
 */
public class Bullet extends GameObject{

    private int x ,y;
    private Dir dir;
    private static final int SPEED = 20;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    public Rectangle rect = new Rectangle();
    private Boolean living = true;
    public Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Bullet(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;


        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if (!living) GameModel.getInstance().remove(this);
//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x,y,WIGHT,HEIGHT);
//        g.setColor(c);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            default:
                break;
        }
        move();

    }

    private void move() {
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

        rect.x = this.x;
        rect.y = this.y;

        if (x<0 || y<0 || x> TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) living = false;
    }

//    public void collideWith(Tank tank) {
//        if (this.group == tank.getGroup()) return;
////        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);//获取子弹的矩阵
////        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);//获取坦克的矩阵
//        if (rect.intersects(tank.rect)){//判断是否相交
//            tank.die();
//            this.die();
//            int ex = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
//            int ey = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
//            GameModel.getInstance().add(new Explode(ex, ey));
//        }
//    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public void die() {
        this.living = false;
    }
}
