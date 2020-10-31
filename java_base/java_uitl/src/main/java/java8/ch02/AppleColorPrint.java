package java8.ch02;

import java8.ch01.Apple;

/**
 * @Auther: zwy
 * @Date: 2020/7/5
 * @Description: java8.ch02
 * @version:
 */
public class AppleColorPrint implements ApplePrintType {
    @Override
    public String print(Apple apple) {
        return "当前苹果颜色为"+apple.getColor();
    }
}
