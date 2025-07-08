package com.lm.order.service.impl;

import com.lm.order.bean.Order;
import com.lm.order.service.OrderService;
import com.lm.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(Long productId, Long userId) {
        //从远程商品服务获取商品信息
        Product product = getProductFromRemoteWithBalance2(productId);

        // 这里可以进行订单创建逻辑
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("朔州");
        order.setNickName("lmcow");
        // 设置其他订单属性
        BigDecimal price = product.getPrice();
        //单价乘数目
        BigDecimal totalAmount = price.multiply(new BigDecimal(product.getNum()));
        order.setTotalAmount(totalAmount); // 示例总金额

        // 设置商品列表
        order.setProductList(List.of(product)); // 将商品信息添加到订单中

        return order;
    }

    /**
     * 基于负载均衡客户端获取商品信息
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemoteWithBalance(Long productId) {

        // 使用负载均衡客户端获取商品服务的实例
        ServiceInstance instance = loadBalancerClient.choose("service-product"); // 使用负载均衡客户端获取实例
        if (instance == null) {
            log.error("没有找到可用的商品服务实例");
            return null; // 或者抛出异常
        }

//        http://localhost:9000/product/1
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("订单服务调用商品服务获取商品信息: {}", url);


//        2.向远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);


        return product; // 示例返回null，实际应返回Product对象
    }

    /**
     * 基于注解的负载均衡
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemoteWithBalance2(Long productId) {
        // 获取到商品服务所在的所有机器的 IP + 端口号
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance instance = instances.get(0); // 这里简单取第一个实例，实际应用中可能需要负载均衡等处理


//        http://service-product/product/1
        String url = "http://" + "service-product/product/" + productId;
        log.info("订单服务调用商品服务获取商品信息: {}", url);

//        2.向远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);

        return product; // 示例返回null，实际应返回Product对象
    }

}
