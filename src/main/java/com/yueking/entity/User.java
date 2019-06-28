package com.yueking.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    public User() {
    }

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 2, max = 20)
    private String username;


    @Size(max = 60)
    private String address;


    @Column(name = "create_date")
    private Date createDate;


    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;


    @Size(min = 2, max = 20)
    private String name;

    @NotEmpty
    @Email
    @Size(max = 50)
    private String email;


}
