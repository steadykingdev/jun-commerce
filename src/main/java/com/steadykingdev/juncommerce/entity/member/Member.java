package com.steadykingdev.juncommerce.entity.member;

import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        name = "LOGIN_ID_UNIQUE",
        columnNames = {"login_id"}
)})
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Size(min = 2, max = 15)
    @Column(name = "login_id")
    private String loginId;

    @Size(min = 2, max = 15)
    private String username;

    private String password;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(Long id, String loginId, String username, String password, Set<Authority> authorities, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.address = address;
    }

}
