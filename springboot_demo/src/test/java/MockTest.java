import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author junode
 * @Date 2022/4/18
 */
public class MockTest {

    @Test
    public void createMockObject() {
        // 使用 mock 静态方法创建 Mock 对象.
        List mockedList = Mockito.mock(List.class);
        Assert.assertTrue(mockedList instanceof List);

        // mock 方法不仅可以 Mock 接口类, 还可以 Mock 具体的类型.
      /*  ArrayList mockedArrayList = Mockito.mock(ArrayList.class);
        Assert.assertTrue(mockedArrayList instanceof List);
        Assert.assertTrue(mockedArrayList instanceof ArrayList);*/

        // 对List类型进行mock操作了，我们就可以定制他的具体行为

        // 当调用 mockedList.add("one")时，返回true
        Mockito.when(mockedList.add("one")).thenReturn(true);
        // 当调用 mockedList.size()时，则返回2
        Mockito.when(mockedList.size()).thenReturn(1);
//        Mockito.when(mockedList.size()).thenReturn(2);

        Assert.assertTrue(mockedList.add("one"));
        // 因为我们没有定制 add("two")，因此返回默认值，即false
        Assert.assertFalse(mockedList.add("two"));
        Assert.assertEquals(mockedList.size(),1);

        Iterator i = Mockito.mock(Iterator.class);
        Mockito.when(i.next()).thenReturn("Hello,").thenReturn("Mockito!");
        String result = i.next() + " " + i.next();
        //assert
        Assert.assertEquals("Hello, Mockito!", result);
    }
}
