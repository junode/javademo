package com.junode.design_pattern_test.service;

import com.junode.design_pattern_test.entity.LuckyUser;

/**
 * @Description TODO
 * @Author junode
 * @Date 2022/1/12
 */
public interface ILuckUserService {
    LuckyUser getById(Integer userId);
}
