package com.yueking.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
//@Entity
@Table(name = "sys_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "sys_onetomany")
    @JoinColumn(name="user_id")
    private Set<Role> roles;
}
