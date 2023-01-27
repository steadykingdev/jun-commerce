package com.steadykingdev.juncommerce;

import com.steadykingdev.juncommerce.entity.item.Item;
import com.steadykingdev.juncommerce.entity.member.Authority;
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
            Item item1 = new Item("스톤아일랜드", "티",500000, 50);
            Item item2 = new Item("준", "바지",15000, 50);
            Item item3 = new Item("리복","신발",50000, 50);
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);

            Authority authority1 = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();
            Authority authority2 = Authority.builder()
                    .authorityName("ROLE_ADMIN")
                    .build();

            em.persist(authority1);
            em.persist(authority2);
        }
    }
}
