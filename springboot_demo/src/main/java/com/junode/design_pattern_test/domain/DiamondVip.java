package com.junode.design_pattern_test.domain;

import com.junode.design_pattern_test.domain.api.Lucky;
import com.junode.design_pattern_test.domain.api.User;
import com.junode.design_pattern_test.entity.LuckyUser;

/**
 * 钻石会员
 */
public class DiamondVip  extends LuckyUser implements User {
    public static Integer getTag() {return 1;}

    @Override
    public void action(Lucky luckyInstance) {
        if(luckyInstance instanceof FreeLucky){

        }else if(luckyInstance instanceof OrderLucky) {

        }else if(luckyInstance instanceof FreeLucky) {

        }
    }
}
