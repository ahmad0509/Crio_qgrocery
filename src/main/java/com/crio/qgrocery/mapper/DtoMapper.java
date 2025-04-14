package com.crio.qgrocery.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crio.qgrocery.dto.CustomerDto;
import com.crio.qgrocery.dto.GroceryItemDto;
import com.crio.qgrocery.dto.OrderDto;
import com.crio.qgrocery.entity.Customer;
import com.crio.qgrocery.entity.GroceryItem;
import com.crio.qgrocery.entity.Order;

@Component
public class DtoMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Mapping from Customer entity to CustomerDto
    public CustomerDto customerToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    // Mapping from CustomerDto to Customer entity
    public Customer dtoToCustomer(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    // Mapping from GroceryItem entity to GroceryItemDto
    public GroceryItemDto groceryItemToDto(GroceryItem groceryItem) {
        return modelMapper.map(groceryItem, GroceryItemDto.class);
    }

    // Mapping from GroceryItemDto to GroceryItem entity
    public GroceryItem dtoToGroceryItem(GroceryItemDto groceryItemDto) {
        return modelMapper.map(groceryItemDto, GroceryItem.class);
    }

    // Mapping from Order entity to OrderDto
    public OrderDto orderToDto(Order order) {

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setCustomerId(order.getCustomer().getCustomerId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalPrice(order.getTotalPrice());
        List<Long> groceryItemIds = order.getGroceryItems().stream()
                .map(GroceryItem::getItemId)
                .collect(Collectors.toList());
        orderDto.setGroceryItemIds(groceryItemIds);
        return orderDto;
    }


    // Mapping from OrderDto to Order entity
    public Order dtoToOrder(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}