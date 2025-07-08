package com.lm.product;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Test
    void discoveryClientTest() {
        // 获取服务列表
        discoveryClient.getServices().forEach(serviceId -> {
            System.out.println("Service ID: " + serviceId);
            // 获取服务实例
            discoveryClient.getInstances(serviceId).forEach(instance -> {
                System.out.println("Instance: " + instance.getServiceId() + " - " + instance.getHost() + ":" + instance.getPort());
            });
        });
    }

}
