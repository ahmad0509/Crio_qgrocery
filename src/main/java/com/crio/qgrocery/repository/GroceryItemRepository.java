package com.crio.qgrocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.qgrocery.entity.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    // This interface will automatically provide CRUD operations for GroceryItem entity

}
