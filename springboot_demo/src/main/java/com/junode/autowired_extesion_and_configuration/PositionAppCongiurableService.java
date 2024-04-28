package com.junode.autowired_extesion_and_configuration;

import com.junode.autowired_extentsion.IProcessor;
import com.junode.autowired_extentsion.PositionDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author junode
 * @version 1.0.0
 * @Description 可扩展性和可配置性
 * @createTime 2024年04月12日 00:03:00
 */
@Service
public class PositionAppCongiurableService {

    /**
     * 扩展性与可配置性。与扩展性的区别，将List 改成 Map
     */
    @Autowired
    private Map<String, IProcessor<PositionDo, PositionDo>> processors;

    public PositionDo build(List<String> filterCondition) {
        PositionDo positionDo = new PositionDo();
        filterCondition.forEach(key -> {
            // 由外部传入配置信息/或者读取系统参数列表。
            // 这里假设key就和外部传入的过滤条件一致
            if (processors.get(key) != null) {
                processors.get(key).process(positionDo);
            }
        });
        return positionDo;
    }
}
