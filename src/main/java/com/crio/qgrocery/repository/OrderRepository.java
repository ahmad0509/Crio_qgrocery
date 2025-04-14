package com.crio.qgrocery.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.qgrocery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // This interface will automatically provide CRUD operations for Order entity

}
