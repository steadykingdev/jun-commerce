package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.order.OrderResponseDto;
import com.steadykingdev.juncommerce.entity.DeliveryStatus;
import com.steadykingdev.juncommerce.entity.delivery.Delivery;
import com.steadykingdev.juncommerce.entity.item.Item;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.entity.order.Order;
import com.steadykingdev.juncommerce.entity.order.OrderItem;
import com.steadykingdev.juncommerce.repository.item.ItemRepository;
import com.steadykingdev.juncommerce.repository.member.MemberRepository;
import com.steadykingdev.juncommerce.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findById(itemId).get();

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getItemPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderExistence(orderId);
        order.cancel();
    }

    private Order orderExistence(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("주문을 찾을 수 없습니다."));
    }
}
