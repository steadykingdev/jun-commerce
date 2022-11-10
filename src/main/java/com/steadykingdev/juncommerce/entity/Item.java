package com.steadykingdev.juncommerce.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String brand;
    private String itemName;
    private String itemSize;
    private int itemPrice;
    private int stockQuantity;

    public Item(String itemName) {
        this.itemName = itemName;
    } // 임시로 만든 생성자 함수

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            // throw
        }
        this.stockQuantity = restStock;
    }

}
