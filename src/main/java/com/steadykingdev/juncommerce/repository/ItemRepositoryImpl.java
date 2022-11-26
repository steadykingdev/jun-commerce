package com.steadykingdev.juncommerce.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.steadykingdev.juncommerce.dto.*;

import javax.persistence.EntityManager;
import java.util.List;

import static com.steadykingdev.juncommerce.entity.QItem.item;

public class ItemRepositoryImpl implements ItemRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<ItemResponseDto> search(ItemSearchCondition condition) {
        return null;
    }

    public List<ItemResponseDto> findAllItems() {

        return queryFactory
                .select(new QItemResponseDto(item.itemname))
                .from(item)
                .fetch();
    }

    @Override
    public ItemResponseDto findItemDtoById(Long id) {

        return queryFactory
                .select(new QItemResponseDto(item.itemname))
                .from(item)
                .where(item.id.eq(id))
                .fetchOne();
    }
}
