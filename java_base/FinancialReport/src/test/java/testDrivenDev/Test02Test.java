package testDrivenDev;

import org.testng.Assert;


class Test02Test {


    @org.junit.jupiter.api.Test
    void testStr() {
        Assert.assertEquals(false,Test02.testStr("a"));
        System.out.println(Test02.testStr("a"));
    }
}