package com.junode.autowired_extentsion;

import org.springframework.stereotype.Service;

/**
 * @author junode
 * @version 1.0.0
 * @Description 证券服务类
 * @createTime 2024年04月11日 23:47:00
 */
@Service
public class MarketServiceImpl implements IMarketService{

    @Override
    public MarketDo getMarketBy(String key) {
        return new MarketDo();
    }
}
