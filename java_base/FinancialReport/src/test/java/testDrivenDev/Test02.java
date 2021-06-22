package testDrivenDev;

/**
 * @Description TODO
 * @Author junode
 * @Date 2021/2/4
 */
public class Test02 {


    public static boolean testStr(String str) {
        if(str == "a") {
            return true;
        }else if(str =="b") {
            return false;
        }else if(str == "c") {
            return true;
        }else {
            return false;
        }
    }
}
