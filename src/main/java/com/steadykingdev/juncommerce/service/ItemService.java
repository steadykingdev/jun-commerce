package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.SaveItemRequestDto;
import com.steadykingdev.juncommerce.dto.ItemResponseDto;
import com.steadykingdev.juncommerce.dto.UpdateItemRequestDto;
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
    public Long saveItem(SaveItemRequestDto itemDto) {
        Item savedItem = itemRepository.save(itemDto.toEntity());
        return savedItem.getId();
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemRequestDto ItemDto) {

    }

    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

}
