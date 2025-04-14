package com.crio.qgrocery.service;

import java.util.List;

import com.crio.qgrocery.dto.CustomerDto;

public interface CustomerManager {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
}
