package com.demo.service;

import com.demo.dao.UserDetailsMapper;
import com.demo.pojo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toutou on 2018/10/15.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDetailsMapper userDetailsMapper;

    public UserDetails getUserDetailsByUid(int uid){
        return userDetailsMapper.getUserDetailsByUid(uid);
    }
}