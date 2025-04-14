package com.crio.qgrocery.service;

import java.util.List;

import com.crio.qgrocery.dto.GroceryItemDto;

public interface GroceryItemManager {
    GroceryItemDto createItem(GroceryItemDto itemDto);
    GroceryItemDto getItemById(Long id);
    List<GroceryItemDto> getAllItems();
    GroceryItemDto updateItem(Long id, GroceryItemDto itemDto);
    void deleteItem(Long id);
}
