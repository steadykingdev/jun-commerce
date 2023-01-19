package com.steadykingdev.juncommerce.repository.item;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.steadykingdev.juncommerce.dto.item.ItemResponseDto;
import com.steadykingdev.juncommerce.dto.item.QItemResponseDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.steadykingdev.juncommerce.entity.item.QItem.item;


public class ItemRepositoryImpl implements ItemRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ItemResponseDto> findAllItems() {

        return queryFactory
                .select(new QItemResponseDto(item.id, item.brand, item.itemName, item.itemPrice, item.stockQuantity))
                .from(item)
                .fetch();
    }
}
