package com.junode.design_pattern_test.domain;

import com.junode.design_pattern_test.domain.anno.MyAnnotation;
import com.junode.design_pattern_test.domain.api.Lucky;
import com.junode.design_pattern_test.entity.LuckyDraw;

/**
 * 免费抽象
 */
@MyAnnotation("2")
public class FreeLucky extends LuckyDraw implements Lucky {
   /* public static Integer getType() {
        return 2;
    }*/
}
