package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.SaveItemRequestDto;
import com.steadykingdev.juncommerce.dto.ItemResponseDto;
import com.steadykingdev.juncommerce.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @ApiOperation(value = "상품 조회", notes = "모든 상품을 조회한다")
    @GetMapping("/api/items")
    public List<ItemResponseDto> getItems() {
        return itemService.findItems();
    }

    @ApiOperation(value = "상품 조회", notes = "하나의 상품을 조회한다")
    @GetMapping("/api/item/{id}")
    public ItemResponseDto getItem(@PathVariable("id") Long id) {
        return itemService.findOne(id);
    }

    @ApiOperation(value = "상품 등록", notes = "상품을 등록한다")
    @PostMapping("/api/item/add")
    public Long addItem(@RequestBody SaveItemRequestDto saveItemRequestDto) {
        return itemService.saveItem(saveItemRequestDto);
    }

}
