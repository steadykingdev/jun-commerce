package com.steadykingdev.juncommerce.repository;

import com.steadykingdev.juncommerce.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void testItem() throws Exception {
        //given
        Item item = new Item("옷");

        //when
        Item savedItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(savedItem.getId()).get();

        //then
        assertThat(findItem.getId()).isEqualTo(item.getId());
        assertThat(findItem.getItemName()).isEqualTo(item.getItemName());
        assertThat(findItem).isEqualTo(item);
    }

}