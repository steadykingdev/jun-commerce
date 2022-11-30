package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ApiResult;
import com.steadykingdev.juncommerce.dto.SaveItemRequestDto;
import com.steadykingdev.juncommerce.dto.ItemResponseDto;
import com.steadykingdev.juncommerce.service.ItemService;
import com.steadykingdev.juncommerce.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public ApiResult getItems() {
        List<ItemResponseDto> items = itemService.findItems();
        return ApiUtils.success(items);
    }

    @GetMapping("/api/item/{id}")
    public ApiResult getItem(@PathVariable("id") Long id) {
        ItemResponseDto item = itemService.findOne(id);
        return ApiUtils.success(item);
    }

    @PostMapping("/api/item/add")
    public ApiResult addItem(@RequestBody SaveItemRequestDto saveItemRequestDto) {
        Long itemId = itemService.saveItem(saveItemRequestDto);
        return ApiUtils.success(itemId);
    }

}
