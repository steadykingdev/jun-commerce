package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.item.ItemResponseDto;
import com.steadykingdev.juncommerce.dto.item.SaveItemRequestDto;
import com.steadykingdev.juncommerce.entity.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ItemServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired ItemService itemService;

    @BeforeEach
    public void before() {
        Item item1 = new Item("나이키", "에어포스", 100000, 50);
        Item item2 = new Item("아디다스", "슈퍼스타", 80000, 30);
        Item item3 = new Item("푸마", "스웨이드", 70000, 20);
        em.persist(item1);
        em.persist(item2);
        em.persist(item3);
    }

    @Test
    public void findItemsTest() throws Exception {
        //given

        //when
        List<ItemResponseDto> itemDtos = itemService.findItems();

        //then
        assertThat(itemDtos.size()).isEqualTo(6); // initDb에 3개 더 있음.
    }

    @Test
    public void findOneTest() throws Exception {
        //given
        Item item = new Item("알렉산더 맥퀸", "오버솔", 560000, 30);
        em.persist(item);

        //when
        ItemResponseDto itemDto = itemService.findOne(item.getId());

        //then
        assertThat(itemDto.getId()).isEqualTo(item.getId());
        assertThat(itemDto.getBrand()).isEqualTo(item.getBrand());
        assertThat(itemDto.getItemName()).isEqualTo(item.getItemName());
        assertThat(itemDto.getItemPrice()).isEqualTo(item.getItemPrice());
        assertThat(itemDto.getStockQuantity()).isEqualTo(item.getStockQuantity());
    }

    @Test
    public void saveItemTest() throws Exception {
        //given
        SaveItemRequestDto itemDto = new SaveItemRequestDto(null, "알렉산더 맥퀸", "오버솔", 560000, 30);

        //when
        Long savedItemId = itemService.saveItem(itemDto);

        //then
        assertThat(savedItemId).isEqualTo(7L);
    }

}
