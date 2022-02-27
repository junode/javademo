package com.junode.design.pattern.service;

import com.junode.design.pattern.entity.LuckyUser;

/**
 * @Description TODO
 * @Author junode
 * @Date 2022/1/12
 */
public interface ILuckUserService {
    LuckyUser getById(Integer userId);
}
