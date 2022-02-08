package org.example.concurrent.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 产品名称
 */
@Builder
@Data
public class Product {
    private String id;
    private String name;
}
