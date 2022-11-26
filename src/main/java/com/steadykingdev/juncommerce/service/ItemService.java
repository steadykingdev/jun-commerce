package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.ItemRequestDto;
import com.steadykingdev.juncommerce.dto.ItemResponseDto;
import com.steadykingdev.juncommerce.entity.Item;
import com.steadykingdev.juncommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemResponseDto> findItems() {
        return itemRepository.findAllItems();
    }

    public ItemResponseDto findOne(Long itemId) {
        return itemRepository.findItemDtoById(itemId);
    }

    @Transactional
    public ItemResponseDto saveItem(ItemRequestDto itemDto) {
        Item savedItem = itemRepository.save(itemDto.toEntity());
        return ItemResponseDto.from(savedItem);
    }
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

}
