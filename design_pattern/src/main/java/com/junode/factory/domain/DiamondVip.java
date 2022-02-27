package com.junode.factory.domain;

import com.junode.factory.domain.api.Lucky;
import com.junode.factory.entity.LuckyUser;
import com.junode.factory.domain.api.User;

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
