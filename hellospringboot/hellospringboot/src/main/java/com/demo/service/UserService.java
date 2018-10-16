package com.demo.service;

import com.demo.pojo.User;
import java.util.List;

/**
 * Created by toutou on 2018/10/15.
 */
public interface UserService {
    List<User> getUser(int age);
}