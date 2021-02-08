package testDrivenDev;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Description TDD
 * @Author junode
 * @Date 2021/2/4
 */
@Test()
public class Test01 {

    @DataProvider
    public Object[][] data() {
        return new String[][] {new String[] {"Third Line\nFourth Line"}, new String[] {"Third Line\nFourth Line"}};
    }

    @Test(dataProvider = "data")
    public void test(String d) {
        Assert.assertEquals("Third Line\nFourth Line", "Third Line\nFourth Line");
    }


    @Test
    public void testCase1(){
        System.out.println("hello testng");
    }


}
