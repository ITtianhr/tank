package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.Audio;
import com.mashibing.tank.ResourceMgr;
import com.mashibing.tank.TankFrame;

import java.awt.*;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank.abstractfactory
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/24 16:30
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/24    tianhr            v1.0.0               修改原因
 */
public class RectExplode extends BaseExplode{
    public static int WIDTH = ResourceMgr.exploads[0].getWidth();
    public static int HEIGHT = ResourceMgr.exploads[0].getHeight();


    private int x ,y;
    //    private Boolean living = true;
    private TankFrame tf = null;
    private int step = 0;


    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y , 20*step, 20*step);
        step++;
//        g.drawImage(ResourceMgr.exploads[step++],x,y,null);
        if (step>=5){
            tf.exploads.remove(this);
        }
        g.setColor(c);
    }
}
