package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @ClassName: com.mashibing.tank.cor
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: thr
 * @date: 2020/8/27 14:48
 * <p>
 * Modification History:
 * Date          Author           Version            Description
 * ---------------------------------------------------------*
 * 2020/8/27    tianhr            v1.0.0               修改原因
 */
public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
