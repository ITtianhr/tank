package com.mashibing.tank;

import com.mashibing.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/25 19:23
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/25    tianhr            v1.0.0               修改原因
 */
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }

    Tank myTank;

    public static GameModel getInstance(){
        return INSTANCE;
    }


//    public List<Bullet> bulletList = new ArrayList<Bullet>();
//    public List<Tank> tanks = new ArrayList<>();
//    public List<Explode> exploads = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    List<GameObject> gameObjects = new ArrayList<>();

    private GameModel(){

    }

    private void init() {
        myTank = new Tank(200,300,Dir.DOWN, Group.GOOD);

        String tankCount = (String)PropertyMgr.get("initTankCount");
        int tankCounts = Integer.valueOf(tankCount);
        for (int i = 0; i < tankCounts; i++) {
            new Tank(50 + i*80, 200,Dir.DOWN,Group.BAD);
        }

        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    public void add(GameObject go){
        this.gameObjects.add(go);
    }

    public void remove(GameObject go){
        this.gameObjects.remove(go);
    }

    public  void paint(Graphics g){

        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("bullets:" + bulletList.size(), 10, 60);
//        g.drawString("tanks:" + tanks.size(), 10, 80);
//        g.drawString("exploads:" + exploads.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
//        for (int i = 0; i < tanks.size(); i++) {
//            tanks.get(i).paint(g);
//        }
//        for (int i = 0; i < exploads.size(); i++) {
//            exploads.get(i).paint(g);
//        }

        //碰撞检测
//        for (int i = 0; i <bulletList.size() ; i++) {
//            for (int j = 0; j <tanks.size() ; j++) {
//                bulletList.get(i).collideWith(tanks.get(j));
//            }
//        }

        // 互相碰撞
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) { // Comparator.compare(o1,o2)
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                // for
                chain.collide(o1, o2);
            }
        }

//        x+=10;
//        y+=10;
    }

    public Tank getMyTank(){
        return myTank;
    }
}
