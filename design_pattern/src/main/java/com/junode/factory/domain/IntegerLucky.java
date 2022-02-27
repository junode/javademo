package com.junode.factory.domain;

import com.junode.factory.domain.anno.MyAnnotation;
import com.junode.factory.domain.api.Lucky;
import com.junode.factory.entity.LuckyDraw;

/**
 * 积分抽奖
 */
@MyAnnotation("1")
public class IntegerLucky  extends LuckyDraw implements Lucky {
    /*public static Integer getType() {
        return 1;
    }*/
}
