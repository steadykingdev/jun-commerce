package com.steadykingdev.juncommerce.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.steadykingdev.juncommerce.entity.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.NONE)
public class ItemRequestDto {

    private Long id;
    private String brand;
    private String itemName;
    private int itemPrice;

    @QueryProjection
    public ItemRequestDto(Long id, String brand, String itemName, int itemPrice) {
        this.id = id;
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Item toEntity() {
        return Item.builder()
                .id(id)
                .brand(brand)
                .itemname(itemName)
                .itemPrice(itemPrice)
                .build();
    }

}
