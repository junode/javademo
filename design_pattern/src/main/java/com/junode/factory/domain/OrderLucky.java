package com.junode.factory.domain;

import com.junode.factory.domain.anno.MyAnnotation;
import com.junode.factory.domain.api.Lucky;
import com.junode.factory.entity.LuckyDraw;

/**
 * 订单抽奖
 */
@MyAnnotation("0")
public class OrderLucky  extends LuckyDraw implements Lucky {
    /*public static Integer getType() {
        return 0;
    }*/
}
