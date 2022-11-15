package com.steadykingdev.juncommerce.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.steadykingdev.juncommerce.dto.ItemDto;
import com.steadykingdev.juncommerce.dto.ItemSearchCondition;
import com.steadykingdev.juncommerce.dto.QItemDto;
import com.steadykingdev.juncommerce.entity.Item;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.steadykingdev.juncommerce.entity.QItem.item;

public class ItemRepositoryImpl implements ItemRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<ItemDto> search(ItemSearchCondition condition) {
        return null;
    }

    public List<ItemDto> findAllItems() {
        return queryFactory
                .select(new QItemDto(item.itemname))
                .from(item)
                .fetch();
    }

    @Override
    public ItemDto findDtoById(Long id) {

        return queryFactory
                .select(new QItemDto(item.itemname))
                .from(item)
                .where(item.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<Item> findAll() {
        List<String> a = new ArrayList<String>();
        return null;
    }


}
