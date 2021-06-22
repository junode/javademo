package java8.ch01;

/**
 * @Auther: zwy
 * @Date: 2020/7/5
 * @Description: 对苹果颜色属性进行建模，过滤出相应颜色
 * @version:
 */
public class GreenApplePredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
