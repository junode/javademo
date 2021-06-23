package spring.cloud.tdd.test;

import spring.cloud.tdd.api.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author junode
 * @Date 2021/2/2
 */
public class Publisher {
    private List<Subscriber> publishers = new ArrayList<>();
    public void addMessage(Subscriber message) {
        this.publishers.add(message);
    };
}
