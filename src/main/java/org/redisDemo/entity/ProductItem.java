package org.redisDemo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("ProductItem")
public class ProductItem implements Serializable {
    @Id
    private long prodId;
    private String prodName;
    private int prodQty;
    private long prodPrice;
}
