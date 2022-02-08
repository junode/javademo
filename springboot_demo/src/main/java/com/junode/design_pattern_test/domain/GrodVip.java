package com.junode.design_pattern_test.domain;

import com.junode.design_pattern_test.domain.api.Lucky;
import com.junode.design_pattern_test.domain.api.User;
import com.junode.design_pattern_test.entity.LuckyUser;

/**
 * 黄金会员
 */
public class GrodVip extends LuckyUser implements User {
    public static Integer getTag() {
        return 2;
    }

    @Override
    public void action(Lucky luckyInstance) {
        if(luckyInstance instanceof FreeLucky){

        }else if(luckyInstance instanceof OrderLucky) {

        }else if(luckyInstance instanceof FreeLucky) {

        }
    }
}
