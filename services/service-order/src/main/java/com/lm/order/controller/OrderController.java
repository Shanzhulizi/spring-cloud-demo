package com.lm.order.controller;

import com.lm.order.bean.Order;
import com.lm.order.properties.OrderProperties;
import com.lm.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@ResponseBody
@RefreshScope//激活配置属性的自动刷新功能
public class OrderController {


    @Autowired
    OrderService orderService;

//    @Value("${order.timeout}")
//    String orderTimeout;
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String getConfig() {
//        return "order.timeout: " + orderTimeout + ", order.auto-confirm: " + orderAutoConfirm;
        return "order.timeout: " + orderProperties.getTimeout() + ", order.auto-confirm: " + orderProperties.getAutoConfirm();
    }


//    http://localhost:8000/create?userId=100&productId=4
    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId, @RequestParam("userId")  Long userId) {
        // 这里可以调用商品服务获取商品信息
        Order order = orderService.createOrder(productId, userId);
        // 这里可以进行订单创建逻辑
        log.info("创建订单: productId={}, num={}", productId);
        // 返回订单信息或状态
         return order; //     如果需要返回订单信息，可以取消注释
    }


}
