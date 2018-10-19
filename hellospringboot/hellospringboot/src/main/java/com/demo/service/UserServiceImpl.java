package com.demo.service;

import com.demo.dao.UserDetailsMapper;
import com.demo.dao.UserPositionMapper;
import com.demo.pojo.UserDetails;
import com.demo.pojo.UserPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by toutou on 2018/10/15.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDetailsMapper userDetailsMapper;

    @Autowired
    UserPositionMapper userPositionMapper;

    public UserDetails getUserDetailsByUid(int uid){
        return userDetailsMapper.getUserDetailsByUid(uid);
    }

    public List<UserPosition> getVicinity(BigDecimal minlng, BigDecimal maxlng, BigDecimal minlat, BigDecimal maxlat){return userPositionMapper.getvicinity(minlng, maxlng, minlat, maxlat);}

    public List<UserPosition> getvicinitysort(BigDecimal longitude,BigDecimal latitude){return userPositionMapper.getvicinitysort(longitude, latitude);}
}