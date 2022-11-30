package com.steadykingdev.juncommerce.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.steadykingdev.juncommerce.entity.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class UpdateItemRequestDto {

    private String brand;
    private String itemName;
    private int itemPrice;

    public UpdateItemRequestDto(Long id, String brand, String itemName, int itemPrice) {
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
