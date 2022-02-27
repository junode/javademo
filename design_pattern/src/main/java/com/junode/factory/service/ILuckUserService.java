package com.junode.factory.service;

import com.junode.factory.entity.LuckyUser;

/**
 * @Description TODO
 * @Author junode
 * @Date 2022/1/12
 */
public interface ILuckUserService {
    LuckyUser getById(Integer userId);
}
