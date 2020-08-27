package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/27 14:46
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/27    tianhr            v1.0.0               修改原因
 */
public class ColliderChain {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new BulletWallCollider());
        add(new TankWallCollider());
    }


    public void add(Collider c) {
        colliders.add(c);
    }


    public boolean collide(GameObject o1, GameObject o2) {
        for(int i=0; i<colliders.size(); i++) {
            if(!colliders.get(i).collide(o1, o2)) {
                return false;
            }
        }

        return true;
    }
}
