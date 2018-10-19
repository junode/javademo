package com.demo.service;

import com.demo.pojo.UserDetails;
import com.demo.pojo.UserPosition;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by toutou on 2018/10/15.
 */
public interface UserService {
    UserDetails getUserDetailsByUid(int uid);
    List<UserPosition> getVicinity(BigDecimal minlng, BigDecimal maxlng, BigDecimal minlat, BigDecimal maxlat);
    List<UserPosition> getvicinitysort(BigDecimal longitude,BigDecimal latitude);
}