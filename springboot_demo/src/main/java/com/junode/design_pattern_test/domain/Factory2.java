package com.junode.design_pattern_test.domain;

import com.junode.design_pattern_test.domain.anno.MyAnnotation;
import com.junode.design_pattern_test.domain.api.Lucky;
import com.junode.design_pattern_test.domain.api.User;
import com.junode.design_pattern_test.entity.LuckyDraw;
import com.junode.design_pattern_test.entity.LuckyUser;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 对Factory.class的改进，去除if...else...判断
 * 此时采用的是反射的方式
 */
public class Factory2 {
    public final static Map<Integer, Function<LuckyUser, User>> userMap;
    public final static Map<Integer, Function<LuckyDraw, Lucky>> luckyMap;

    static {
        userMap = new ConcurrentHashMap<>();
        luckyMap = new ConcurrentHashMap<>();
        // 包扫描，也可以采用spring的包扫描功能替代
        org.reflection.Reflection reflection = new org.reflection.Reflection("com.junode.design_pattern_test");

        // vip用户
        final Set<Class<? extends User>> monitorClasses = reflection.getSubTypesOf(User.class);
        for (Class<? extends User> monitorClass : monitorClasses) {
            try {
                final Method getTag = monitorClass.getMethod("getTag");
                Object tagValue = getTag.invoke(null);
                userMap.put(Integer.parseInt((String) tagValue), (user -> {
                    try {
                        User vip = monitorClass.newInstance();
                        BeanUtils.copyProperties(user, vip);
                        return vip;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                }));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 抽奖类型也类似，而up主是采用注解的方式实现的。
//        final Set<Class<? extends Lucky>> luckClazzs = reflection.getSubTypesOf(Lucky.class);
        final Set<Class<? extends Lucky>> luckClazzs = reflection.getTypesAnnotatedWith(MyAnnotation.class);
        for (Class<? extends Lucky> luckClazz : luckClazzs) {
            System.out.println(luckClazz.getName());
            MyAnnotation annotation = luckClazz.getAnnotation(MyAnnotation.class);
            System.out.println("annotation.value() =" + annotation.value());
        }
        for (Class<? extends Lucky> luckClazz : luckClazzs) {
            try {
                final Method getTag = luckClazz.getMethod("getType");

                Object luckValue = getTag.invoke(null);
                luckyMap.put(Integer.parseInt((String) luckValue), (luck -> {
                    try {
                        Lucky luckType = luckClazz.newInstance();
                        BeanUtils.copyProperties(luck, luckType);
                        return luckType;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                }));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(111);
    }

    public static User getUserInstance(LuckyUser user) {
        User impl = userMap.get(user.getVipLevel()).apply(user);
        return impl;
    }

    public static Lucky getLuckyInstance(LuckyDraw luckyDraw) {
        Lucky lucky = luckyMap.get(luckyDraw.getLuckType()).apply(luckyDraw);
        return lucky;
    }


}
