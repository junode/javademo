package com.junode.design_pattern_test.domain;

import com.junode.design_pattern_test.domain.anno.MyAnnotation;
import com.junode.design_pattern_test.domain.api.Lucky;
import com.junode.design_pattern_test.entity.LuckyDraw;

/**
 * 订单抽奖
 */
@MyAnnotation("0")
public class OrderLucky  extends LuckyDraw implements Lucky {
    /*public static Integer getType() {
        return 0;
    }*/
}
