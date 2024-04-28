package com.junode.autowired_extentsion;

import org.springframework.stereotype.Service;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月11日 23:49:00
 */
@Service
public class SecurityServiceImpl implements ISecurityService{
    @Override
    public SecurityDo getSecurityBy(String key) {
        return new SecurityDo();
    }
}
