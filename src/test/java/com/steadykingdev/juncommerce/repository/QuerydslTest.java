package com.steadykingdev.juncommerce.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.steadykingdev.juncommerce.entity.Item;
import com.steadykingdev.juncommerce.entity.QItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class QuerydslTest {

    @Autowired
    EntityManager em;

    @Test
    public void contextLoads() throws Exception {
        //given
        Item item = new Item("a브랜드", "a이름", 10000,50);
        em.persist(item);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QItem qItem = QItem.item;

        //when
        Item result = query
                .selectFrom(qItem)
                .where(qItem.itemname.eq("a"))
                .fetchOne();

        //then
        Assertions.assertThat(result).isEqualTo(item);
        Assertions.assertThat(result.getItemname()).isEqualTo(item.getItemname());
    }

}
