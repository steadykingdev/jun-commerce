package com.steadykingdev.juncommerce.repository.item;

import com.steadykingdev.juncommerce.dto.item.ItemResponseDto;

import java.util.List;

public interface ItemRepositoryCustom {

    List<ItemResponseDto> findAllItems();
}
