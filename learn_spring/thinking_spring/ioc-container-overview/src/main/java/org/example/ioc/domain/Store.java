package org.example.ioc.domain;

/**
 * demo来自：https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring
 * @Author junode
 * @Date 2021/3/7
 */
public class Store {
    private Item itemDemo;
    private String name;

    /*public Store() {
        this.item = new ItemImpl();
    }*/

    public Store(Item itemDemo,String name) {
        this.itemDemo = itemDemo;
        this.name = name;
    }

    public Item getItemDemo() {
        return itemDemo;
    }

    public void setItemDemo(Item itemDemo) {
        this.itemDemo = itemDemo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Store{" +
                "itemDemo=" + itemDemo +
                ", name='" + name + '\'' +
                '}';
    }
}
