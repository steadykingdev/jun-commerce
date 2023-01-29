package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ApiResponse;
import com.steadykingdev.juncommerce.dto.item.ItemResponseDto;
import com.steadykingdev.juncommerce.dto.item.SaveItemRequestDto;
import com.steadykingdev.juncommerce.dto.item.UpdateItemRequestDto;
import com.steadykingdev.juncommerce.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.crossstore.ChangeSetPersister.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @ApiOperation(value = "상품 리스트 조회", notes = "모든 상품을 조회한다")
    @GetMapping("/list")
    public ApiResponse<List<ItemResponseDto>> getItems() {
        List<ItemResponseDto> items = itemService.findItems();
        return ApiResponse.createSuccess(items);
    }

    @ApiOperation(value = "상품 조회", notes = "하나의 상품을 조회한다")
    @GetMapping("/{id}")
    public ApiResponse<ItemResponseDto> getItem(@PathVariable("id") Long id) {
        ItemResponseDto item = itemService.findOne(id);
        return ApiResponse.createSuccess(item);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "상품 등록", notes = "상품을 등록한다")
    @PostMapping("/add")
    public ApiResponse addItem(@Valid @RequestBody SaveItemRequestDto saveItemRequestDto) {
        itemService.saveItem(saveItemRequestDto);
        return ApiResponse.createSuccessWithNoContent();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "상품 수정", notes = "상품을 수정한다")
    @PostMapping("/update/{id}")
    public ApiResponse editItem(@Valid @RequestBody UpdateItemRequestDto updateItemRequestDto, @PathVariable("id") Long id) {
        itemService.updateItem(id, updateItemRequestDto);
        return ApiResponse.createSuccessWithNoContent();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "상품 삭제", notes = "상품을 삭제한다")
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return ApiResponse.createSuccessWithNoContent();
    }
}
