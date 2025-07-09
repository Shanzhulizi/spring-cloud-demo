package com.lm.order.feign.fallback;

import com.lm.order.feign.ProductFeignClient;
import com.lm.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {

    @Override
    public Product getProductById(Long id) {
        // 返回一个默认的Product对象
        Product product = new Product();
        product.setId(id);
        product.setProductName("Fallback Product");
        product.setPrice(new BigDecimal(0.0));
        return product;
    }
}
