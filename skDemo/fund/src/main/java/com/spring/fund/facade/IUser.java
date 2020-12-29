package com.spring.fund.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spring.cloud.common.model.UserInfo;

/**
 * @Description 用户接口
 * @Author junode
 * @Date 2020/12/27
 */

@FeignClient("user") // 声明为OpenFeign客户端
public interface IUser {

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/info/{id}") // 注意方法和注解的对应选择
    public UserInfo getUser(@PathVariable("id") Long id);

    /**
     * 修改用户信息
     * @param userInfo
     * @return 用户信息
     */
    @PutMapping("/user/info")
    public UserInfo putUser(@RequestBody UserInfo userInfo);

}
