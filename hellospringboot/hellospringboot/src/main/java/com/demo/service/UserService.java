package com.demo.service;

import com.demo.pojo.UserDetails;

import java.util.List;

/**
 * Created by toutou on 2018/10/15.
 */
public interface UserService {
    UserDetails getUserDetailsByUid(int uid);
}