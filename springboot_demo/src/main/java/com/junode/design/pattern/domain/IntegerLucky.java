package com.junode.design.pattern.domain;

import com.junode.design.pattern.domain.anno.MyAnnotation;
import com.junode.design.pattern.domain.api.Lucky;
import com.junode.design.pattern.entity.LuckyDraw;

/**
 * 积分抽奖
 */
@MyAnnotation("1")
public class IntegerLucky  extends LuckyDraw implements Lucky {
    /*public static Integer getType() {
        return 1;
    }*/
}
