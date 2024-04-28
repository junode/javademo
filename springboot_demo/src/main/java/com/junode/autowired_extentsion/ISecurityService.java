package com.junode.autowired_extentsion;

/**
 * @author junode
 * @version 1.0.0
 * @Description 证券服务类
 * @createTime 2024年04月11日 23:47:00
 */
public interface ISecurityService {

    /**
     * 根据证券key获取证券信息
     * @param key
     * @return
     */
    SecurityDo getSecurityBy(String key);
}
