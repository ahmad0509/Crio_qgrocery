package com.crio.qgrocery.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotEmpty(message = "At least one grocery item ID must be provided")
    private List<Long> groceryItemIds;

    private Date orderDate;

    private Double totalPrice;
}