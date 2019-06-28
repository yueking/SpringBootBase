package com.yueking.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Size(max = 20)
    private  String name;

    @OneToMany(mappedBy = "department")
    private Set<User> users = new HashSet<User>();

    public Department(){};
}
