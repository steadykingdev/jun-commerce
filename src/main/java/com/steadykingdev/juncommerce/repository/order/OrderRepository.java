package com.steadykingdev.juncommerce.repository.order;

import com.steadykingdev.juncommerce.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
