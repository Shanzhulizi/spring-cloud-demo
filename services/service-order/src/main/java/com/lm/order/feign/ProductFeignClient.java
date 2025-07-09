package com.lm.order.feign;


import com.lm.order.feign.fallback.ProductFeignClientFallback;
import com.lm.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    /**
     * 这是OpenFeign的注解，用于定义远程调用的接口
     * 标注在FeignClient接口的方法上，是发送这样的请求
     * 在Controller上，是接收这样的请求
     *
     * @param id
     */
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
