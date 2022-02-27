package com.junode.factory.service.impl;

import com.junode.factory.domain.Factory;
import com.junode.factory.domain.api.Lucky;
import com.junode.factory.domain.api.User;
import com.junode.factory.entity.LuckyUser;
import com.junode.factory.domain.Factory2;
import com.junode.factory.entity.LuckyDraw;
import com.junode.factory.service.ILuckDrawService;
import org.springframework.stereotype.Service;

/**
 * @Author junode
 * @Date 2022/1/12
 */
@Service
public class LuckDrawServiceImpl implements ILuckDrawService {

    private final LuckUserServiceImpl luckUserService = new LuckUserServiceImpl();

    @Override
    public Object lucky(Integer userId, Integer actId) {
        final LuckyUser user = luckUserService.getById(userId);
        final LuckyDraw luckDraw = this.getById(actId);

        // 对应自创需求的9种情况
        // 采用类的多态来解决此种情况
        /*if(Objects.equals(luckDraw.getLuckType(),0)) {
            if(Objects.equals(user.getVipLevel(),0)) {

            }else if(Objects.equals(user.getVipLevel(),1)) {

            }else {

            }
        } else if(Objects.equals(luckDraw.getLuckType(),1)) {
            if(Objects.equals(user.getVipLevel(),0)) {

            }else if(Objects.equals(user.getVipLevel(),1)) {

            }else {

            }
        }else {
            if(Objects.equals(user.getVipLevel(),0)) {

            }else if(Objects.equals(user.getVipLevel(),1)) {

            }else {

            }
        }
        return null;*/

        // 采用工厂方式实现测试，此时就实现了多态类的实现的拿取
        User userInstance = Factory.getUserInstance(user);
        Lucky luckyInstance = Factory2.getLuckyInstance(luckDraw);

        // 拿到实现类后，当我们做逻辑处理时，还是需要做判断，比如我们将luckyInstance逻辑放在userInstance处理
        userInstance.action(luckyInstance); // 在各个实现类里面还是需要判断确定它是哪种抽奖类型
        return null;
    }

    @Override
    public Object lucky2(Integer userId, Integer actId) {
        return null;
    }

    @Override
    public Object lucky3(Integer userId, Integer actId) {
        return null;
    }

    @Override
    public LuckyDraw getById(Integer actId) {
        return null;
    }
}
