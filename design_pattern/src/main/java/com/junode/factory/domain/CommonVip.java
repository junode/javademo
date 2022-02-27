package com.junode.factory.domain;

import com.junode.factory.domain.api.Lucky;
import com.junode.factory.domain.api.User;
import com.junode.factory.entity.LuckyUser;

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
