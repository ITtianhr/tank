package com.mashibing.tank.cor;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Explode;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank.cor
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/27 14:49
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/27    tianhr            v1.0.0               修改原因
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            //TODO copy code from method collideWith
            if(b.group == t.getGroup()) return true;

            if(b.rect.intersects(t.rect)) {
                t.die();
                b.die();
                int eX = t.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
                int eY = t.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
                new Explode(eX, eY);
                return false;
            }

        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }

        return true;

    }
}
