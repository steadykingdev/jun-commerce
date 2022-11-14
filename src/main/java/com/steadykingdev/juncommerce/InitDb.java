package com.steadykingdev.juncommerce;

import com.steadykingdev.juncommerce.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Item item1 = new Item("t");
            Item item2 = new Item("b");
            Item item3 = new Item("s");
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);
        }
    }
}
