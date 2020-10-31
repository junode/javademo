package java8.ch04.pojo;

/**
 * @Auther: zwy
 * @Date: 2020/7/18
 * @Description: 交易员
 * @version:
 */
public class Trader {
    private String name; // 姓名
    private String workAddr; // 工作地址

    public Trader(String name, String workAddr) {
        this.name = name;
        this.workAddr = workAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkAddr() {
        return workAddr;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", workAddr='" + workAddr + '\'' +
                '}';
    }
}
