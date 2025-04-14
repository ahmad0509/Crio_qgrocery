package com.crio.qgrocery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.qgrocery.dto.GroceryItemDto;
import com.crio.qgrocery.service.GroceryItemManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class GroceryItemController {

    private final GroceryItemManager itemManager;

    @PostMapping
    public ResponseEntity<GroceryItemDto> createItem(@RequestBody @Valid GroceryItemDto dto) {
        GroceryItemDto created = itemManager.createItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryItemDto> getItem(@PathVariable Long id) {
        GroceryItemDto item = itemManager.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<GroceryItemDto>> getAllItems() {
        return ResponseEntity.ok(itemManager.getAllItems());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemManager.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}