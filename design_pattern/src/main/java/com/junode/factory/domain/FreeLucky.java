package com.junode.factory.domain;

import com.junode.factory.domain.anno.MyAnnotation;
import com.junode.factory.domain.api.Lucky;
import com.junode.factory.entity.LuckyDraw;

/**
 * 免费抽象
 */
@MyAnnotation("2")
public class FreeLucky extends LuckyDraw implements Lucky {
   /* public static Integer getType() {
        return 2;
    }*/
}
