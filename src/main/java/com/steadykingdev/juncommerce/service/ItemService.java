package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.item.ItemResponseDto;
import com.steadykingdev.juncommerce.dto.item.SaveItemRequestDto;
import com.steadykingdev.juncommerce.dto.item.UpdateItemRequestDto;
import com.steadykingdev.juncommerce.entity.item.Item;
import com.steadykingdev.juncommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public void updateItem(Long itemId, UpdateItemRequestDto itemDto) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException("상품이 존재하지 않습니다."));
        item.updateItem(itemDto.getBrand(), itemDto.getItemName(), itemDto.getItemPrice(), itemDto.getStockQuantity());
    }

    @Transactional
    public void deleteItem(Long itemId) {
        try {
            itemRepository.deleteById(itemId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchElementException("상품이 존재하지 않습니다.");
        }

    }
}
