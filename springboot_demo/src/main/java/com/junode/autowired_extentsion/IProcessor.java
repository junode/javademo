package com.junode.autowired_extentsion;

/**
 * @author junode
 * @version 1.0.0
 * @Description 这里定一个处理器类，将源S，通过处理得到T
 * @createTime 2024年04月11日 23:41:00
 */
public interface IProcessor<S,T> {
    /**
     * 过滤器
     * @param source 源数据
     * @return
     */
    T process(S source);
}
