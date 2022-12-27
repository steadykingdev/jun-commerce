package com.steadykingdev.juncommerce.dto.order;

import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.order.OrderItem;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponseDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private Address address;
    private List<OrderItem> orderItems;
}
