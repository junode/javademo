import java.util.ArrayList;
import java.util.List;

/**
 * @Author junode
 * @Date 2022/4/18
 */
public class MailServiceStub implements MailService{
    private List<Message> messages = new ArrayList<>();
    @Override
    public void send(Message msg) {
        messages.add(msg);
    }
    public int numberSent(){
        return messages.size();
    }
}
