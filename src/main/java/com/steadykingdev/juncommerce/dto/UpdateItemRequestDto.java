package com.steadykingdev.juncommerce.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Setter(AccessLevel.NONE)
public class UpdateItemRequestDto {

    @NotBlank(message = "브랜드를 입력하세요.")
    private String brand;

    @NotBlank(message = "상품명을 입력하세요.")
    private String itemName;

    @NotBlank(message = "가격을 입력하세요.")
    private int itemPrice;

    @NotBlank(message = "재고 수량을 입력하세요.")
    private int stockQuantity;

    public UpdateItemRequestDto(Long id, String brand, String itemName, int itemPrice, int stockQuantity) {
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.stockQuantity = stockQuantity;
    }
}
