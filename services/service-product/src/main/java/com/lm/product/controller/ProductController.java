package com.lm.product.controller;

import com.lm.product.bean.Product;
import com.lm.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@ResponseBody//用于 直接返回数据（如 JSON、XML 或文本）到 HTTP 响应体，而非跳转 HTML 页面
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 获取商品信息
     */
//    http://localhost:9000/product/1
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        log.info("获取商品信息: {}", product);
        return product;
    }

}
