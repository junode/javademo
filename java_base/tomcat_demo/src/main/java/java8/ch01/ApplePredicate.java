package java8.ch01;

/**
 * @Auther: zwy
 * @Date: 2020/7/5
 * @Description: 对苹果选择标准进行建模，判断苹果是否符合标准
 * @version:
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}
