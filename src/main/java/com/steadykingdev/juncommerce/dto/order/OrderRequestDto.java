package com.steadykingdev.juncommerce.dto.order;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private Long memberId;
    private Long itemId;
    private int count;
}
