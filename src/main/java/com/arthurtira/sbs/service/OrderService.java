package com.arthurtira.sbs.service;

import com.arthurtira.sbs.dto.OrderDto;

public interface OrderService {
    void processOrder(OrderDto orderDto);
    OrderDto placeOrder(OrderDto orderDto);
}
