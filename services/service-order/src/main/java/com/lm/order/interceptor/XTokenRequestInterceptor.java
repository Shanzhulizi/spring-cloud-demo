package com.lm.order.interceptor;

import feign.RequestInterceptor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(feign.RequestTemplate template) {
        // 在这里添加自定义的请求头
        template.header("X-Token", UUID.randomUUID().toString());
        // 可以根据需要添加其他请求头
    }
}
