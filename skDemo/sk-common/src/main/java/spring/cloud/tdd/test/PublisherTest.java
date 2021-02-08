package spring.cloud.tdd.test;


import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import spring.cloud.tdd.api.Subscriber;

/**
 * @Description TODO
 * @Author junode
 * @Date 2021/2/2
 */

public class PublisherTest   {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void oneSubscriberReceivesAMessage(){
        final Subscriber subscriber = context.mock(Subscriber.class);
        Publisher publisher = new Publisher() ;
        publisher.addMessage(subscriber);
        final String message = "message";

        context.checking(new Expectations(){
            {oneOf(subscriber).receive(message);}
        });


    }
}
