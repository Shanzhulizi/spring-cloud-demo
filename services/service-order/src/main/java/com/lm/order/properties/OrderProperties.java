package com.lm.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")
@Data
public class OrderProperties {
    private String timeout;
    private String autoConfirm;

    // 其他订单相关的配置属性可以在这里添加
}
