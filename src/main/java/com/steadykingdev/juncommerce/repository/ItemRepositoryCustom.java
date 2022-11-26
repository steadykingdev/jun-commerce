package com.steadykingdev.juncommerce.repository;

import com.steadykingdev.juncommerce.dto.ItemResponseDto;
import com.steadykingdev.juncommerce.dto.ItemSearchCondition;

import java.util.List;

public interface ItemRepositoryCustom {

    List<ItemResponseDto> search(ItemSearchCondition condition);
    List<ItemResponseDto> findAllItems();
    ItemResponseDto findItemDtoById(Long id);

}
