package spring.cloud.tdd.test;

import spring.cloud.tdd.api.Subscriber;

/**
 * @Description TODO
 * @Author junode
 * @Date 2021/2/2
 */
public class SubScriberImpl implements Subscriber {
    @Override
    public void receive(String message) {
        System.out.println(message);
    }
}
