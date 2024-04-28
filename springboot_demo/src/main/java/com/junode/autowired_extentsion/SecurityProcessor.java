package com.junode.autowired_extentsion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author junode
 * @version 1.0.0
 * @Description 通过获取证券信息对持仓进行加工
 * @createTime 2024年04月11日 23:44:00
 */
@Service
public class SecurityProcessor implements IProcessor<PositionDo, PositionDo> {

    @Autowired
    private ISecurityService securityService;


    @Override
    public PositionDo process(PositionDo source) {
        SecurityDo securityDo = securityService.getSecurityBy(String.join("_", source.getSecurityId(), source.getSecurityMarketNo()));
        source.setSecurityName(securityDo.getSecurityName());
        return source;
    }
}
