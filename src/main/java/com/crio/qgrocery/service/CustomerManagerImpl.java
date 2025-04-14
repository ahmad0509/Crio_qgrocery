package com.crio.qgrocery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.qgrocery.dto.CustomerDto;
import com.crio.qgrocery.entity.Customer;
import com.crio.qgrocery.mapper.DtoMapper;
import com.crio.qgrocery.repository.CustomerRepository;

@Service
public class CustomerManagerImpl implements CustomerManager{

    
    public final CustomerRepository customerRepository;
    public final DtoMapper dtoMapper;
    
    @Autowired
    public CustomerManagerImpl(CustomerRepository customerRepository, DtoMapper dtoMapper) {
        this.customerRepository = customerRepository;
        this.dtoMapper = dtoMapper;
    }
    
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = dtoMapper.dtoToCustomer(customerDto);
        return dtoMapper.customerToDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        return dtoMapper.customerToDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(dtoMapper::customerToDto)
            .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        return dtoMapper.customerToDto(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
