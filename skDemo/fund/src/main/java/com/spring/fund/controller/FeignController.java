package com.spring.fund.controller;

import com.spring.fund.facade.IUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.common.model.UserInfo;

import javax.annotation.Resource;

/**
 * @Description 测试Feign接口
 * @Author junode
 * @Date 2020/12/27
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Resource
    private IUser iUser;

    @GetMapping("/user/{id}")
    public UserInfo getUser(@PathVariable("id") Long id){
        UserInfo info = iUser.getUser(id);
        return info;
    }

    @GetMapping("/user/{id}/{userName}/{note}")
    public UserInfo updateUser(@PathVariable("id") Long id,
                               @PathVariable("userName") String userName,
                               @PathVariable("note") String note){
        UserInfo user = new UserInfo(id,userName,note);
        return iUser.putUser(user);
    }
}
