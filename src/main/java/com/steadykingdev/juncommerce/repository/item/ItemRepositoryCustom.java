package com.steadykingdev.juncommerce.repository.item;

import com.steadykingdev.juncommerce.dto.item.ItemResponseDto;
import com.steadykingdev.juncommerce.dto.item.ItemSearchCondition;

import java.util.List;

public interface ItemRepositoryCustom {

    List<ItemResponseDto> search(ItemSearchCondition condition);
    List<ItemResponseDto> findAllItems();
}
