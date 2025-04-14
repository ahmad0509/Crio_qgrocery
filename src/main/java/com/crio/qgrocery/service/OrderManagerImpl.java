package com.crio.qgrocery.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.qgrocery.dto.OrderDto;

import com.crio.qgrocery.entity.GroceryItem;
import com.crio.qgrocery.entity.Order;
import com.crio.qgrocery.mapper.DtoMapper;
import com.crio.qgrocery.repository.CustomerRepository;
import com.crio.qgrocery.repository.GroceryItemRepository;
import com.crio.qgrocery.repository.OrderRepository;

@Service
public class OrderManagerImpl implements OrderManager{
    private final GroceryItemRepository groceryItemRepository;
    private final OrderRepository orderRepository;
    private final DtoMapper dtoMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderManagerImpl(GroceryItemRepository groceryItemRepository, CustomerRepository customerRepository, OrderRepository orderRepository,DtoMapper dtoMapper) {
        this.groceryItemRepository = groceryItemRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = dtoMapper.dtoToOrder(orderDto);
        // Set order date
        order.setOrderDate(new Date());


        // Fetch the customer by ID and throw an exception if not found
        boolean exists = customerRepository.existsById(orderDto.getCustomerId());
        if (!exists) {
            throw new RuntimeException("Customer not found");
        }
        

        // If groceryItemIds is provided, fetch corresponding GroceryItems from DB
        if (orderDto.getGroceryItemIds() != null && !orderDto.getGroceryItemIds().isEmpty()) {
            // Fetch the GroceryItems by their IDs
            List<GroceryItem> groceryItems = groceryItemRepository.findAllById(orderDto.getGroceryItemIds());
        
            // If the number of fetched grocery items does not match the size of the input IDs, handle it
            if (groceryItems.size() != orderDto.getGroceryItemIds().size()) {
            throw new RuntimeException("Some grocery items not found");
         }

            // Set the fetched grocery items into the order
            order.setGroceryItems(groceryItems);
        } else {
        order.setGroceryItems(Collections.emptyList());
        }

        // Calculate the total price based on grocery items
        double totalPrice = order.getGroceryItems().stream()
            .mapToDouble(GroceryItem::getPrice)
            .sum();
            order.setTotalPrice(totalPrice);

        // Save the order and return the corresponding DTO
        return dtoMapper.orderToDto(orderRepository.save(order));
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        return dtoMapper.orderToDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
            .stream()
            .map(dtoMapper::orderToDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}


