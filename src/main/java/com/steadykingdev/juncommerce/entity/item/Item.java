package com.steadykingdev.juncommerce.entity.item;

import com.steadykingdev.juncommerce.entity.order.OrderItem;
import com.steadykingdev.juncommerce.exception.OutOfStockException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String brand;
    private String itemName;
    private int itemPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private int stockQuantity;

    public Item(String brand, String itemName, int itemPrice, int stockQuantity) {
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.stockQuantity = stockQuantity;
    }

    public void updateItem(String brand, String itemName, int itemPrice, int stockQuantity) {
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.stockQuantity = stockQuantity;
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다.(현재 재고 수량: " + this.stockQuantity + ")");
        }
        this.stockQuantity = restStock;
    }

    @Builder
    public Item(Long id, String brand, String itemname, int itemPrice, int stockQuantity) {
        this.id = id;
        this.brand = brand;
        this.itemName = itemname;
        this.itemPrice = itemPrice;
        this.stockQuantity =stockQuantity;
    }

}
