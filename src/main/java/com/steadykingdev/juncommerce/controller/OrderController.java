package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ApiResponse;
import com.steadykingdev.juncommerce.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "주문하기", notes = "주문을 생성한다.")
    @PostMapping("/add")
    public ApiResponse addOrder(@RequestBody Long memberId, @RequestBody Long itemId, @RequestBody int count) {

        orderService.order(memberId, itemId, count);
        return ApiResponse.createSuccessWithNoContent();
    }




}
