package com.steadykingdev.juncommerce.dto;

import com.steadykingdev.juncommerce.entity.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.NONE)
public class SaveItemRequestDto {

    @NotNull(message = "브랜드를 입력하세요.")
    private String brand;

    @NotNull(message = "상품명을 입력하세요.")
    private String itemName;

    @NotNull(message = "가격을 입력하세요.")
    private int itemPrice;

    @NotNull(message = "재고 수량을 입력하세요.")
    private int stockQuantity;

    public SaveItemRequestDto(Long id, String brand, String itemName, int itemPrice, int stockQuantity) {
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.stockQuantity = stockQuantity;
    }

    public Item toEntity() {
        return Item.builder()
                .brand(brand)
                .itemname(itemName)
                .itemPrice(itemPrice)
                .stockQuantity(stockQuantity)
                .build();
    }

}
