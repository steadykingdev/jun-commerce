package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.common.CommonResult;
import com.steadykingdev.juncommerce.common.ListResult;
import com.steadykingdev.juncommerce.common.SingleResult;
import com.steadykingdev.juncommerce.dto.SaveItemRequestDto;
import com.steadykingdev.juncommerce.dto.ItemResponseDto;
import com.steadykingdev.juncommerce.service.ItemService;
import com.steadykingdev.juncommerce.service.ResponseService;
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
    private final ResponseService responseService;

    @ApiOperation(value = "상품 조회", notes = "모든 상품을 조회한다")
    @GetMapping("/api/items")
    public ListResult<ItemResponseDto> getItems() {
        return responseService.getListResult(itemService.findItems());
    }

    @ApiOperation(value = "상품 조회", notes = "하나의 상품을 조회한다")
    @GetMapping("/api/item/{id}")
    public SingleResult<ItemResponseDto> getItem(@PathVariable("id") Long id) {
        return responseService.getSingleResult(itemService.findOne(id));
    }

    @ApiOperation(value = "상품 등록", notes = "상품을 등록한다")
    @PostMapping("/api/item/add")
    public CommonResult addItem(@RequestBody SaveItemRequestDto saveItemRequestDto) {
        return responseService.getSingleResult(itemService.saveItem(saveItemRequestDto));
    }

}
