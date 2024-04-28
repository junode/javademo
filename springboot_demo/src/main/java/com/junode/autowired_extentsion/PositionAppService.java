package com.junode.autowired_extentsion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月12日 00:03:00
 */
@Service
public class PositionAppService {

    /**
     * 扩展性。当需要添加处理器时，则补充实现类即可。
     */
    @Autowired
    private List<IProcessor<PositionDo, PositionDo>> processors;

    public PositionDo build() {
        PositionDo positionDo = new PositionDo();
        processors.forEach(process->{
            process.process(positionDo);
        });
        return positionDo;
    }
}
