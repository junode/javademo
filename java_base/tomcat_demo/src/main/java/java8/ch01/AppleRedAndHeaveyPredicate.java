package java8.ch01;

/**
 * @Auther: zwy
 * @Date: 2020/7/5
 * @Description: java8.ch01
 * @version:
 */
public class AppleRedAndHeaveyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor()) && 150 < apple.getWeight();
    }
}
