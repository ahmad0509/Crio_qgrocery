package com.crio.qgrocery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.qgrocery.dto.GroceryItemDto;
import com.crio.qgrocery.entity.GroceryItem;
import com.crio.qgrocery.mapper.DtoMapper;
import com.crio.qgrocery.repository.GroceryItemRepository;

@Service
public class GroceryItemManagerImpl implements GroceryItemManager {

    private final GroceryItemRepository itemRepository;
    private final DtoMapper dtoMapper;

    @Autowired
    public GroceryItemManagerImpl(GroceryItemRepository itemRepository, DtoMapper dtoMapper) {
        this.itemRepository = itemRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public GroceryItemDto createItem(GroceryItemDto itemDto) {
        GroceryItem item = dtoMapper.dtoToGroceryItem(itemDto);
        return dtoMapper.groceryItemToDto(itemRepository.save(item));
    }

    @Override
    public GroceryItemDto getItemById(Long id) {
        GroceryItem item = itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item not found"));
        return dtoMapper.groceryItemToDto(item);
    }

    @Override
    public List<GroceryItemDto> getAllItems() {
        return itemRepository.findAll()
            .stream()
            .map(dtoMapper::groceryItemToDto)
            .collect(Collectors.toList());
    }

    @Override
    public GroceryItemDto updateItem(Long id, GroceryItemDto itemDto) {
        GroceryItem item = itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setName(itemDto.getName());
        item.setCategory(itemDto.getCategory());
        item.setPrice(itemDto.getPrice());
        return dtoMapper.groceryItemToDto(itemRepository.save(item));
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

}
