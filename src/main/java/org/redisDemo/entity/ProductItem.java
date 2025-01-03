package org.redisDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("ProductItem")
public class ProductItem implements Serializable {
    @Id
    private int prodId;
    private String prodName;
    private int prodQty;
    private long prodPrice;

    public int getProdId() {
        return prodId;
    }

}
