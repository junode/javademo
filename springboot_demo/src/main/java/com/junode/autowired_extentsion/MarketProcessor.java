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
public class MarketProcessor implements IProcessor<PositionDo, PositionDo> {

    @Autowired
    private IMarketService marketService;


    @Override
    public PositionDo process(PositionDo source) {
        MarketDo marketDo = marketService.getMarketBy(String.join("_", source.getPositionDate(), source.getSecurityId(), source.getSecurityMarketNo()));
        source.setFloatRate(marketDo.getFloatRate());
        return source;
    }
}
