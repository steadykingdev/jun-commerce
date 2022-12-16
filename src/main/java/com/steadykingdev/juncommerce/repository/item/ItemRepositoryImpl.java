package com.steadykingdev.juncommerce.repository.item;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.steadykingdev.juncommerce.dto.item.ItemResponseDto;
import com.steadykingdev.juncommerce.dto.item.ItemSearchCondition;
import com.steadykingdev.juncommerce.dto.item.QItemResponseDto;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.steadykingdev.juncommerce.entity.item.QItem.item;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemResponseDto> search(ItemSearchCondition condition) {
        return null;
    }

    public List<ItemResponseDto> findAllItems() {

        return queryFactory
                .select(new QItemResponseDto(item.id, item.brand, item.itemName, item.itemPrice, item.stockQuantity))
                .from(item)
                .fetch();
    }
}
