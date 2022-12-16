package com.steadykingdev.juncommerce.repository.item;

import com.steadykingdev.juncommerce.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
}
