package com.steadykingdev.juncommerce.dto;

import com.steadykingdev.juncommerce.entity.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.NONE)
public class SaveItemRequestDto {

    private String brand;
    private String itemName;
    private int itemPrice;

    public SaveItemRequestDto(Long id, String brand, String itemName, int itemPrice) {
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Item toEntity() {
        return Item.builder()
                .brand(brand)
                .itemname(itemName)
                .itemPrice(itemPrice)
                .build();
    }

}
