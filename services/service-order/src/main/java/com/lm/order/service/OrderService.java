package com.lm.order.service;

import com.lm.order.bean.Order;

public interface OrderService {

    public Order createOrder(Long productId, Long userId);
}
