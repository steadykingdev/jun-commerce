package com.steadykingdev.juncommerce.entity.delivery;

import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.DeliveryStatus;
import com.steadykingdev.juncommerce.entity.order.Order;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
