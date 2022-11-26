package com.steadykingdev.juncommerce.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.steadykingdev.juncommerce.entity.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class ItemResponseDto {

    private Long id;
    private String brand;
    private String itemName;
    private int itemPrice;

    @QueryProjection
    public ItemResponseDto(Long id, String brand, String itemName, int itemPrice) {
        this.id = id;
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public static ItemResponseDto from(Item item) {
        return new ItemResponseDto(item.getId(), item.getBrand(), item.getItemname(), item.getItemPrice());
    }
}
