package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.OrderStatus;
import com.steadykingdev.juncommerce.entity.item.Item;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.entity.order.Order;
import com.steadykingdev.juncommerce.exception.OutOfStockException;
import com.steadykingdev.juncommerce.repository.order.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void order() throws Exception {
        //given
        Member member = createMember();
        Item item = createItem("나이키", "에어포스", 100000, 50);
        int orderCount = 5;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findById(orderId).get();

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(getOrder.getTotalPrice()).isEqualTo(100000 * orderCount);
        assertThat(item.getStockQuantity()).isEqualTo(45);
    }

    @Test
    public void stockQuantityExceptionTest() throws Exception {
        //given
        Member member = createMember();
        Item item = createItem("나이키", "에어포스", 100000, 10);

        int orderCount = 11;

        //then
        Assertions.assertThatThrownBy(() -> orderService.order(member.getId(), item.getId(), orderCount))
                .isInstanceOf(OutOfStockException.class)
                .hasMessage("상품의 재고가 부족합니다.(현재 재고 수량: 10)");
    }

    @Test
    public void orderCancelTest() throws Exception {
        //given
        Member member = createMember();
        Item item = createItem("나이키", "에어포스", 100000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then

        Order getOrder = orderRepository.findById(orderId).get();

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }

    private Member createMember() {
        Address address1 = new Address("서울시", "시흥대로", "123-45");
        Member member = new Member(null, "member111", "멤버", "pass111", address1);
        em.persist(member);
        return member;
    }

    private Item createItem(String brand, String name, int price, int stockQuantity) {
        Item item = new Item(brand, name, price, stockQuantity);
        em.persist(item);
        return item;
    }



}
