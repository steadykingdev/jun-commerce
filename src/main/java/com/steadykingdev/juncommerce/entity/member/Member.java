package com.steadykingdev.juncommerce.entity.member;

import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String username;

    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(Long id, String loginId, String username, String password, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.address = address;
    }


}
