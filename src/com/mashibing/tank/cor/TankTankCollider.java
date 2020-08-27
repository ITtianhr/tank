package com.mashibing.tank.cor;

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
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;
            if(t1.getRect().intersects(t2.getRect())) {
                t1.back();
                t2.back();
            }

        }

        return true;

    }

}
