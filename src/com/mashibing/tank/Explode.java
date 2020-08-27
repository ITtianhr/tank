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
public class Explode extends GameObject{

    public static int WIDTH = ResourceMgr.exploads[0].getWidth();
    public static int HEIGHT = ResourceMgr.exploads[0].getHeight();


    private int x ,y;
//    private Boolean living = true;
//    private GameModel gm = null;
    private int step = 0;


    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.exploads[step++],x,y,null);
        System.out.println("11111");
        if (step>=ResourceMgr.exploads.length){
            GameModel.getInstance().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

}
