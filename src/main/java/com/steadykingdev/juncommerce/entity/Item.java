package com.steadykingdev.juncommerce.entity;

import lombok.AccessLevel;
import lombok.Builder;
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
    private String itemname;
    private int itemPrice;

    public Item(String brand, String itemname, int itemPrice) {
        this.brand = brand;
        this.itemname = itemname;
        this.itemPrice = itemPrice;
    }

    //    public void addStock(int quantity) {
//        this.stockQuantity += quantity;
//    }

//    public void removeStock(int quantity) {
//        int restStock = this.stockQuantity - quantity;
//        if(restStock < 0) {
//            // throw
//        }
//        this.stockQuantity = restStock;
//    }

    @Builder
    public Item(Long id, String brand, String itemname, int itemPrice) {
        this.id = id;
        this.brand = brand;
        this.itemname = itemname;
        this.itemPrice = itemPrice;
    }

}
