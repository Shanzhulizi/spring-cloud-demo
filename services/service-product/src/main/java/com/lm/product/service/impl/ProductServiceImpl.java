package com.lm.product.service.impl;


import com.lm.product.bean.Product;
import com.lm.product.service.ProductService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public Product getProductById(Long productId) {
        Product product =new Product();
        product.setId(productId);
        product.setProductName("苹果");
        product.setPrice(new BigDecimal("100.0"));
        product.setNum(10); // Example stock quantity

        return product;
    }
}
