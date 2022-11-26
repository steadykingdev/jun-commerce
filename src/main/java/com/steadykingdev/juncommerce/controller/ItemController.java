package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ItemRequestDto;
import com.steadykingdev.juncommerce.dto.ItemResponseDto;
import com.steadykingdev.juncommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public List<ItemResponseDto> getItems() {
        return itemService.findItems();
    }

    @GetMapping("/api/item/{id}")
    public ItemResponseDto getItem(@PathVariable("id") Long id) {
        return itemService.findOne(id);
    }

    @PostMapping("/api/item/add")
    public ItemResponseDto addItem(@RequestBody ItemRequestDto requestItemDto) {
        return itemService.saveItem(requestItemDto);
    }
}
