package com.steadykingdev.juncommerce.repository;

import com.steadykingdev.juncommerce.dto.ItemDto;
import com.steadykingdev.juncommerce.dto.ItemSearchCondition;
import com.steadykingdev.juncommerce.entity.Item;

import java.util.List;

public interface ItemRepositoryCustom {

    List<ItemDto> search(ItemSearchCondition condition);
    List<ItemDto> findAllItems();
    ItemDto findDtoById(Long id);

    List<Item> findAll();
}
