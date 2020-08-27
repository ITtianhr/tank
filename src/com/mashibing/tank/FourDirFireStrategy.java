package com.mashibing.tank;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/12 21:13
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/12    tianhr            v1.0.0               修改原因
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bx, by, dir, t.group);
        }
        if (t.group == Group.GOOD) new Thread(()-> new Audio("audio/tank_fire.wav").play()).start();
    }
}
