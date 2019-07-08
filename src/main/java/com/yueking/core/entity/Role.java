package com.yueking.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="sys_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
}
