package spring.cloud.tdd.api;

/**
 * @Description 发布订阅接口
 * @Author junode
 * @Date 2021/2/2
 */
public interface Subscriber {
    void receive(String message);
}
