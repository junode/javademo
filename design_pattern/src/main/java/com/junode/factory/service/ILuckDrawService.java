package com.junode.factory.service;

import com.junode.factory.entity.LuckyDraw;

/**
 * IService是mybatis plus的
 */
public interface ILuckDrawService /*extends IService<LuckyDraw>*/{
    LuckyDraw getById(Integer actId);

    Object lucky(Integer userId,Integer actId);

    Object lucky2(Integer userId,Integer actId);

    Object lucky3(Integer userId,Integer actId);

}
