package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;
import com.mashibing.tank.Wall;

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
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank)o1;
            Wall w = (Wall)o2;


            if(t.rect.intersects(w.rect)) {
                t.back();
            }

        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collide(o2, o1);
        }

        return true;

    }

}
