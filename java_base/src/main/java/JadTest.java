import java.util.ArrayList;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description jad使用示例
 */
public class JadTest {
    public static void main(String[] args) {
        List<String> userNames = new ArrayList<String>(){{
           add("hello");
           add("welcome");
            add("test");
        }};
        for (String userName : userNames) {
            if(userName.equals("test")){
                userNames.remove(userName);
            }
        }
        System.out.println("userNames = " + userNames);
    }
}
