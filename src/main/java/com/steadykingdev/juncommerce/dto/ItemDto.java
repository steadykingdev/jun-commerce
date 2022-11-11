package com.steadykingdev.juncommerce.dto;

import lombok.Data;

@Data
public class ItemDto {

    private int id;
    private String brand;
    private String itemName;
    private String size;
    private int price;
    private int stockQuantity;

    public ItemDto(String itemName) {
        this.itemName = itemName;
    }
}
