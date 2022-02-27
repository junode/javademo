package com.junode.concurrent.pojo;

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
