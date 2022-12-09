package com.steadykingdev.juncommerce.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class ItemTest {

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    public void before() {
        Item itemA = new Item("t브랜드", "t이름", 10000, 50);
        Item itemB = new Item("b브랜드", "b이름", 30000, 50);
        em.persist(itemA);
        em.persist(itemB);
    }

    @Test
    public void querydslTest() throws Exception {
        //given
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem i = new QItem("i");
        //when
        Item findItem = queryFactory
                .select(i)
                .from(i)
                .where(i.itemName.eq("t이름"))
                .fetchOne();

        //then
        Assertions.assertThat(findItem.getItemName()).isEqualTo("t");
    }



}