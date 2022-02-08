package com.junode.design_pattern_test.domain;

import com.junode.design_pattern_test.domain.api.Lucky;
import com.junode.design_pattern_test.domain.api.User;
import com.junode.design_pattern_test.entity.LuckyUser;
import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * 普通会员
 *  这里继承LuckyUser，只是为了省事情
 */
public class CommonVip extends LuckyUser implements User {
    public static Integer getTag() {return 0;}

    @Override
    public void action(Lucky luckyInstance) {
        if(luckyInstance instanceof FreeLucky){

        }else if(luckyInstance instanceof OrderLucky) {

        }else if(luckyInstance instanceof FreeLucky) {

        }
    }
}
