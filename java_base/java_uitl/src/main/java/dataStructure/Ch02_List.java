package dataStructure;

/**
 * @Auther: zwy
 * @Date: 2020/8/6
 * @Description: 抽象数据类型线性表的定义实现
 */
public class Ch02_List {
    private int capacity;
    // 存储元素的数组
    private Integer[] eles;
    // 当前元素个数
    private Integer len;

    /**
    * 功能描述: 构造函数
    * @auther: zwy
    */
    public Ch02_List(int capacity){
        this.capacity = capacity;
        eles = (Integer[])new Object[capacity];
        this.len = 0;
    }

    /**
    * 功能描述: 销毁线性表
    * @auther: zwy
    */
    public void destory(){
        this.eles = null;
        this.len = 0;
    }

    /**
    * 功能描述: 置空线性表
    * @auther: zwy
    */
    public void clear(){
        this.eles = (Integer[])new Object[this.capacity];
        this.len = 0;
    }

    /**
    * 功能描述: 判断线性表是否为空
    * @auther: zwy
    */
    public boolean isEmpty(){
        return this.len == 0;
    }

    /**
    * 功能描述: 返回数组元素个数
    * @auther: zwy
    */
    public int getLen(){
        return this.len;
    }

    /**
    * 功能描述: 返回第i个元素
    * @auther: zwy
    */
    public Integer getItem(int index) throws Exception {
        if(index<capacity){
            throw new Exception("IndexOutOfBound,数组越界");
        }
        return this.eles[index];
    }


}
