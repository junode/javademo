package com.junode.design_pattern_test.domain;

import com.junode.design_pattern_test.domain.anno.MyAnnotation;
import com.junode.design_pattern_test.domain.api.Lucky;
import com.junode.design_pattern_test.entity.LuckyDraw;

/**
 * 积分抽奖
 */
@MyAnnotation("1")
public class IntegerLucky  extends LuckyDraw implements Lucky {
    /*public static Integer getType() {
        return 1;
    }*/
}
