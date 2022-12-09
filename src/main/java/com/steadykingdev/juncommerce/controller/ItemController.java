package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ApiResponse;
import com.steadykingdev.juncommerce.dto.SaveItemRequestDto;
import com.steadykingdev.juncommerce.dto.ItemResponseDto;
import com.steadykingdev.juncommerce.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @ApiOperation(value = "상품 리스트 조회", notes = "모든 상품을 조회한다")
    @GetMapping("/api/items")
    public ApiResponse<List<ItemResponseDto>> getItems() {
        List<ItemResponseDto> items = itemService.findItems();
        return ApiResponse.createSuccess(items);
    }

    @ApiOperation(value = "상품 조회", notes = "하나의 상품을 조회한다")
    @GetMapping("/api/item/{id}")
    public ApiResponse<ItemResponseDto> getItem(@PathVariable("id") Long id) {
        ItemResponseDto item = itemService.findOne(id);
        return ApiResponse.createSuccess(item);
    }

    @ApiOperation(value = "상품 등록", notes = "상품을 등록한다")
    @PostMapping("/api/item/add")
    public ApiResponse addItem(@Valid @RequestBody SaveItemRequestDto saveItemRequestDto) {
        itemService.saveItem(saveItemRequestDto);
        return ApiResponse.createSuccessWithNoContent();
    }

}