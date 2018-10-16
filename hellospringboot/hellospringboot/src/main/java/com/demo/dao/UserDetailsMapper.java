package com.demo.dao;

import com.demo.pojo.UserDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDetails record);

    int insertSelective(UserDetails record);

    UserDetails selectByPrimaryKey(Integer id);

    UserDetails getUserDetailsByUid(Integer uid);

    int updateByPrimaryKeySelective(UserDetails record);

    int updateByPrimaryKey(UserDetails record);
}