package com.crio.qgrocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.qgrocery.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // This interface will automatically provide CRUD operations for Customer entity

}
