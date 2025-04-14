package com.crio.qgrocery.service;

import java.util.List;

import com.crio.qgrocery.dto.OrderDto;

public interface OrderManager {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long id);
    List<OrderDto> getAllOrders();
    void deleteOrder(Long id);
}
