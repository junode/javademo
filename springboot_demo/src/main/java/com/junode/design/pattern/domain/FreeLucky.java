package com.junode.design.pattern.domain;

import com.junode.design.pattern.domain.anno.MyAnnotation;
import com.junode.design.pattern.domain.api.Lucky;
import com.junode.design.pattern.entity.LuckyDraw;

/**
 * 免费抽象
 */
@MyAnnotation("2")
public class FreeLucky extends LuckyDraw implements Lucky {
   /* public static Integer getType() {
        return 2;
    }*/
}
