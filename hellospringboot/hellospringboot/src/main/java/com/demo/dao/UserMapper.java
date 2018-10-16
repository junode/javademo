package com.demo.dao;

import com.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by toutou on 2018/10/15.
 */
@Mapper
public interface UserMapper {
    @Select("SELECT id,username,age,phone,email FROM USER WHERE AGE=#{age}")
    List<User> getUser(int age);
}